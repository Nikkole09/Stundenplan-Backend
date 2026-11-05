package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.SemesterplanEintrag;
import htw.projekt.stundenplan.backend.entity.SemesterplanId;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für Speichern und Suchen.
public interface SemesterplanEintragRepository
    extends JpaRepository<SemesterplanEintrag, SemesterplanId> {
}
