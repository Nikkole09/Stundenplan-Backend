
package htw.projekt.stundenplan.backend.service;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.Studienordnung;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    private final StudienordnungRepository studienordnungRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthServiceImpl(
            BenutzerkontoRepository benutzerkontoRepository,
            StudienordnungRepository studienordnungRepository) {

        this.benutzerkontoRepository = benutzerkontoRepository;
        this.studienordnungRepository = studienordnungRepository;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        // 1. E-Mail vorbereiten
        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        // 2. Prüfen, ob E-Mail bereits existiert
        if (benutzerkontoRepository.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-Mail ist bereits registriert"
            );
        }

        // 3. Studienordnung suchen
        Studienordnung studienordnung =
                studienordnungRepository
                        .findById(request.studienordnungId())
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Studienordnung nicht gefunden"
                                )
                        );

        // 4. Neues Benutzerkonto erstellen
        Benutzerkonto benutzerkonto = new Benutzerkonto();

        benutzerkonto.setName(request.name().trim());
        benutzerkonto.setEmail(email);

        // Passwort verschlüsseln
        benutzerkonto.setPasswordHash(
                passwordEncoder.encode(request.password())
        );

        benutzerkonto.setRole("ROLE_STUDENT");
        benutzerkonto.setStudienordnung(studienordnung);
        benutzerkonto.setActive(true);
        benutzerkonto.setMustChangePw(false);

        // 5. Benutzerkonto speichern
        Benutzerkonto gespeichert =
                benutzerkontoRepository.save(benutzerkonto);

        // 6. Antwort zurückgeben
        return new RegisterResponse(
                gespeichert.getId(),
                gespeichert.getEmail(),
                gespeichert.getRole(),
                studienordnung.getId()
        );
    }

   
@Override
public AuthResponse login(LoginRequest request) {

    // 1. E-Mail vorbereiten
    String email = request.email()
            .trim()
            .toLowerCase(Locale.ROOT);

    // 2. Benutzerkonto suchen
    Benutzerkonto benutzerkonto =
            benutzerkontoRepository
                    .findByEmailIgnoreCase(email)
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.UNAUTHORIZED,
                                    "Ungültige Anmeldedaten"
                            )
                    );

    // 3. Passwort und Kontostatus überprüfen
    if (!Boolean.TRUE.equals(benutzerkonto.getActive())
            || !passwordEncoder.matches(
                    request.password(),
                    benutzerkonto.getPasswordHash())) {

        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Ungültige Anmeldedaten"
        );
    }

    // 4. JWT-Anbindung folgt nach Integration von T1
    throw new UnsupportedOperationException(
            "JWT-TokenProvider aus T1 noch nicht verfügbar"
    );
}

}
