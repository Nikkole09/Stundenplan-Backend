package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Modul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModulRepository extends JpaRepository<Modul, Long> {
}
