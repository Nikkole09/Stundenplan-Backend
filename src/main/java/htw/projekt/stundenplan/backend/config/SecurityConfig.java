package htw.projekt.stundenplan.backend.config;

import htw.projekt.stundenplan.backend.security.JwtAuthenticationFilter;
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
                .httpBasic(basic -> basic.disable()) // Popup ausschalten!
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}