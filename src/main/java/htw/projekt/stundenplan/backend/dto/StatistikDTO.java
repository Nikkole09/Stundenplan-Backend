package htw.projekt.stundenplan.backend.dto;

import java.util.List;

// Enthält die Statistik einer Kohorte mit den Werten pro Semester.
public record StatistikDTO(
        String kohorte,
        double durchschnittFortschritt,
        List<SemesterStatistikDTO> semesterListe
) {
}