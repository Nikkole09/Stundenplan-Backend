/*
 *
 *  *  NICHT MEHR VERWENDBAR *
 *
 */

/*
package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.StudienplanEintrag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Dieses Repository ist für die Datenbank-Tabelle
studienplan_eintrag zuständig.
 * /

public interface StudienplanEintragRepository

/*
JpaRepository gibt uns fertige Funktionen: save(), findById(), findAll(), deleteById() usw.
StudienplanEintrag = Entity, die gespeichert wird.
Long = Datentyp der Id.
 * /

extends JpaRepository<StudienplanEintrag, Long>
{
    // Gibt alle gespeicherten Studienplan-Einträgeeiner Studentin zurück
    List<StudienplanEintrag> findByStudentEmail(String studentEmail);

    /*
    Prüft, ob eine Studentin dieselbe Lehrveranstaltung bereits im gleichen Semester ausgewählt hat.
    So können doppelte Einträge verhindert werden.
     * /
    boolean existsByStudentEmailAndLehrveranstaltung_IdAndSemester
    (
        String studentEmail,
        Long lehrveranstaltungId,
        Integer semester
    );
}
*/
