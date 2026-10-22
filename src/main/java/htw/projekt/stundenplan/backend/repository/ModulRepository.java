package htw.projekt.stundenplan.backend.repository;
//holt die Module aus der DB
import htw.projekt.stundenplan.backend.entity.Modul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModulRepository extends JpaRepository<Modul, Long> {
    // durch JpaRepository<Modul, Long> stellt Spring funtionen automatisch wie zb findAll() bereit,
    //deswegen musste kein eigene  SQL-Abfrage für das Lesen aller Module geschrieben werden

}
