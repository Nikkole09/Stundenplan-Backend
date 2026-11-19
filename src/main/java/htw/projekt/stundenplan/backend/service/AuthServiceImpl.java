
package htw.projekt.stundenplan.backend.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.PasswortResetToken;
import htw.projekt.stundenplan.backend.entity.Studienordnung;
import htw.projekt.stundenplan.backend.enums.Rolle;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.repository.PasswortResetTokenRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;
import htw.projekt.stundenplan.backend.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    private final StudienordnungRepository studienordnungRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // Neu für Passwort-Reset
    private final PasswortResetTokenRepository resetTokenRepository;
    private final PasswortResetMailService resetMailService;

    private final SecureRandom secureRandom = new SecureRandom();

    public AuthServiceImpl(
            BenutzerkontoRepository benutzerkontoRepository,
            StudienordnungRepository studienordnungRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            PasswortResetTokenRepository resetTokenRepository,
            PasswortResetMailService resetMailService) {

        this.benutzerkontoRepository = benutzerkontoRepository;
        this.studienordnungRepository = studienordnungRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.resetTokenRepository = resetTokenRepository;
        this.resetMailService = resetMailService;
    }

    // ------------------------------------
    // Bestehende Registrierung
    // ------------------------------------

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        if (benutzerkontoRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-Mail ist bereits registriert"
            );
        }

        Studienordnung studienordnung =
                studienordnungRepository
                        .findById(request.studienordnungId())
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Studienordnung nicht gefunden"
                                )
                        );

        Benutzerkonto benutzerkonto = new Benutzerkonto(
                request.name().trim(),
                email,
                passwordEncoder.encode(request.password()),
                Rolle.ROLE_STUDENT,
                studienordnung
        );

        Benutzerkonto gespeichert =
                benutzerkontoRepository.save(benutzerkonto);

        return new RegisterResponse(
                gespeichert.getId(),
                gespeichert.getEmail(),
                gespeichert.getRolle().name(),
                studienordnung.getId()
        );
    }

    // ------------------------------------
    // Bestehender Login
    // ------------------------------------

    @Override
    public AuthResponse login(LoginRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        Benutzerkonto benutzerkonto =
                benutzerkontoRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "Ungültige Anmeldedaten"
                                )
                        );

        if (!benutzerkonto.getAktiv()
                || !passwordEncoder.matches(
                        request.password(),
                        benutzerkonto.getPasswordHash())) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Ungültige Anmeldedaten"
            );
        }

        String token = jwtTokenProvider.generateToken(
                benutzerkonto.getEmail()
        );

        return new AuthResponse(
                token,
                "Bearer",
                List.of(benutzerkonto.getRolle().name())
        );
    }

    // ------------------------------------
    // Neu: Passwort-Reset anfordern
    // ------------------------------------

    @Override
    @Transactional
    public void passwortZuruecksetzen(String email) {

        // Unbekannte oder ungültige E-Mail:
        // Nach außen immer dieselbe Antwort.
        if (email == null) {
            return;
        }

        String normalisierteEmail = email
                .trim()
                .toLowerCase(Locale.ROOT);

        if (!normalisierteEmail.matches(
                "^[^\\s@]+@(student\\.)?htw-berlin\\.de$")) {
            return;
        }

        benutzerkontoRepository
                .findByEmail(normalisierteEmail)
                .filter(Benutzerkonto::getAktiv)
                .ifPresent(benutzerkonto -> {

                    // Sicheren zufälligen Token erstellen
                    byte[] zufallsBytes = new byte[32];
                    secureRandom.nextBytes(zufallsBytes);

                    String token = Base64.getUrlEncoder()
                            .withoutPadding()
                            .encodeToString(zufallsBytes);

                    // Vorherige Reset-Tokens entfernen
                    resetTokenRepository
                            .deleteByBenutzerkonto(benutzerkonto);

                    // Token-Hash mit Ablaufzeit speichern
                    PasswortResetToken resetToken =
                            new PasswortResetToken(
                                    hashToken(token),
                                    Instant.now().plus(
                                            30,
                                            ChronoUnit.MINUTES
                                    ),
                                    benutzerkonto
                            );

                    resetTokenRepository.save(resetToken);

                    // Mail erst nach erfolgreichem
                    // Datenbank-Commit versenden.
                    TransactionSynchronizationManager
                            .registerSynchronization(
                                    new TransactionSynchronization() {

                        @Override
                        public void afterCommit() {
                            resetMailService.sendeResetMail(
                                    benutzerkonto.getEmail(),
                                    token
                            );
                        }
                    });
                });
    }

    // ------------------------------------
    // Neu: Passwort mit Token ändern
    // ------------------------------------

    @Override
    @Transactional
    public void passwortNeuSetzen(
            String token,
            String neuesPasswort) {

        if (token == null || token.isBlank()
                || token.length() > 128) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ungültiger oder abgelaufener Reset-Token"
            );
        }

        // Gleiche Mindestlänge wie bei der Registrierung.
        // Die Byte-Grenze verhindert BCrypt-Trunkierung.
        if (neuesPasswort == null
                || neuesPasswort.length() < 8
                || neuesPasswort.getBytes(
                        StandardCharsets.UTF_8
                ).length > 72) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Das Passwort ist ungültig"
            );
        }

        // Token über seinen Hash suchen
        PasswortResetToken resetToken =
                resetTokenRepository
                        .findByTokenHash(hashToken(token))
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Ungültiger oder abgelaufener Reset-Token"
                                )
                        );

        // Ablaufzeit prüfen
        if (!resetToken.getAblaufzeit()
                .isAfter(Instant.now())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ungültiger oder abgelaufener Reset-Token"
            );
        }

        Benutzerkonto benutzerkonto =
                resetToken.getBenutzerkonto();

        if (!benutzerkonto.getAktiv()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ungültiger oder abgelaufener Reset-Token"
            );
        }

        // Neues Passwort mit BCrypt hashen
        benutzerkonto.setPasswordHash(
                passwordEncoder.encode(neuesPasswort)
        );

        // Benutzerkonto speichern
        benutzerkontoRepository.save(benutzerkonto);

        // Alle Reset-Tokens dieses Kontos löschen
        resetTokenRepository
                .deleteByBenutzerkonto(benutzerkonto);
    }

    // ------------------------------------
    // Neu: SHA-256-Hash des Reset-Tokens
    // ------------------------------------

    private String hashToken(String token) {

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    token.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 ist nicht verfügbar",
                    e
            );
        }
    }
}
