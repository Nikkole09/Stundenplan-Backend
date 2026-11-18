package htw.projekt.stundenplan.backend.dto.dashboard; 

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Ermittelt das Benutzerkonto aus der aktuellen JWT-Authentifizierung.
 * Im JwtAuthenticationFilter ist der Benutzername die E-Mail-Adresse.
 *
 * @author Khawla Yousef
 */
@Service
public class CurrentUserService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    
    //Wir holen uns das Repository, um Daten aus der DB zu suchen
    public CurrentUserService(BenutzerkontoRepository benutzerkontoRepository) {
        this.benutzerkontoRepository = benutzerkontoRepository;
    }

    /**
     * Sucht die angemeldete Benutzerin anhand von authentication.getName().
     *
     * @param authentication aktuelle Spring-Security-Authentifizierung
     * @return Benutzerkonto der angemeldeten Person
     */
    public Benutzerkonto requireUser(Authentication authentication) {
        // Prüfen, ob überhaupt eine Anmeldung da ist
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Benutzer ist nicht angemeldet"
            );
        }

        return benutzerkontoRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Benutzerkonto nicht gefunden"
                ));
    }
}