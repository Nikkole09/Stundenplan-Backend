
package htw.projekt.stundenplan.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;

public interface BenutzerkontoRepository
        extends JpaRepository<Benutzerkonto, Long> {

    Optional<Benutzerkonto> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}
