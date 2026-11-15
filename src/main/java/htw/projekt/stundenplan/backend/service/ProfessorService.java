package htw.projekt.stundenplan.backend.service;

import htw.projekt.stundenplan.backend.dto.ProfessorCreateRequest;

/**
 * Funktion:
 * Definiert die Funktionen zur administrativen
 * Erstellung neuer Professorenkonten.
 *
 * @author Nicole Eisner
 */
public interface ProfessorService {

    /**
     * Erstellt ein neues Professorenkonto.
     *
     * @param request Name und E-Mail-Adresse des Professors
     * @return ID des neu erstellten Professorenkontos
     */
    Long createProfessor(ProfessorCreateRequest request);
}
