package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.dto.dashboard.DashboardResponse;
import htw.projekt.stundenplan.backend.dto.dashboard.DashboardUser;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.service.CurrentUserService;
import htw.projekt.stundenplan.backend.service.DashboardOnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    public DashboardController(
            CurrentUserService currentUserService,
            DashboardOnboardingService onboardingService) {
        this.currentUserService = currentUserService;
        this.onboardingService = onboardingService;
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

        return new DashboardResponse(
                dashboardUser,
                !user.isDashboardOnboardingDone(),
                null,
                null,
                null,
                List.of()
        );
    }

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