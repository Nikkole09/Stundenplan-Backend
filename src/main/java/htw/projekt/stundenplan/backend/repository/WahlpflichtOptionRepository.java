package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.WahlpflichtOption;
import htw.projekt.stundenplan.backend.entity.WahlpflichtOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für Speichern und Suchen
public interface WahlpflichtOptionRepository extends JpaRepository<WahlpflichtOption, WahlpflichtOptionId> {

}
