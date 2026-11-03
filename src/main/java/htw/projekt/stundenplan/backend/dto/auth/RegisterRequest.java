
package htw.projekt.stundenplan.backend.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank(message = "Name darf nicht leer sein")
    String name,

    @NotBlank(message = "E-Mail darf nicht leer sein")
    @Email(message = "Ungültige E-Mail-Adresse")
    String email,

    @NotBlank(message = "Passwort darf nicht leer sein")
    @Size(min = 8, message = "Mindestens 8 Zeichen")
    String password,

    @NotNull(message = "Studienordnung muss ausgewählt werden")
    @Positive
    @JsonProperty("studienordnung_id")
    Long studienordnungId

) {}
