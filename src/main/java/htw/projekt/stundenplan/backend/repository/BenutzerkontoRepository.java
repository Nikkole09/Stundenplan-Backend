package htw.projekt.stundenplan.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;

// Repository für Speichern und Suchen
public interface BenutzerkontoRepository extends JpaRepository<Benutzerkonto, Long> {
    
Optional<Benutzerkonto> findByEmail(String email);

Optional<Benutzerkonto> findByEmailIgnoreCase(String email);
}
