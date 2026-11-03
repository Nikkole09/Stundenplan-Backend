package htw.projekt.stundenplan.backend.dto.auth;

public record RegisterResponse(
    Long id,
    String email,
    String role,
    Long studienordnungId
) {}
