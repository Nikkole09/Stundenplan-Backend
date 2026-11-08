/**
 * Entity für eine Voraussetzung zwischen zwei Modulen.
 * Bildet die Tabelle 'modul_voraussetzung' ab und unterscheidet NV und EV.
 *
 * @author Khawla Yousef
 */

package htw.projekt.stundenplan.backend.entity;

import htw.projekt.stundenplan.backend.enums.VoraussetzungTyp;
import jakarta.persistence.*;

@Entity
@Table(name = "modul_voraussetzung")
public class ModulVoraussetzung {

    @EmbeddedId
    private ModulVoraussetzungId id; // Zusammengesetzter Schlüssel aus zwei Modul-IDs.

    // Das Modul, das eine Voraussetzung besitzt.
    @MapsId("modulId") // Verbindet den Schlüsselteil modulId mit dieser Beziehung.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modul_id", nullable = false)
    private Modul modul; // Das Modul, das diese Voraussetzung besitzt.

    // Das Modul, das vorher besucht werden muss oder empfohlen wird.
    @MapsId("vorausgesetztesModulId") // Verbindet den zweiten Schlüsselteil mit der Beziehung.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vorausgesetztes_modul_id", nullable = false)
    private Modul vorausgesetztesModul;

    @Enumerated(EnumType.STRING)
    @Column(name = "voraussetzung_typ", nullable = false, length = 2)
    private VoraussetzungTyp voraussetzungTyp; // NV = notwendig, EV = empfohlen.

    protected ModulVoraussetzung() {
        // Wird von JPA benötigt.
    }

    public ModulVoraussetzung(Modul modul, Modul vorausgesetztesModul,
                              VoraussetzungTyp voraussetzungTyp) {
        this.modul = modul;
        this.vorausgesetztesModul = vorausgesetztesModul;
        this.voraussetzungTyp = voraussetzungTyp;
        // Beide Modul-IDs bilden zusammen den Primärschlüssel.
        this.id = new ModulVoraussetzungId(modul.getId(),
                                           vorausgesetztesModul.getId());
    }

    public ModulVoraussetzungId getId() { return id; }
    public Modul getModul() { return modul; }
    public Modul getVorausgesetztesModul() { return vorausgesetztesModul; }

}