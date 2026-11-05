package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Zusammengestzter Schlüssel für diese Tabelle
@Embeddable
public class SemesterplanId implements Serializable {

    private long benutzerId;
    private long modulId;

    // Leerer Konstruktor für JPA
    public SemesterplanId() {
    }

    // Erstellt den Schlüssel aus den verbundenen IDs
    public SemesterplanId(long benutzerId, long modulId) {
        this.benutzerId = benutzerId;
        this.modulId = modulId;
    }

    // Getter für die IDs
    public long getBenutzerId() {
        return benutzerId;
    }

    public long getModulId() {
        return modulId;
    }

    // equals und hashCode
    // Zwei schlüssel sind gleich, wenn beide IDs gleich sind
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterplanId that = (SemesterplanId) o;
        return benutzerId == that.benutzerId && modulId == that.modulId;
    }

    // Erzeugt den Hash-Wert des Schlüssels
    @Override
    public int hashCode() {
        return Objects.hash(benutzerId, modulId);
    }
}
