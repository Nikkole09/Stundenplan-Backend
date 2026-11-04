package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.ModulVoraussetzung;
import htw.projekt.stundenplan.backend.entity.ModulVoraussetzungId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository für Modulvoraussetzungen (CRUD-Operationen)
 * Nutzt den kombinierten Schlüssel (ModulVoraussetzungId), da die Tabelle 
 * aus zwei verknüpften Modul-IDs besteht
 */
public interface ModulVoraussetzungRepository
        extends JpaRepository<ModulVoraussetzung, ModulVoraussetzungId> {
}