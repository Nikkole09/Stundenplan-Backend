package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Kombinierter Primärschlüssel für Modulvoraussetzungen.
 * Da eine Beziehung immer aus ZWEI Modulen besteht Modul + vorausgesetztes Modul,
 * benötigen wir diese ID-Klasse für die Datenbank.
 */
@Embeddable
public class ModulVoraussetzungId implements Serializable {

    private Long modulId;               
    private Long vorausgesetztesModulId; 

    protected ModulVoraussetzungId() {}

    public ModulVoraussetzungId(Long modulId, Long vorausgesetztesModulId) {
        this.modulId = modulId;
        this.vorausgesetztesModulId = vorausgesetztesModulId;
    }

    public Long getModulId() { return modulId; }
    public Long getVorausgesetztesModulId() { return vorausgesetztesModulId; }

    @Override
    // Java benötigt equals & hashCode für zusammengesetzte Schlüssel zum Vergleichen
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ModulVoraussetzungId other)) return false;
        return Objects.equals(modulId, other.modulId)
                && Objects.equals(vorausgesetztesModulId, other.vorausgesetztesModulId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modulId, vorausgesetztesModulId);
    }
}