/**
 * Entity für ein Benutzerkonto der Studienplan-Anwendung
 * Bilden die Tabelle "Benutzerkonto" ab.
 *
 * @author : Israa Alnaddaf
 */
package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import htw.projekt.stundenplan.backend.enums.Rolle;

@Entity
@Table(name = "Benutzerkonto")
public class Benutzerkonto {
    @Id // Eindeuiger Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Die Datenbank erzeugt die ID
    private Long id; // Primärschlüssel der Klasse

    // Beschreibung der Datenbanksplate
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column (nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column (name = "password_hash", nullable = false, columnDefinition = "TEXT")
    private String passwordHash;

    // Enum wird als Text gespeichert
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rolle rolle;

    @ManyToOne(fetch = FetchType.LAZY) // Viele Eintrage gehören zu einem Eintrag
    @JoinColumn(name = "studienordnung_id") // Fremdschlüssel zur anderen Tabelle
    private Studienordnung studienordnung;
    @Column(nullable = false)
    private boolean aktiv = true;

    @Column(name = "passwort_aendern_pflicht", nullable = false)
    private boolean passwortAendernPflicht = false;

    protected Benutzerkonto() { // JPA benötigt diesen leeren Konstruktor
    }

    public Benutzerkonto(String name, String email, String passwordHash, Rolle rolle, Studienordnung studienordnung) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rolle = rolle;
        this.studienordnung = studienordnung;
    }

    // Getter: Diese Methoden lesen Werte aus dem Objekt aus
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public Studienordnung getStudienordnung() {
        return studienordnung;
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public boolean getPasswortAendernPflicht() {
        return passwortAendernPflicht;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPasswortAendernPflicht(boolean passwortAendernPflicht) {
        this.passwortAendernPflicht = passwortAendernPflicht;
    }
}