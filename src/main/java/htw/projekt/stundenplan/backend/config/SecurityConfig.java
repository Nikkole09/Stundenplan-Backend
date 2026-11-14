package htw.projekt.stundenplan.backend.config;

import htw.projekt.stundenplan.backend.security.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Legt fest, welche Endpunkte ohne Token erreichbar sind und welche geschützt sind.
 * Meldet den JwtAuthenticationFilter an, der Token vor jedem Request prüft.
 *
 * @author Maeva Nguemezi
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    /**
     * PasswordEncoder = Wie Passwort gespeichert wird
     *
     * JETZT: NoOpPasswordEncoder = Klartext! Passwort bleibt lesbar in DB
     *
     * SPÄTER vor Abgabe: BCryptPasswordEncoder = Gehasht!
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //später zu return new BCryptPasswordEncoder() ändern damit es nicht mehr klartext ist;
    }

    /**
     * Verhindert eine zusätzliche Servlet-Registrierung des JWT-Filters.
     * Der Filter wird weiterhin über die Spring-Security-Filterkette ausgeführt.
     */
     @Bean
     public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(
        JwtAuthenticationFilter filter) {

    FilterRegistrationBean<JwtAuthenticationFilter> registration =
            new FilterRegistrationBean<>(filter);

    registration.setEnabled(false);

    return registration;
     }

    /**
     * SecurityFilterChain = Welche URLs dürfen ohne Token, welche nicht?
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable()) // "Please sign in" ausschalten!

                // HTTP-Statuscodes 401 und 403 unterscheiden
                .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint(
                 (request, response, authException) ->
                       response.sendError(401, "Unauthorized")
                      )
                .accessDeniedHandler(
                  (request, response, accessDeniedException) ->
                   response.sendError(403, "Forbidden")
                      )
                )

                .httpBasic(basic -> basic.disable()) // Popup ausschalten!
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/error").permitAll() // Fehlerbehandlung erlauben
                        .requestMatchers("/api/admin/professors").hasAuthority("ROLE_ADMIN")  // Nur Administratoren dürfen Professorenkonten erstellen
                        .anyRequest().authenticated()
                )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}