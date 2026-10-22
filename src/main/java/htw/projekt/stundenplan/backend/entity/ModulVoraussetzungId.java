
package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ModulVoraussetzungId implements Serializable {

    @Column(name = "modul_id")
    private Long modulId;

    @Column(name = "voraussetzung_id")
    private Long voraussetzungId;

    public ModulVoraussetzungId() {
    }

    public ModulVoraussetzungId(Long modulId, Long voraussetzungId) {
        this.modulId = modulId;
        this.voraussetzungId = voraussetzungId;
    }

    public Long getModulId() {
        return modulId;
    }

    public void setModulId(Long modulId) {
        this.modulId = modulId;
    }

    public Long getVoraussetzungId() {
        return voraussetzungId;
    }

    public void setVoraussetzungId(Long voraussetzungId) {
        this.voraussetzungId = voraussetzungId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModulVoraussetzungId)) return false;
        ModulVoraussetzungId that = (ModulVoraussetzungId) o;
        return Objects.equals(modulId, that.modulId)
                && Objects.equals(voraussetzungId, that.voraussetzungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modulId, voraussetzungId);
    }
}
