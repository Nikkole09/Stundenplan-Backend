/**
 * API-Daten eines Semesters mit seinen Module.
 *
 * @author Israa Alnaddaf
*/
package htw.projekt.stundenplan.backend.dto.dashboard;

import java.util.List;

public record DashboardSemester(int semester, List<DashboardModule> modules) {
}
