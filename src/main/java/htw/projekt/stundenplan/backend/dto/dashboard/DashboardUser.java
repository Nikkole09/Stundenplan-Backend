package htw.projekt.stundenplan.backend.dto.dashboard;


/**
 * Sichere Benutzerdaten für den Dashboard-Header
 * Dieses Paket schicken wir an das Frontend
 * Gibt bewusst keinen Passwort-Hash zurück
 *
 * @author Khawla Yousef
 */
public record DashboardUser(
        Long id,
        String name,
        String email,
        String profilbildUrl
) {
}