package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WahlpflichtAuswahlId implements Serializable {
    private Long benutzerId; // Benutzerkonto-ID
    private Long slotModulId; // Modul-ID des Slots

    protected WahlpflichtAuswahlId() { // JPA benötigt diesen leeren Konstruktor
    }

    // Erstellt den Schlüssel aus den verbundenen IDs
    public WahlpflichtAuswahlId(Long benutzerId, Long slotModulId) {
        this.benutzerId = benutzerId;
        this.slotModulId = slotModulId;
    }

    public Long getBenutzerId() {
        return benutzerId;
    }

    public Long getSlotModulId() {
        return slotModulId;
    }

    // Zwei Schlüssel sind gleich, wenn beide IDs gleich sind
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WahlpflichtAuswahlId other)) return false;
        return Objects.equals(benutzerId, other.benutzerId) && Objects.equals(slotModulId, other.slotModulId);
    }

    // Erzeugt den Hash-Wert des zusammengesetzten Schlüssels
    @Override
    public int hashCode() {
        return Objects.hash(benutzerId, slotModulId);
    }

}
