package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Lehrveranstaltung;
import org.springframework.data.jpa.repository.JpaRepository;

/*
- Dieses Repository ist für die Datenbank-Tabelle lehrveranstaltung zuständig.
- JpaRepository gibt uns fertige Funktionen: save(), findById(), findAll(), deleteById() usw.
 */
public interface LehrveranstaltungRepository extends JpaRepository<Lehrveranstaltung, Long>
{

}
