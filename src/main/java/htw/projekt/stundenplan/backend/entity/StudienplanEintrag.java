/*
 *
 *  *  NICHT MEHR VERWENDBAR. *
 *  *  Entity StudienplanEintrag wurde geloescht in pgAdmin. *
 *
 */

/*
/**
 * Die Klasse StudienplanEintrag repräsentiert einen Eintrag in einem Studienplan.
 *
 * Beispiel:
 * test@htw-berlin.de wählt Lehrveranstelung 1
 * für das persönliche Semester 3 aus.
 * /

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

/*
Die Klasse StudienplanEintrag repräsentiert einen Eintrag in einem Studienplan.

Beispiel:
test@htw-berlin.de wählt Lehrveranstelung 1
für das persönliche Semester 3 aus.
 * /

@Entity

@Table(
    name = "studienplan_eintrag", //Der Name der Tabelle in der Datenbank

    /*
    Diese Kombination darf nicht doopelt vorkommen:

    dieselber Studentin
    + dieselbe Lehrveranstaltung
    + dasselbe Semester
     * /

    uniqueConstraints = {
        @UniqueConstraint (
            columnNames = {
                "student_email",
                "lehrveranstaltung_id",
                 "semester"
            }
        )
    }
)

public class StudienplanEintrag {

    /*
    - Eindueige Id für jeder Stundenplan Eintrag
    - Die Id wird autumatisch verggeben von die Datenbank
     * /

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Voläufige Identifikation der Studentin.

    Die Login Funktion gibt es noch nicht.
    Deshalb benutzen wir für die ersten Sprint eine Test-Email

    Später wird diese Feld durch eine User-Beziehung mit
    der Login-Funktion ersetzt werden
     * /

    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    /*
    Eine ausgewählte Lehrveranstaltung.

    Mehrere Stundenplan Einträge können dieselbe Lehrveranstaltung verwenden,
    z.b für verschiedene Studentinnen oder für verschiedene Semester.
     * /

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lehrveranstaltung_id", nullable = false)
    private Lehrveranstaltung lehrveranstaltung;

    /*
    Das persönliche Semester, für das die Studentin
    die Lehrveranstaltung eingeplant hat
     * /
    @Column(nullable = false)
    private Integer semester;

    /*
    Leerer Konstruktor.
    JPA braucht diesen Konstruktor, um objekte aus der Datenbank zu erstellen
     * /
    public StudienplanEintrag(){}

    //Konstruktor zum Erstellen eines neue Studienplan Eintrags
    public StudienplanEintrag(
        String studentEmail,
        Lehrveranstaltung lehrveranstaltung,
        Integer semester
    )
    {
        this.studentEmail = studentEmail;
        this.lehrveranstaltung = lehrveranstaltung;
        this.semester = semester;
    }

    // Gibt die Id des Eintrags zurük
    public Long getId()
    {
        return id;
    }

    // Setzt die Id des Eintrags
    public void setId(Long id)
    {
        this.id =id;
    }

    // Gibt die Test Email der Studentin zurück
    public String getStudentEmail()
    {
        return studentEmail;
    }

    // Setzt die Test email der Studentin
    public void setStudentEmail(String studentEmail)
    {
        this.studentEmail = studentEmail;
    }

    // Gibt die ausgewählte Lehrveranstaltung zurück
    public Lehrveranstaltung getLehrveranstaltung()
    {
        return lehrveranstaltung;
    }

    // Setzt die ausgewählte Lehrveranstaltung
    public void setLehrveranstaltung(Lehrveranstaltung lehrveranstaltung) {
        this.lehrveranstaltung = lehrveranstaltung;
    }

    // Gibt das persönliche Semester zurück
    public Integer getSemester()
    {
        return semester;
    }

    // Setzt das persönliche Semester
    public void setSemester(Integer semester)
    {
        this.semester = semester;
    }

}
*/