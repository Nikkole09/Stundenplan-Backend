/**
 * Entity für die konkrete Wahl eines Wahlpflichtmoduls.
 * Bildet die Tabelle "Wahlpflicht_auswahl" ab.
 *
 * @author : Israa Alnaddaf
 */

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Wahlpflicht_auswahl")
public class WahlpflichtAuswahl {
    @EmbeddedId // Zusammengesetzter Primärschlüssel
    private WahlpflichtAuswahlId id; // Primärschlüssel der Klasse

    @MapsId ("benutzerId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge
    @JoinColumn(name = "benutzer_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Benutzerkonto benutzer;

    @MapsId("slotModulId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge
    @JoinColumn(name = "slot_modul_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Modul slotModul;

    @ManyToOne (fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumn(name = "wahl_modul_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Modul wahlModul;

    // Nur eine vorher in WahlpflichtOption erlaubte Kombination darf ausgewählt werden
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumns(value = {
        @JoinColumn(name = "slot_modul_id", referencedColumnName = "slot_modul_id",
        insertable = false, updatable = false), // Fremdschlüssel zur anderen Tabelle
        @JoinColumn(name = "wahl_modul_id", referencedColumnName = "wahl_modul_id",
        insertable = false, updatable = false)
        }, foreignKey = @ForeignKey (name = "fk_auswahl_gueltige_option"))
    private WahlpflichtOption gueltigeOption;

    protected WahlpflichtAuswahl() { // JPA benötigt diesen leeren Konstruktor
    }

    public WahlpflichtAuswahl(Benutzerkonto benutzer, Modul slotModul, Modul wahlModul) {
        this.benutzer = benutzer;
        this.slotModul = slotModul;
        this.wahlModul = wahlModul;
        this.id = new WahlpflichtAuswahlId(benutzer.getId(), slotModul.getId());
    }

    // Getter: Diese Methoden lesen Werte aus dem Objekt aus
    public WahlpflichtAuswahlId getId()
    {
        return id;
    }

    public Benutzerkonto getBenutzer() {
        return benutzer;
    }

    public Modul getSlotModul() {
        return slotModul;
    }

    public Modul getWahlModul() {
        return wahlModul;
    }
    public WahlpflichtOption getErlaubteOption() {
        return gueltigeOption;
    }
}
