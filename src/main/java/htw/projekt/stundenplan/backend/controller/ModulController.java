package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.ModulResponse;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Diese Klasse nimmt Anfragen an das Backend entgegen.
@RestController
public class ModulController {

    // Über das Repository laden wir die Module aus der Datenbank.
    private final ModulRepository modulRepository;

    // Spring gibt dem Controller das Repository beim Start.
    public ModulController(ModulRepository modulRepository) {
        this.modulRepository = modulRepository;
    }

    // Diese Methode läuft, wenn jemand die URL mit GET aufruft.
    @GetMapping("/api/stupo/{id}/modules")
    public List<ModulResponse> getModules(@PathVariable("id") Long id) {
        // id kommt aus der URL. Bei /api/stupo/2/modules ist id zum Beispiel 2.
        // Wir laden damit nur die Module der Studienordnung 2.
        return modulRepository.findByStudienordnungId(id)
                .stream()
                // Für jedes gefundene Modul übernehmen wir die sechs Felder für die Antwort.
                .map(modul -> new ModulResponse(
                        modul.getId(),
                        modul.getNummer(),
                        modul.getName(),
                        modul.getSemester(),
                        modul.getModulTyp(),
                        modul.getEcts()
                ))
                // Wir sammeln die Antworten in einer Liste, die Spring als JSON ausgibt.
                .toList();
    }
}