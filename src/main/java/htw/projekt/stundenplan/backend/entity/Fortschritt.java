/**
* Entity für den Lernfortschritt eines Studenten in einem Modul.
* Bildet die Tabelle "Fortschritt" ab.
*
* @autor : Israa Alnaddaf
*/

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import htw.projekt.stundenplan.backend.enums.FortschrittStatus;

@Entity
@Table(name = "Fortschritt")
public class Fortschritt {

    @EmbeddedId  // Zusammengesetzter Primärschlüssel
    private FortschrittId id; // Primärschlüssel der Klasse

    @MapsId ("BenutzerId") // Verbindet die Beziehung mit einem Teil des schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumn(name = "benutzer_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Benutzerkonto benutzer;

    // Verbindet die Beziehung mit einem Teil des Schlüssels
    @MapsId("ModulId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele Einträge gehören zu einem Eintrag
    @JoinColumn(name = "modul_id", nullable = false)
    private Modul modul;

    // Enum wird als Text gespeichert
    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 20)
    private FortschrittStatus status = FortschrittStatus.OFFEN;

    // Beschreibung der Datenbankspalte
    @Column (precision = 4, scale = 1)
    private BigDecimal note;

    protected Fortschritt() { // JPA benötigt diesen leeren Konstruktor
    }

    public Fortschritt(Benutzerkonto benutzer, Modul modul) {
        this.benutzer = benutzer;
        this.modul = modul;
        this.id = new FortschrittId(benutzer.getId(), modul.getId());
    }

    // Getter: Diese Methoden lesen Werte aus dem Objekt aus
    public FortschrittId getId() {
        return id;
    }

    public Benutzerkonto getBenutzer() {
        return benutzer;
    }

    public Modul getModul() {
        return modul;
    }

    public FortschrittStatus getStatus() {
        return status;
    }

    public BigDecimal getNote() {
        return note;
    }

    // Setter: Diese Methoden schreiben Werte in das Objekt
    public void setStatus(FortschrittStatus status) {
        this.status = status;
    }
    public void setNote(BigDecimal note) {
        this.note = note;
    }
}
