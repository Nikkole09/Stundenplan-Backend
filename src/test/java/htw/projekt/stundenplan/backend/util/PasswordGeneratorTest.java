package htw.projekt.stundenplan.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Funktion:
 * Überprüft die Generierung zufälliger Initialpasswörter
 * und die Einhaltung der Passwortanforderungen.
 *
 * @author Nicole Eisner
 */
class PasswordGeneratorTest {

    private final PasswordGenerator passwordGenerator =
            new PasswordGenerator();

    // Prüft die Länge des generierten Passworts
    @Test
    void passwortHat16Zeichen() {
        String passwort = passwordGenerator.generatePassword();

        assertEquals(16, passwort.length());
    }

    // Prüft die Anforderungen an das Initialpasswort
    @Test
    void passwortErfuelltAlleAnforderungen() {

        for (int i = 0; i < 100; i++) {
            String passwort = passwordGenerator.generatePassword();

            assertEquals(16, passwort.length());
            assertTrue(passwort.matches(".*[A-Z].*"));
            assertTrue(passwort.matches(".*[a-z].*"));
            assertTrue(passwort.matches(".*[0-9].*"));
            assertTrue(passwort.matches(".*[!@#$%&*?].*"));
            assertTrue(passwort.matches("[A-Za-z0-9!@#$%&*?]+"));
        }
    }

    // Prüft, ob unterschiedliche Passwörter generiert werden
    @Test
    void passwoerterSindUnterschiedlich() {
        String passwort1 = passwordGenerator.generatePassword();
        String passwort2 = passwordGenerator.generatePassword();

        assertNotEquals(passwort1, passwort2);
    }
}