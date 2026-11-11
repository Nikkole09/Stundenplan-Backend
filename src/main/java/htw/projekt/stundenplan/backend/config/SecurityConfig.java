
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
 * Konfiguriert die Sicherheit der Anwendung.
 *
 * Legt fest, welche Endpunkte öffentlich zugänglich sind
 * und welche bestimmte Benutzerberechtigungen benötigen.
 *
 * Registriert den JWT-Filter für die Authentifizierung
 * und schützt die administrativen Endpunkte.
 *
 * @author Maeva Nguemezi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Erstellt die Security-Konfiguration.
     *
     * @param jwtAuthenticationFilter Filter zur JWT-Prüfung
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Verwendet vorübergehend einen PasswordEncoder
     * ohne Verschlüsselung für Testkonten.
     *
     * Vor dem produktiven Einsatz muss ein sicherer
     * PasswordEncoder verwendet werden.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Verhindert, dass der JWT-Filter zusätzlich
     * als normaler Servlet-Filter registriert wird.
     *
     * Der Filter soll ausschließlich innerhalb
     * der Spring-Security-Filterkette laufen.
     *
     * @param filter JWT-Authentifizierungsfilter
     * @return deaktivierte Servlet-Registrierung
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
     * Definiert die Sicherheitsregeln für HTTP-Anfragen.
     *
     * Öffentliche Endpunkte:
     * - Authentifizierung
     * - Swagger-Dokumentation
     * - Fehlerbehandlung
     *
     * Geschützte Endpunkte:
     * - Admin-Verwaltung nur mit ROLE_ADMIN
     * - Alle weiteren Endpunkte nur mit Anmeldung
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception bei Konfigurationsfehlern
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .formLogin(form -> form.disable())

                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth

                        // Login und Registrierung erlauben
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // Fehlerbehandlung erlauben
                        .requestMatchers("/error")
                        .permitAll()

                        // Swagger-Dokumentation erlauben
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        )
                        .permitAll()

                        // Nur Administratorinnen erlauben
                        .requestMatchers("/api/admin/**")
                        .hasAuthority("ROLE_ADMIN")

                        // Alle anderen Endpunkte benötigen Anmeldung
                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
