
package htw.projekt.stundenplan.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import htw.projekt.stundenplan.backend.entity.Studienordnung;

public interface StudienordnungRepository
        extends JpaRepository<Studienordnung, Long> {
}
