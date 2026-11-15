
package htw.projekt.stundenplan.backend.service;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.ProfessorCreateRequest;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.enums.Rolle;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;

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

    public ProfessorServiceImpl(
            BenutzerkontoRepository benutzerkontoRepository) {

        this.benutzerkontoRepository = benutzerkontoRepository;
    }

    /**
     * Prüft die E-Mail-Adresse und erstellt ein
     * neues Professorenkonto in der Datenbank.
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

        // Vorläufiges Testpasswort
        // Wird in Task 4 durch die Passwortgenerierung ersetzt
        String anfangspasswort = "Test1234!";

        // Neues Professorenkonto erstellen
        Benutzerkonto professor = new Benutzerkonto(
                request.getName().trim(),
                email,
                anfangspasswort,
                Rolle.ROLE_PROF,
                null
        );

        // Passwortwechsel beim ersten Login verpflichtend
        professor.setPasswortAendernPflicht(true);

        // Professorenkonto in der Datenbank speichern
        Benutzerkonto gespeichert =
                benutzerkontoRepository.save(professor);

        // ID des erstellten Professorenkontos zurückgeben
        return gespeichert.getId();
    }
}
