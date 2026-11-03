/*
 *
 *  *  NICHT MEHR VERWENDBAR
 *  *  Controller Modul wird angepasst und neu erstellt
 *
 */

/*
package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.entity.Modul;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModulController {

    private final ModulRepository modulRepository;

    public ModulController(ModulRepository modulRepository) {
        this.modulRepository = modulRepository;
    }

    @GetMapping
    public List<Modul> getAlleModule() {
        return modulRepository.findAll();
    }
}
*/
package htw.projekt.stundenplan.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Die Klasse nimmt Anfragen entgegen und gibt Daten zurück
@RestController
public class ModulController {

    //Wenn diese URL mit GET aufgerufen wird, startet die Methode.
    @GetMapping("/api/stupo/{id}/modules")
    public List<Object> getModules(@PathVariable("id") Long id) {
        // Die Datenbankabfrage wird später ergänzt.

        // Gibt erstmal eine leere Liste zurück.
        // Sobald die Tabellen fertig sind, wird hier die Datenbankabfrage ergänzt.
        return List.of();
    }
}

