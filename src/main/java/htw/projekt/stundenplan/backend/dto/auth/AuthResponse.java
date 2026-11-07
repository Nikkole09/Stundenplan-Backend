
package htw.projekt.stundenplan.backend.dto.auth;

import java.util.List;

public record AuthResponse(
    String token,
    String tokenType,
    List<String> roles
) {}
