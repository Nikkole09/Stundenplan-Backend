package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.entity.Modul;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Macht die Klasse zur Web-Schnittstelle, (unter der URL /api/modules) die automatisch Java-Objekte in JSON umwandelt
@RequestMapping("/api/modules") //legt die Basisadresse fest des Contollers 
public class ModulController {

    private final ModulRepository modulRepository;

    public ModulController(ModulRepository modulRepository) {
        this.modulRepository = modulRepository; // hier wird das Repository automatisch eingebunden
    }

    @GetMapping //Reagiert auf HTTP-GET-Anfragen an /api/modules
    public List<Modul> getAlleModule() {
        return modulRepository.findAll();// Holt alle Module aus der DB und gibt sie als JSON Liste zurück
    }
}
