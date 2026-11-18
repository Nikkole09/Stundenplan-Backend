/**
 * Sichere API-Daten eines Moduls für das Dashboard.
 * @author Israa Alnaddaf
*/
package htw.projekt.stundenplan.backend.dto.dashboard;

import java.math.BigDecimal;

public record DashboardModule(Long id, String nummer, String name,
    Integer ects, Integer semester, String status, BigDecimal note) {
}
