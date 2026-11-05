package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity für ein Modul im Studium (z. B. "Programmieren 1")
 * Speichert alle wichtigen Infos und weiß genau, zu welcher 
 * Studienordnung es gehört
 * 
 * @author Khawla Yousef
 */
@Entity // Diese klasse wird von jpa als datenbanktabelle erkannt
@Table(name = "modul", uniqueConstraints = {
        // Gleiche Modulnummern sind in verschiedenen Studienordnungen erlaubt(z. B. "B34")
        // aber nicht zweimal in derselben Studienordnung
        @UniqueConstraint(
                name = "uk_modul_ordnung_nummer",
                columnNames = {"studienordnung_id", "nummer"}
        )})
public class Modul {

    @Id // Primärschlüssel eindeutige ID jeder Zeile
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Automatische ID als Nummer für jede Zeile in der Datenbank wir erstellt 
    private Long id;

    // Die Verbindung zur Studienordnung 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "studienordnung_id", nullable = false)
    private Studienordnung studienordnung; // Jedes Modul gehört zu genau einer Studienordnung

    @Column(nullable = false, length = 20)
    private String nummer; // Nummer des Moduls, z. B. B34

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name; // Vollständiger Name des Moduls

    @Column(nullable = false)
    private Integer semester; // In welchem Semester das Modul stattfindet zb. in 1 oder 2

    @Column(name = "modul_typ", nullable = false, length = 10)
    private String modulTyp; // Ob es ein Pflichtmodul (P) oder Wahlpflichtmodul (WP) ist

    @Column(nullable = false)
    private Integer ects; // ECTS-Punkte des Moduls

    // Liste der Unterteile dieses Moduls (z. B. Vorlesung, Übung)
    // Wenn das Modul gelöscht wird, werden auch seine Teile gelöscht (Cascade/OrphanRemoval)
    @OneToMany(mappedBy = "modul", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModulTeil> teile = new ArrayList<>(); // Ein Modul kann mehrere Teile haben

    protected Modul() { //Standard-Konstruktor für Java/JPA
        // Wird von JPA benötigt
    }

    /**
     * Ein Konstruktor mit Übergabeparametern, hiermit kann man im Code ganz bequem ein neues Modul erstellen 
     * und direkt alle wichtigen Werte wie Name, ects, Semester usw mitgeben
     */
    public Modul(Studienordnung studienordnung, String nummer, String name,
                 Integer semester, String modulTyp, Integer ects) {
        this.studienordnung = studienordnung;
        this.nummer = nummer;
        this.name = name;
        this.semester = semester;
        this.modulTyp = modulTyp;
        this.ects = ects;
    }

    // Getter-Methoden zum auslesen 
    public Long getId() { return id; }
    public Studienordnung getStudienordnung() { return studienordnung; }
    public String getNummer() { return nummer; }
    public String getName() { return name; }
    public Integer getSemester() { return semester; }
    public String getModulTyp() { return modulTyp; }
    public Integer getEcts() { return ects; }
    public List<ModulTeil> getTeile() { return teile; }

    // Setter-Methoden zum Ändern
    public void setStudienordnung(Studienordnung studienordnung) { this.studienordnung = studienordnung; }
    public void setNummer(String nummer) { this.nummer = nummer; }
    public void setName(String name) { this.name = name; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public void setModulTyp(String modulTyp) { this.modulTyp = modulTyp; }
    public void setEcts(Integer ects) { this.ects = ects; }
}