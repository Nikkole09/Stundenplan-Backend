package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.WahlpflichtAuswahl;
import htw.projekt.stundenplan.backend.entity.WahlpflichtAuswahlId;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository für Speichern und Suchen.
public interface WahlpflichtAuswahlRepository
        extends JpaRepository<WahlpflichtAuswahl, WahlpflichtAuswahlId> {
}