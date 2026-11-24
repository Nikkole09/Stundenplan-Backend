package htw.projekt.stundenplan.backend.service;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.ProfessorCreateRequest;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.enums.Rolle;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.util.PasswordGenerator;

/**
 * Funktion:
 * Implementiert die Erstellung neuer Professorenkonten
 * durch eine Administratorin.
 *
 * @author Nicole Eisner
 */
@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    private final PasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;

    // Benötigte Komponenten für die Kontoerstellung einbinden
    public ProfessorServiceImpl(
            BenutzerkontoRepository benutzerkontoRepository,
            PasswordGenerator passwordGenerator,
            PasswordEncoder passwordEncoder) {

        this.benutzerkontoRepository = benutzerkontoRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Prüft die E-Mail-Adresse, generiert ein sicheres
     * Initialpasswort und erstellt ein neues
     * Professorenkonto in der Datenbank.
     *
     * @param request Name und E-Mail-Adresse des Professors
     * @return ID des erstellten Professorenkontos
     */
    @Override
    @Transactional
    public Long createProfessor(ProfessorCreateRequest request) {

        // E-Mail-Adresse vereinheitlichen
        String email = request.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        // Prüfen, ob die E-Mail bereits existiert
        if (benutzerkontoRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-Mail ist bereits registriert"
            );
        }

        // Zufälliges Initialpasswort generieren
        String anfangspasswort = passwordGenerator.generatePassword();

        // Initialpasswort mit BCrypt sicher hashen
        String passwordHash = passwordEncoder.encode(anfangspasswort);

        // Neues Professorenkonto mit gehashtem Passwort erstellen
        Benutzerkonto professor = new Benutzerkonto(
                request.getName().trim(),
                email,
                passwordHash,
                Rolle.ROLE_PROF,
                null,
                null
        );

        // Passwortwechsel beim ersten Login verpflichtend
        professor.setPasswortAendernPflicht(true);

        // Professorenkonto in der Datenbank speichern
        Benutzerkonto gespeichert =
                benutzerkontoRepository.save(professor);

        // TODO: Initialpasswort später intern an den E-Mail-Service übergeben

        // ID des erstellten Professorenkontos zurückgeben
        return gespeichert.getId();
    }
}