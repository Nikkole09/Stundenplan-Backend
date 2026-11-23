package htw.projekt.stundenplan.backend.dto.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

class RegisterRequestTest {

    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void gueltigeRegistrierung() {
        RegisterRequest request = new RegisterRequest(
        "Max Mustermann",
        "max@example.com",
        "Test12345!",
        1L,
        "2024"
);

        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    void ungueltigeEmailWirdAbgelehnt() {
        RegisterRequest request = new RegisterRequest(
                "Max Mustermann",
                "ungueltige-email",
                "Test12345!",
                1L,
                "2024"
        );

        assertFalse(validator.validate(request).isEmpty());
    }

    @Test
    void zuKurzesPasswortWirdAbgelehnt() {
        RegisterRequest request = new RegisterRequest(
                "Max Mustermann",
                "max@example.com",
                "123",
                1L,
                "2024"
        );

        assertFalse(validator.validate(request).isEmpty());
    }
}
