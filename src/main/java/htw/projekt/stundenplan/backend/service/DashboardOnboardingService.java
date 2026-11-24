package htw.projekt.stundenplan.backend.service;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Speichert den Abschluss der ersten Dashboard-Aktion.
 *
 * @author Khawla Yousef
 */
@Service
public class DashboardOnboardingService {

    private final BenutzerkontoRepository benutzerkontoRepository;

    public DashboardOnboardingService(
            BenutzerkontoRepository benutzerkontoRepository) {
        this.benutzerkontoRepository = benutzerkontoRepository;
    }

    /**
     * Markiert das Onboarding als beendet.
     *
     * @param user Benutzerkonto der angemeldeten Person
     */

    //Markiert das Onboarding als erledigt. 
    //Setzt den Wert auf true und speichert den User in der Datenbank ab.
    @Transactional
    public void finishOnboarding(Benutzerkonto user) {
        user.setDashboardOnboardingDone(true);
        benutzerkontoRepository.save(user);
    }
}