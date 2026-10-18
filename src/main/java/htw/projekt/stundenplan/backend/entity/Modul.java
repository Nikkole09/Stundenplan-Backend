package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module")
public class Modul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String kuerzel;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String dozent;

    @Column
    private Integer lp;

    @Column
    private Integer fachsemester;

    @OneToMany(mappedBy = "modul", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lehrveranstaltung> lehrveranstaltungen = new ArrayList<>();

    public Modul() {}

    public Modul(String name, String dozent, String kuerzel,Integer lp, Integer fachsemester ) {
        this.name = name;
        this.dozent = dozent;
        this.kuerzel = kuerzel;
        this.lp = lp;
        this.fachsemester = fachsemester;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDozent() { return dozent; }
    public void setDozent(String dozent) { this.dozent = dozent; }

    public List<Lehrveranstaltung> getLehrveranstaltungen() { return lehrveranstaltungen; }
    public void setLehrveranstaltungen(List<Lehrveranstaltung> lehrveranstaltungen) { this.lehrveranstaltungen = lehrveranstaltungen; }

    public String getKuerzel() { return kuerzel; }
    public void setKuerzel(String kuerzel) { this.kuerzel = kuerzel; }

    public Integer getLp() { return lp; }
    public void setLp(Integer lp) { this.lp = lp; }

    public Integer getFachsemester() { return fachsemester; }
    public void setFachsemester(Integer fachsemester) { this.fachsemester = fachsemester; }

}
