package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import htw.projekt.stundenplan.backend.enums.Rolle;
import java.util.List;

// Repository für Speichern und Suchen
public interface BenutzerkontoRepository extends JpaRepository<Benutzerkonto, Long> {
    Optional<Benutzerkonto> findByEmail(String email);
    // Sucht alle Studierenden einer Kohorte.
    List<Benutzerkonto> findByStudienjahrgangAndRolle(
    String studienjahrgang, Rolle rolle);
}
