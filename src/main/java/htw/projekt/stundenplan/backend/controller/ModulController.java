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
