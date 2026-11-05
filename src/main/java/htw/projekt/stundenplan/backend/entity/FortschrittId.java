package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Zusammengesetzter Primärschlüssel für die Fortschritt-Entität
@Embeddable
public class FortschrittId implements Serializable {

    private Long benutzerId; // Benutzerkonto-ID
    private Long modulId; // Modul-ID

    protected FortschrittId() { // JPA benötigt diesen leeren Konstruktor
    }

    // Erstellt einen schlüssel aus Benutzer-Id und Modul-Id
    public FortschrittId(Long benutzerId, Long modulId) {
        this.benutzerId = benutzerId;
        this.modulId = modulId;
    }

    // Getter: Diese Methoden lesen Werte aus dem Objekt aus
    public Long getBenutzerId() {
        return benutzerId;
    }

    public Long getModulId() {
        return modulId;
    }

    // Zwei schlüssel sind gleich, wenn beide IDs gleich sind
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FortschrittId other)) return false;
        return Objects.equals(benutzerId, other.benutzerId) && Objects.equals(modulId, other.modulId);
    }

    // Erzeugt den Hash-Wert für den zusammengesetzten Schlüssel
    @Override
    public int hashCode() {
        return Objects.hash(benutzerId, modulId);
    }
}