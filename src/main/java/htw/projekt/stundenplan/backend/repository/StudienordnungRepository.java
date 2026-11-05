package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Studienordnung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository-Schnittstelle für Datenbank-Operationen zur Studienordnung
 * Bietet standardmäßig CRUD-Operationen (Erstellen, Lesen, Aktualisieren, Löschen)
 */
public interface StudienordnungRepository 
        extends JpaRepository<Studienordnung, Long> {

    /**
     * Sucht eine Studienordnung anhand ihrer eindeutigen Kennung (z. B. "StuPO-2024").
     * Falls sie nicht existiert, gibt es keinen Fehler, 
     * sondern einfach ein leeres Ergebnis (aso gibt Optional zurück)
     */
    Optional<Studienordnung> findByKennung(String kennung);
}