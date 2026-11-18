package htw.projekt.stundenplan.backend.config;

import htw.projekt.stundenplan.backend.security.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Legt fest, welche Endpunkte ohne Token erreichbar sind und welche geschützt sind.
 * Meldet den JwtAuthenticationFilter an, der Token vor jedem Request prüft.
 * Erlaubt CORS für das Frontend, damit das Frontend mit dem Backend reden darf.
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
     * BCryptPasswordEncoder = Passwort wird gehasht gespeichert
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
     * Erlaubt dem Frontend mit dem Backend zu reden.
     * Ohne das blockt der Browser Registrierung und Login mit CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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
                        .requestMatchers("/registrierung").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/error").permitAll() // Fehlerbehandlung erlauben
                        .requestMatchers("/api/admin/professors").hasAuthority("ROLE_ADMIN")  // Nur Administratoren dürfen Professorenkonten erstellen
                        .anyRequest().authenticated()
                )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}