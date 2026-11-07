package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
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

    // Registrierung eines Studierenden
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Anmeldung mit E-Mail und Passwort
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}
