package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.dashboard.DashboardResponse;
import htw.projekt.stundenplan.backend.dto.dashboard.DashboardUser;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.service.CurrentUserService;
import htw.projekt.stundenplan.backend.service.DashboardOnboardingService;
import htw.projekt.stundenplan.backend.service.DashboardService;
import htw.projekt.stundenplan.backend.dto.dashboard.DashboardModule;
import htw.projekt.stundenplan.backend.dto.dashboard.DashboardSemester;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.math.BigDecimal;

/**
 * REST-Endpunkte für die persönliche Dashboard-Übersicht.
 *
 * @author Khawla Yousef
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final CurrentUserService currentUserService;
    private final DashboardOnboardingService onboardingService;
    private final DashboardService dashboardService;

    public DashboardController(
            CurrentUserService currentUserService,
            DashboardOnboardingService onboardingService, DashboardService dashboardService) {
        this.currentUserService = currentUserService;
        this.onboardingService = onboardingService;
        this.dashboardService = dashboardService;
    }

    /**
     * GET-Endpunkt: Lädt die Startseite.
     * Schickt die Profildaten und den Status für die Willkommens-Box an das Frontend.
     */
    @GetMapping
    public DashboardResponse getDashboard(Authentication authentication) {
        Benutzerkonto user = currentUserService.requireUser(authentication);

        DashboardUser dashboardUser = new DashboardUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        );

        List<DashboardModule> modules = dashboardService.loadModules(user);  // Module der Studienordnung und Fortschritte laden
        List<DashboardSemester> semesters = dashboardService.groupBySemester(modules); // Vorhandene Module nach Semester gruppieren

        // LP aus den echten Modulen berechnen.
        int completedEcts = dashboardService.calculateCompletedEcts(
                modules
        );

        // Das Studienziel stammt aus der Studienordnung.
        Integer totalEcts = user.getStudienordnung().getGesamtEcts();

        // Notendurchschnitt anhand der bestandenen Module berechnen.
        BigDecimal weightedAverage =
                dashboardService.calculateWeightedAverage(modules);
    }

     // Dashboard-Antwort mit echten Moduldaten zurückgeben (davor waren sie auf null)
        return new DashboardResponse(
            dashboardUser,
            !user.isDashboardOnboardingDone(),
            completedEcts,
            totalEcts,
            weightedAverage,
            semesters
        );

    /**
     * PATCH-Endpunkt: Wird aufgerufen, wenn die Studentin die Willkommens-Box wegdrückt.
     * Speichert ab, dass das Onboarding erledigt ist.
     */
    @PatchMapping("/onboarding")
    public ResponseEntity<Void> finishOnboarding(
            Authentication authentication) {
        Benutzerkonto user = currentUserService.requireUser(authentication);
        onboardingService.finishOnboarding(user);// Auf "erledigt" setzen
        return ResponseEntity.noContent().build();
    }
}