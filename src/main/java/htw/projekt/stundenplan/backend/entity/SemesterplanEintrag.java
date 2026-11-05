/**
* Entity für ein geplantes Modul im einem Semester.
* Bildet die Tabelle
*
*@author: Israa  Alnaddaf
*/

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "semesterplan_eintrag")
public class SemesterplanEintrag {

    @EmbeddedId // Zusammengesetzter Primärschlüssel
    private SemesterplanId id; // Primärschlüssel der Klasse

    @MapsId("benutzerId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele SemesterplanEinträge gehören zu einem Benutzer
    @JoinColumn(name = "benutzer_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Benutzerkonto benutzer;

    @MapsId("modulId") // Verbindet die Beziehung mit einem Teil des Schlüssels
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Viele SemesterplanEinträge gehören zu einem Modul
    @JoinColumn(name = "modul_id", nullable = false) // Fremdschlüssel zur anderen Tabelle
    private Modul modul;

    // Beschreibung der Datenbankspalten
    @Column(name = "semester_nr", nullable = false)
    private Integer semesterNr; // Semester-Nummer

    // Leerer Konstruktor für JPA
    public SemesterplanEintrag() {
    }

    public SemesterplanEintrag(Benutzerkonto benutzer, Modul modul, Integer semesterNr) {
        this.benutzer = benutzer;
        this.modul = modul;
        this.semesterNr = semesterNr;
        this.id = new SemesterplanId(benutzer.getId(), modul.getId()); // Setzt den zusammengesetzten Schlüssel
    }

    // Getter und Setter
    public SemesterplanId getId() {
        return id;
    }

    public Benutzerkonto getBenutzer() {
        return benutzer;
    }

    public Modul getModul() {
        return modul;
    }

    public Integer getSemesterNr() {
        return semesterNr;
    }

    public void setSemesterNr(Integer semesterNr) {
        this.semesterNr = semesterNr;
    }

}
