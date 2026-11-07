
package htw.projekt.stundenplan.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @NotBlank(message = "E-Mail darf nicht leer sein")
    @Email(message = "Ungültige E-Mail-Adresse")
    String email,

    @NotBlank(message = "Passwort darf nicht leer sein")
    String password

) {}
