
package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.ProfessorCreateRequest;
import htw.projekt.stundenplan.backend.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller für administrative Funktionen.
 *
 * Ermöglicht Administratorinnen das Anlegen
 * neuer Professorenkonten.
 *
 * @author Nicole Eisner
 */
@RestController
@RequestMapping("/api/admin")
@Tag(
    name = "Admin-Verwaltung",
    description = "Endpunkte zur Verwaltung durch Administratorinnen"
)
public class AdminController {

    private final ProfessorService professorService;

    public AdminController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * Erstellt ein neues Professorenkonto.
     *
     * @param request Name und E-Mail des Professors
     * @return ID des neu erstellten Kontos
     */
    @Operation(
        summary = "Professorenkonto erstellen",
        description = "Erstellt ein neues Professorenkonto "
            + "mit der Rolle ROLE_PROF. "
            + "Beim ersten Login muss das Passwort geändert werden. "
            + "Nur Administratorinnen dürfen diesen Endpunkt aufrufen."
    )
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Professorenkonto erfolgreich erstellt"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Ungültige Eingabedaten"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Nicht authentifiziert"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Keine Administratorberechtigung"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "E-Mail-Adresse bereits registriert"
        )
    })
    @PostMapping("/professors")
    public ResponseEntity<Long> createProfessor(
            @Valid @RequestBody ProfessorCreateRequest request) {

        Long professorId = professorService.createProfessor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorId);
    }
}
