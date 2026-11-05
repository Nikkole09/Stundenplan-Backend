/**
 * Entity für einen Teil eines Moduls.
 * Ein Modul kann aus mehreren teilen bestehen, zb. Vorlesung, Übung  usw.
 *
 * @author khawla Yousef
 */

package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity 
@Table(name = "modul_teil", uniqueConstraints = {
        // Gleiche Modulteil-Nummern sind in verschiedenen Modulen erlaubt,
        // aber nicht zweimal im selben Modul.
        @UniqueConstraint(
                name = "uk_modulteil_modul_nummer",
                columnNames = {"modul_id", "nummer"}
        )})
public class ModulTeil {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische ID für jede Zeile
    private Long id;

   // Verknüpfung zum zugehörigen Modul (viele Modulteile gehören zu genau einem Modul: Many-to-One) zb gehört VL/PÜ zu Programmieren 1
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modul_id", nullable = false)
    private Modul modul;

    @Column(nullable = false, length = 20)
    private String nummer; // Nummer des Modulteils, z. B. B34.1

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name; // Name des Modulteils (z. B. "Vorlesung Programmieren 1")

    @Column(name = "form", nullable = false, length = 30)
    private String form; // Lehrform, z. B. SL (Vorlesung), PÜ (Praktische Übung)

    @Column(name = "sws", nullable = false, precision = 4, scale = 1)
    private BigDecimal sws; // Semesterwochenstunden (z. B. 2.0)

    // Standard-Konstruktor für JPA (wird im Hintergrund benötigt)
    protected ModulTeil() {
    }

    /**
     * Konstruktor, dadurch erstellen wir direkt einen neuen Modulteil 
     * mit allen wichtigen Start-Werten im Code
     */
    public ModulTeil(Modul modul, String nummer, String name,
                     String form, BigDecimal sws) {
        this.modul = modul;
        this.nummer = nummer;
        this.name = name;
        this.form = form;
        this.sws = sws;
    }

    // Getter-Methoden zum Auslesen
    public Long getId() { return id; }
    public Modul getModul() { return modul; }
    public String getNummer() { return nummer; }
    public String getName() { return name; }
    public String getForm() { return form; }
    public BigDecimal getSws() { return sws; }
}