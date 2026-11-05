package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Fortschritt;
import htw.projekt.stundenplan.backend.entity.FortschrittId;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für Speichern und Suchen.
public interface FortschrittRepository
        extends JpaRepository<Fortschritt, FortschrittId> {
}