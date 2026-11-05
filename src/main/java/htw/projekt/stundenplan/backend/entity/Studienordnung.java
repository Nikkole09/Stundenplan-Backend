package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity für eine Studien- und Prüfungsordnung des FIW-Studiums.
 * Bildet die Tabelle 'studienordnung' ab und speichert die Grunddaten
 * der StuPO 2024 oder der StuPO 2014.
 *
 * @author Khawla Yousef
 */

@Entity
@Table(name = "studienordnung")
public class Studienordnung {

    // Eindeutige ID als Primärschlüssel (wird automatisch hochgezählt)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Eindeutige Kennung der Studienordnung (z.B. "StuPO-2024"), darf nicht null sein
    @Column(name = "kennung", nullable = false, unique = true, length = 30)
    private String kennung;

    // Vollständiger Name der Studienordnung als Textfeld
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    // Standard-Regelstudienzeit in Semestern (Standardwert ist 6)
    @Column(name = "standardsemester", nullable = false)
    private Integer standardsemester = 6;

    // Gesamte ECTS-Punkte, die für den Abschluss benötigt werden (z.B. 180)
    @Column(name = "gesamt_ects", nullable = false)
    private Integer gesamtEcts = 180;

    // Verknüpfung zu allen Modulen, die zu dieser Studienordnung gehören (1-zu-viele)
    @OneToMany(mappedBy = "studienordnung")
    private List<Modul> module = new ArrayList<>();

    // Standard-Konstruktor (wichtig für JPA/Hibernate, daher protected)
    protected Studienordnung() {
        // Wird von JPA benötigt.
    }

    // Öffentlicher Konstruktor zum einfachen Erstellen neuer Studienordnungen im Code
    public Studienordnung(String kennung, String name,
                          Integer standardsemester, Integer gesamtEcts) {
        this.kennung = kennung;
        this.name = name;
        this.standardsemester = standardsemester;
        this.gesamtEcts = gesamtEcts;
    }

    // --- Getter-Methoden zum Auslesen der Werte ---
    public Long getId() { return id; }
    public String getKennung() { return kennung; }
    public String getName() { return name; }
    public Integer getStandardsemester() { return standardsemester; }
    public Integer getGesamtEcts() { return gesamtEcts; }
    public List<Modul> getModule() { return module; }

    // --- Setter-Methoden zum Ändern der Werte ---
    public void setKennung(String kennung) { this.kennung = kennung; }
    public void setName(String name) { this.name = name; }
    public void setStandardsemester(Integer standardsemester) { this.standardsemester = standardsemester; }
    public void setGesamtEcts(Integer gesamtEcts) { this.gesamtEcts = gesamtEcts; }
}