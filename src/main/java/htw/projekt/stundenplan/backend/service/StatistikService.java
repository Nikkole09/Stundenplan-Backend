package htw.projekt.stundenplan.backend.service;

import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.repository.FortschrittRepository;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import org.springframework.stereotype.Service;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.Fortschritt;
import java.util.List;

@Service
public class StatistikService {

    private final BenutzerkontoRepository benutzerkontoRepository;
    private final FortschrittRepository fortschrittRepository;
    private final ModulRepository modulRepository;

    // Spring übergibt die Repositories für Benutzerkonten, Fortschritte und Module.
    public StatistikService(
            BenutzerkontoRepository benutzerkontoRepository,
            FortschrittRepository fortschrittRepository,
            ModulRepository modulRepository) {

        this.benutzerkontoRepository = benutzerkontoRepository;
        this.fortschrittRepository = fortschrittRepository;
        this.modulRepository = modulRepository;
    }

    // Berechnet den Studienfortschritt der ganzen Kohorte in Prozent.
    private double berechneDurchschnitt(
    List<Benutzerkonto> studierende,
    List<Fortschritt> bestandeneFortschritte) {

long gesamtEcts = 0;
long bestandeneEcts = 0;

// Addiert die benötigten ECTS aller Studierenden.
for (Benutzerkonto person : studierende) {
    gesamtEcts += person.getStudienordnung().getGesamtEcts();
}

// Addiert die ECTS der bestandenen Module.
for (Fortschritt fortschritt : bestandeneFortschritte) {
    bestandeneEcts += fortschritt.getModul().getEcts();
}

// Verhindert eine Division durch null.
if (gesamtEcts == 0) {
    return 0;
}

return bestandeneEcts * 100.0 / gesamtEcts;
}
    }
