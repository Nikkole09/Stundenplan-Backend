package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.StudienplanEintragRequest;
import htw.projekt.stundenplan.backend.entity.Lehrveranstaltung;
import htw.projekt.stundenplan.backend.entity.StudienplanEintrag;
import htw.projekt.stundenplan.backend.repository.LehrveranstaltungRepository;
import htw.projekt.stundenplan.backend.repository.StudienplanEintragRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//-----------------------------------
import java.util.List;


/*
Dieser controller verarbeitet die Anfragen
für den persönlichen Stundenplan.
*/
@RestController
@RequestMapping("/api/study-plan" )
public class StudienplanEintragController{

    // Diese Repositories speichert Studienplan Einträge in der Datenbank
    private final StudienplanEintragRepository
    studienplanEintragRepository;

    // Dieses Repository sucht Lehrveranstaltungen in der Datenbank
    private final LehrveranstaltungRepository
    lehrveranstaltungRepository;

    /*
    Spring übergibt die benötigten Repositories
    automatisch über den Konstruktor.
    */
    public StudienplanEintragController(
        StudienplanEintragRepository studienplanEintragRepository,
        LehrveranstaltungRepository lehrveranstaltungRepository
    )
    {
        this.studienplanEintragRepository = studienplanEintragRepository;
        this.lehrveranstaltungRepository = lehrveranstaltungRepository;
    }

    /*
    POST /api/study-plan
    Speichert einen ausgewählte Lehrveranstaltung für eine Studentin und ein Semester
    */
    @PostMapping
    public ResponseEntity<String> speichern(
        @RequestBody StudienplanEintragRequest request
    )
    {
        //Prüfen, ob die Email vorhanden ist
        if(request.getStudentEmail() == null || request.getStudentEmail().isEmpty())
        {
             return ResponseEntity
             .status(HttpStatus.BAD_REQUEST)
             .body("Die E-Mail der Studentin darf nicht leer sein.");
        }

        //Prüfen, ob eine Lehrveranstaltungs-Id vorhanden ist
        if(request.getLehrveranstaltungId() == null)
        {
            return ResponseEntity
            .badRequest()
            .body("Die Lehrveranstaltungs-Id darf nicht leer sein.");
        }

        //Prüfen, ob ein gültiges Semester vorhanden ist
        if(request.getSemester() == null || request.getSemester() < 1)
        {
            return ResponseEntity
            .badRequest()
            .body("Das Semester muss mind. 1 sein.");
        }

        //Lehrveranstaltung mit der gesendeten Id suchen.
        Lehrveranstaltung lehrveranstaltung = lehrveranstaltungRepository
        .findById(request.getLehrveranstaltungId())
        .orElse(null);

        //Wenn keine Lehrveranstaltung gefunden wurde
        if(lehrveranstaltung == null)
        {
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Die Lehrveranstaltung mit der Id " + request.getLehrveranstaltungId() + " wurde nicht gefunden.");
        }

        //Prüfen, ob die Auswahl bereits existiert
        boolean exists = studienplanEintragRepository
        .existsByStudentEmailAndLehrveranstaltung_IdAndSemester(
            request.getStudentEmail(),
            request.getLehrveranstaltungId(),
            request.getSemester()
        );

        if(exists)
        {
            return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body("Diese Auswahl wurde bereits gespeichert.");
        }

        //Neuen Studienplan Eintrag erstellen
        StudienplanEintrag eintrag = new StudienplanEintrag(
            request.getStudentEmail(),
            lehrveranstaltung,
            request.getSemester()
        );

        //Studienplan Eintrag in der Datenbank speichern
        StudienplanEintrag gespeichert = studienplanEintragRepository.save(eintrag);

        //Einfache Antwort zurückgeben, dass der Eintrag gespeichert wurde
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Die Lehrveranstaltung wurde gespeichert. "+ "Eintrags-ID: " + gespeichert.getId());
    }

    //-------------------------------------
    /*
    GET /api/study-plan/{studentEmail}
    Gibt alle gespeicherten Studienplan Einträge einer Studentin zurück
    */
   @GetMapping
   public List<StudienplanEintrag> abrufen(
    @RequestParam String studentEmail
   ){
    return studienplanEintragRepository.findByStudentEmail(studentEmail);
   }



}