
package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "modul_voraussetzung")
public class ModulVoraussetzung {

    // Zusammengesetzter Primärschlüssel
    @EmbeddedId
    private ModulVoraussetzungId id;

    // Modul, für das die Voraussetzung gilt
    @ManyToOne
    @MapsId("modulId")
    @JoinColumn(name = "modul_id")
    private Modul modul;

    // Modul, das als Voraussetzung dient
    @ManyToOne
    @MapsId("voraussetzungId")
    @JoinColumn(name = "voraussetzung_id")
    private Modul voraussetzung;

    // nv = notwendig, ev = empfohlen
    @Column(name = "typ")
    private String typ;

    public ModulVoraussetzung() {
    }

    public ModulVoraussetzungId getId() {
        return id;
    }

    public void setId(ModulVoraussetzungId id) {
        this.id = id;
    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    public Modul getVoraussetzung() {
        return voraussetzung;
    }

    public void setVoraussetzung(Modul voraussetzung) {
        this.voraussetzung = voraussetzung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
