package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "module")
public class Modul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String dozent;

    public Modul() {}

    public Modul(String name, String dozent) {
        this.name = name;
        this.dozent = dozent;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDozent() { return dozent; }
    public void setDozent(String dozent) { this.dozent = dozent; }
}
