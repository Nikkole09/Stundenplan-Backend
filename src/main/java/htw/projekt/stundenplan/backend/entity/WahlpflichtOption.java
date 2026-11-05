/**
 * Entity für eine erlaubte Wahlpflicht-Option
 * Bildet die Tabelle "Wahlpflicht_Option" ab.
 *
 * @author : Israa Alnaddaf
 */

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Wahlpflicht_Option")
public class WahlpflichtOption {
    @EmbeddedId // Zusammengesetzter Primärschlüssel
    private WahlpflichtOptionId id; // Primärschlüssel der Klasse

    @MapsId ("slotModulId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumn(name = "slot_modul_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Modul slotModul;

    @MapsId("wahlModulId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumn(name = "wahl_modul_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Modul wahlModul;

    protected WahlpflichtOption() { // JPA benötigt diesen leeren Konstruktor
    }

    public WahlpflichtOption(Modul slotModul, Modul wahlModul) {
        this.slotModul = slotModul;
        this.wahlModul = wahlModul;
        this.id = new WahlpflichtOptionId(slotModul.getId(), wahlModul.getId());
    }

    // Getter: Diese Methoden lesen Werte aus dem Objekt aus
    public WahlpflichtOptionId getId() {
        return id;
    }
    public Modul getSlotModul() {
        return slotModul;
    }
    public Modul getWahlModul() {
        return wahlModul;
    }
}

