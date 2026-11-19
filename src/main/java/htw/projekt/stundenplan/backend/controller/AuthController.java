
package htw.projekt.stundenplan.backend.controller;

import java.util.Map;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.dto.auth.PasswortResetRequest;
import htw.projekt.stundenplan.backend.dto.auth.NeuesPasswortRequest;
import htw.projekt.stundenplan.backend.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Registrierung
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }

    // Passwort-Reset anfordern
    @PostMapping("/passwort-zuruecksetzen")
    public ResponseEntity<Map<String, String>>
            passwortZuruecksetzen(
                    @RequestBody PasswortResetRequest request) {

        authService.passwortZuruecksetzen(request.email());

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Wenn ein Konto mit dieser E-Mail existiert, "
                        + "wurde eine Nachricht verschickt."
                )
        );
    }

    // Neues Passwort festlegen
    @PostMapping("/passwort-neu-setzen")
    public ResponseEntity<Map<String, String>>
            passwortNeuSetzen(
                    @RequestBody NeuesPasswortRequest request) {

        authService.passwortNeuSetzen(
                request.token(),
                request.neuesPasswort()
        );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Passwort wurde geändert."
                )
        );
    }
}
