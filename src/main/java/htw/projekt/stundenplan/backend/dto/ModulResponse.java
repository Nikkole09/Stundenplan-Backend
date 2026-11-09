package htw.projekt.stundenplan.backend.dto;


// Hier stehen die Daten, die der Endpunkt für ein Modul zurückgibt.
public record ModulResponse(
            Long id,
            String nummer,
            String name,
            Integer semester,
            String modulTyp,
            Integer ects
    )
{

}


