package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Zusammengesetzter Schlüssel für die WahlpflichtOption-Entität
@Embeddable
public class WahlpflichtOptionId implements Serializable {

    private Long slotModulId;
    private Long wahlModulId;

    public WahlpflichtOptionId() { // JPA benötigt diesen leeren Konstruktor
    }

    // Erstellt einen Schlüssel aus den verbundenen IDs
    public WahlpflichtOptionId(Long slotModulId, Long wahlModulId) {
        this.slotModulId = slotModulId;
        this.wahlModulId = wahlModulId;
    }

    public Long getSlotModulId() {
        return slotModulId;
    }
    public Long getWahlModulId() {
        return wahlModulId;
    }

    // Zwei Schlüssel sind gleich, wenn beide IDs gleich sind
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WahlpflichtOptionId other)) return false;
        return Objects.equals(slotModulId, other.slotModulId)
        && Objects.equals(wahlModulId, other.wahlModulId);
    }

    // Erzeugt den Hash-Wert des Schlüssel
    @Override
    public int hashCode() {
        return Objects.hash(slotModulId, wahlModulId);
    }
}