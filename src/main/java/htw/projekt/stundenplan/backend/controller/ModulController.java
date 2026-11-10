package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.ModulResponse;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
public class ModulController {

    private final ModulRepository modulRepository;
    private final StudienordnungRepository studienordnungRepository;

    // Spring stellt beide Repositories bereit.
    public ModulController(ModulRepository modulRepository,
                           StudienordnungRepository studienordnungRepository) {
        this.modulRepository = modulRepository;
        this.studienordnungRepository = studienordnungRepository;
    }

    @GetMapping("/api/stupo/{id}/modules")
    public ResponseEntity<List<ModulResponse>> getModules(@PathVariable("id") Long id) {
        // Gibt 404 zurück, wenn die Studienordnung nicht existiert.
        if (!studienordnungRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Lädt die Module der Studienordnung und bereitet die Antwort vor.
        List<ModulResponse> module = modulRepository.findByStudienordnungId(id)
                .stream()
                .map(modul -> new ModulResponse(
                        modul.getId(),
                        modul.getNummer(),
                        modul.getName(),
                        modul.getSemester(),
                        modul.getModulTyp(),
                        modul.getEcts()
                ))
                .toList();

        return ResponseEntity.ok(module);
    }

    // Spring ruft diese Methode auf, wenn die ID keine gültige ganze Zahl ist.
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleInvalidId() {
        ProblemDetail fehler = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Die Studienordnungs-ID muss eine ganze Zahl sein."
        );
        return ResponseEntity.badRequest().body(fehler);
    }
}