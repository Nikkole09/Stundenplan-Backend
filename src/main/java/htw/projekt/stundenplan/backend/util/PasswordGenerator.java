package htw.projekt.stundenplan.backend.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

/**
 * Funktion: Generiert sichere, zufällige Initialpasswörter für neue Benutzerkonten.
 *
 * @author Nicole Eisner
 */

@Component
public class PasswordGenerator {

    // Erlaubte Zeichen für das Initialpasswort
    private static final String GROSSBUCHSTABEN =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String KLEINBUCHSTABEN =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String ZAHLEN =
            "0123456789";

    private static final String SONDERZEICHEN =
            "!@#$%&*?";

    private static final String ALLE_ZEICHEN =
            GROSSBUCHSTABEN + KLEINBUCHSTABEN
            + ZAHLEN + SONDERZEICHEN;

    // Länge des generierten Initialpassworts
    private static final int PASSWORT_LAENGE = 16;

    // Sicherer Zufallsgenerator für Passwörter
    private final SecureRandom random = new SecureRandom();

    /**
     * Generiert ein zufälliges Initialpasswort.
     * Das Passwort enthält mindestens einen Großbuchstaben,
     * einen Kleinbuchstaben, eine Zahl und ein Sonderzeichen.
     *
     * @return Generiertes Initialpasswort
     */
    public String generatePassword() {

        String passwort;

        // Passwort generieren, bis alle Anforderungen erfüllt sind
        do {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < PASSWORT_LAENGE; i++) {
                int index = random.nextInt(ALLE_ZEICHEN.length());
                builder.append(ALLE_ZEICHEN.charAt(index));
            }

            passwort = builder.toString();

        } while (!passwort.matches(".*[A-Z].*")
                || !passwort.matches(".*[a-z].*")
                || !passwort.matches(".*[0-9].*")
                || !passwort.matches(".*[!@#$%&*?].*"));

        return passwort;
    }
}