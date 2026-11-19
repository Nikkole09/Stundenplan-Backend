package htw.projekt.stundenplan.backend.dto.auth;

public record NeuesPasswortRequest(
        String token,
        String neuesPasswort
) {
}
