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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
// Name und Beschreibung der Gruppe in Swagger.
@Tag(name = "Module", description = "Module einer Studienordnung abrufen")

public class ModulController {

    private final ModulRepository modulRepository;
    private final StudienordnungRepository studienordnungRepository;

    // Spring stellt beide Repositories bereit.
    public ModulController(ModulRepository modulRepository,
                           StudienordnungRepository studienordnungRepository) {
        this.modulRepository = modulRepository;
        this.studienordnungRepository = studienordnungRepository;
    }

    // Beschreibt den Endpunkt in Swagger.
    @Operation(
    summary = "Module einer Studienordnung abrufen",
    description = "Gibt die Module der angegebenen Studienordnung als JSON-Liste zurück."
    )

   // Beschreibt Statuscodes und Fehlerantworten in Swagger.
@ApiResponses(value = {
    @ApiResponse(
            responseCode = "200",
            description = "Module gefunden. Ohne Module wird eine leere Liste zurückgegeben."
    ),
    @ApiResponse(
            responseCode = "400",
            description = "Die Studienordnungs-ID muss eine ganze Zahl sein.",
            content = @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)
            )
    ),
    @ApiResponse(
            responseCode = "403",
            description = "Zugriff verweigert.",
            content = @Content
    ),
    @ApiResponse(
            responseCode = "404",
            description = "Die Studienordnung wurde nicht gefunden.",
            content = @Content
    )
})

    @GetMapping("/api/stupo/{id}/modules")
    public ResponseEntity<List<ModulResponse>> getModules(
        // Erklärt die ID in Swagger und zeigt 1 als Beispiel
        @Parameter(description = "ID der Studienordnung", example = "1")
        // Liest die ID aus der URL als ganze Zahl
        @PathVariable("id") Long id) {
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