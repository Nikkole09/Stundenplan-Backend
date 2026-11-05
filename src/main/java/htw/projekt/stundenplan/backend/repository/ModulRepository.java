package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 *  Repository für CRUD-Zugriff auf Module (unser Datenbank-Helfer)
 * kümmert sich also automatisch darum, dass wir Module speichern, 
 * abrufen, verändern oder löschen können nenen wir-> CRUD-Operationen)
 */
public interface ModulRepository extends JpaRepository<Modul, Long> {

    /**
     * Holt eine Liste mit allen Modulen heraus, die zu einer bestimmten Studienordnung gehören. 
     * Dafür übergeben wir einfach die ID der Studienordnung (z. B. von der StuPO 2024)
     */
    List<Modul> findByStudienordnungId(Long studienordnungId);
}