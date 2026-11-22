package htw.projekt.stundenplan.backend.dto;

// Enthält die Statistik für ein Semester.
public record SemesterStatistikDTO(
        Integer semester,
        double fortschritt,
        double bestandeneKurse,
        long personen
) {
}