package htw.projekt.stundenplan.backend.dto.dashboard;

import java.math.BigDecimal;
import java.util.List;

/**
 * Gesamte Antwort der Dashboard-API.
 * Es enthält alles, was die Startseite der Studentin auf einmal anzeigen muss
 * 
 * @author Khawla Yousef
 */
public record DashboardResponse(
        DashboardUser user,
        boolean showWelcomeBox, 
        Integer completedEcts,
        Integer totalEcts,
        BigDecimal weightedAverage,
        List<DashboardSemester> semesters
) {
}