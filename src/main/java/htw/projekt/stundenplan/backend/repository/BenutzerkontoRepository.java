package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository für Speichern und Suchen
public interface BenutzerkontoRepository extends JpaRepository<Benutzerkonto, Long> {
    Optional<Benutzerkonto> findByEmail(String email);
}
