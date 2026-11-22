package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Fortschritt;
import htw.projekt.stundenplan.backend.entity.FortschrittId;
import org.springframework.data.jpa.repository.JpaRepository;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.enums.FortschrittStatus;
import java.util.List;

// Repository für Speichern und Suchen.
public interface FortschrittRepository
        extends JpaRepository<Fortschritt, FortschrittId> {
// Sucht die Lernfortschritte der ausgewählten Personen nach Status.        
List<Fortschritt> findByBenutzerInAndStatus(
        List<Benutzerkonto> benutzer,
        FortschrittStatus status);
}