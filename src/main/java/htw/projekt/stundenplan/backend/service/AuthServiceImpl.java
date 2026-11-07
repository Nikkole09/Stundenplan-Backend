
package htw.projekt.stundenplan.backend.service;

import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.Studienordnung;
import htw.projekt.stundenplan.backend.enums.Rolle;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;
import htw.projekt.stundenplan.backend.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    private final StudienordnungRepository studienordnungRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            BenutzerkontoRepository benutzerkontoRepository,
            StudienordnungRepository studienordnungRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {

        this.benutzerkontoRepository = benutzerkontoRepository;
        this.studienordnungRepository = studienordnungRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        // Prüfen, ob die E-Mail bereits registriert ist
        if (benutzerkontoRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-Mail ist bereits registriert"
            );
        }

        // Ausgewählte Studienordnung finden
        Studienordnung studienordnung =
                studienordnungRepository
                        .findById(request.studienordnungId())
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Studienordnung nicht gefunden"
                                )
                        );

        // Benutzerkonto erstellen
        Benutzerkonto benutzerkonto = new Benutzerkonto(
                request.name().trim(),
                email,
                passwordEncoder.encode(request.password()),
                Rolle.ROLE_STUDENT,
                studienordnung
        );

        // Benutzerkonto speichern
        Benutzerkonto gespeichert =
                benutzerkontoRepository.save(benutzerkonto);

        return new RegisterResponse(
                gespeichert.getId(),
                gespeichert.getEmail(),
                gespeichert.getRolle().name(),
                studienordnung.getId()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        // Benutzerkonto suchen
        Benutzerkonto benutzerkonto =
                benutzerkontoRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "Ungültige Anmeldedaten"
                                )
                        );

        // Passwort und Kontostatus prüfen
        if (!benutzerkonto.getAktiv()
                || !passwordEncoder.matches(
                        request.password(),
                        benutzerkonto.getPasswordHash())) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Ungültige Anmeldedaten"
            );
        }

        // Vorhandenen JwtTokenProvider verwenden
        String token = jwtTokenProvider.generateToken(
                benutzerkonto.getEmail()
        );

        return new AuthResponse(
                token,
                "Bearer",
                List.of(benutzerkonto.getRolle().name())
        );
    }
}
