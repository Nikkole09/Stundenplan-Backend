package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lehrveranstaltung")
public class Lehrveranstaltung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "lehrform")
    private String lehrform;

    @Column(name = "sws")
    private int sws;


    @Column(name = "fachsemester")
    private Integer fachsemester;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modul_id", nullable = false)
    private Modul modul;


    @ManyToMany
    @JoinTable(
            name = "lehrveranstaltung_voraussetzung",
            joinColumns = @JoinColumn(name = "lehrveranstaltung_id"),
            inverseJoinColumns = @JoinColumn(name = "voraussetzung_id")
    )
    private Set<Lehrveranstaltung> voraussetzungen = new HashSet<>();

    public Lehrveranstaltung() {}

    public Lehrveranstaltung(String name, String lehrform, int sws, Integer fachsemester, Modul modul) {
        this.name = name;
        this.lehrform = lehrform;
        this.sws = sws;
        this.fachsemester = fachsemester;
        this.modul = modul;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLehrform() { return lehrform; }
    public void setLehrform(String lehrform) { this.lehrform = lehrform; }
    public int getSws() { return sws; }
    public void setSws(int sws) { this.sws = sws; }
    public Integer getFachsemester() { return fachsemester; }
    public void setFachsemester(Integer fachsemester) { this.fachsemester = fachsemester; }
    public Modul getModul() { return modul; }
    public void setModul(Modul modul) { this.modul = modul; }
    public Set<Lehrveranstaltung> getVoraussetzungen() { return voraussetzungen; }
    public void setVoraussetzungen(Set<Lehrveranstaltung> voraussetzungen) { this.voraussetzungen = voraussetzungen; }
}