package htw.projekt.stundenplan.backend.entity;
//Hier wird die Verbindung zwischen Java-Code und der Datenbank-Tabelle hergestellt.
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity    //Verbindet diese Java-Klasse mit der DB-Tabelle "Modul" 
@Table(name = "module") // Sagt: Geht exakt auf die Tabelle "module" in PostgreSQL 
public class Modul {

//Diese drei Felder entsprechen den wichtigen Spalten in der Datenbank.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Primärschlüssel, wird von DB automatisch hochgezählt
    private Long id;
//die entsprechen den spalten der Tabelle
    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String dozent;

//die Beziehung zur Tabelle Lehrveranstaltung, für mein user story ist sie nicht relevant (leer)
    @OneToMany(mappedBy = "modul", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lehrveranstaltung> lehrveranstaltungen = new ArrayList<>();

    public Modul() {}

    public Modul(String name, String dozent) {
        this.name = name;
        this.dozent = dozent;
    }

    public Long getId() { return id; } //gibt die id zurück, die in der DB gespeichert ist
    public void setId(Long id) { this.id = id; } //speichert einen neuen id  ab in der DB 

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDozent() { return dozent; }
    public void setDozent(String dozent) { this.dozent = dozent; }

    public List<Lehrveranstaltung> getLehrveranstaltungen() { return lehrveranstaltungen; }
    public void setLehrveranstaltungen(List<Lehrveranstaltung> lehrveranstaltungen) { this.lehrveranstaltungen = lehrveranstaltungen; }
}
