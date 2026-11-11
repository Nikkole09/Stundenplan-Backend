package htw.projekt.stundenplan.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Name: ProfessorCreateRequest
 *
 * Funktion:
 * Dieses DTO nimmt die Daten entgegen, die eine Administratorin
 * beim Erstellen eines neuen Professorenkontos eingibt.
 *
 * Es enthält den Namen und die E-Mail-Adresse des Professors.
 * Beide Felder werden auf gültige Eingaben geprüft.
 *
 * Die Rolle (Role_Prof) und der verpflichtende Passwortwechsel
 * (must_change_pw = true) werden später vom Backend festgelegt
 * und können nicht über dieses DTO verändert werden.
 * 
 * @author Nicole Eisner
 */

public class ProfessorCreateRequest {

    /** Name des Professors. */
    @NotBlank(message = "Der Name darf nicht leer sein.")
    private String name;

    /** E-Mail-Adresse des Professors. */
    @NotBlank(message = "Die E-Mail-Adresse darf nicht leer sein.")
    @Email(message = "Bitte eine gültige E-Mail-Adresse eingeben.")
    private String email;

    /**
     * Erstellt ein leeres ProfessorCreateRequest-Objekt.
     * Wird für die Verarbeitung von JSON-Daten benötigt.
     */
    public ProfessorCreateRequest() {
    }

    /** Gibt den Namen des Professors zurück. */
    public String getName() {
        return name;
    }

    /** Setzt den Namen des Professors. */
    public void setName(String name) {
        this.name = name;
    }

    /** Gibt die E-Mail-Adresse des Professors zurück. */
    public String getEmail() {
        return email;
    }

    /** Setzt die E-Mail-Adresse des Professors. */
    public void setEmail(String email) {
        this.email = email;
    }
}
