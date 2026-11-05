package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.ModulTeil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository für Modulteile. Hierüber greifen wir auf die Modulteil-Daten in der DB zu.
 */
public interface ModulTeilRepository extends JpaRepository<ModulTeil, Long> {
}