package htw.projekt.stundenplan.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Prüft bei jedem Request, ob ein gültiges JWT-Token im Authorization-Header mitgeschickt wurde.
 * Wenn das Token gültig ist, wird der Nutzer als eingeloggat markiert, sodass geschützte Endpunkte aufgerufen werden können.
 * Ohne oder mit ungültigem Token bleibt der Zugriff verweigert.
 *
 * @author Maeva Nguemezi
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Erstellt den Filter mit dem JwtTokenProvider zur Prüfung von Token.
     *
     * @param jwtTokenProvider prüft Token und liest die E-Mail aus dem Token aus
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Wird bei jedem Request einmal aufgerufen.
     * Prüft den Token und loggt den Nutzer bei Erfolg ein.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtTokenProvider.validateToken(token)) {
                String email = jwtTokenProvider.getEmailFromToken(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("JWT authentication successful for: {}", email);
            } else {
                logger.warn("Invalid JWT token");
            }
        }

        filterChain.doFilter(request, response);
    }
}