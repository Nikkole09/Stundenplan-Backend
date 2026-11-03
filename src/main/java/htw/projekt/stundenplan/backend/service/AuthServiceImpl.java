
package htw.projekt.stundenplan.backend.service;

import org.springframework.stereotype.Service;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.LoginRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // TODO: Benutzerkonto in PostgreSQL speichern
        // TODO: Passwort mit BCrypt verschlüsseln
        // TODO: Studienordnung zuordnen

        throw new UnsupportedOperationException(
            "Registrierung noch nicht implementiert"
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // TODO: E-Mail und Passwort überprüfen
        // TODO: JWT-Token erstellen

        throw new UnsupportedOperationException(
            "Login noch nicht implementiert"
        );
    }
}
