package htw.projekt.stundenplan.backend.dto;
import io.swagger.v3.oas.annotations.media.Schema;



// Beschreibt die Daten eines Moduls in Swagger.
@Schema(description = "Moduldaten für die Antwort")
public record ModulResponse(
        @Schema(description = "Eindeutige ID des Moduls", example = "1")
        Long id,

        @Schema(description = "Nummer des Moduls", example = "B11")
        String nummer,

        @Schema(description = "Name des Moduls", example = "Programmieren 1")
        String name,

        @Schema(description = "Vorgesehenes Semester", example = "1")
        Integer semester,

        @Schema(description = "P für Pflichtmodul, WP für Wahlpflichtmodul", example = "P")
        String modulTyp,

        @Schema(description = "Anzahl der ECTS-Punkte", example = "6")
        Integer ects
) {}




