
package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "benutzerkonto")
public class Benutzerkonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "email", nullable = false, unique = true,
            columnDefinition = "text")
    private String email;

    @Column(name = "password_hash", nullable = false,
            columnDefinition = "text")
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studienordnung_id")
    private Studienordnung studienordnung;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "must_change_pw")
    private Boolean mustChangePw;

    public Benutzerkonto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Studienordnung getStudienordnung() {
        return studienordnung;
    }

    public void setStudienordnung(Studienordnung studienordnung) {
        this.studienordnung = studienordnung;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getMustChangePw() {
        return mustChangePw;
    }

    public void setMustChangePw(Boolean mustChangePw) {
        this.mustChangePw = mustChangePw;
    }
}
