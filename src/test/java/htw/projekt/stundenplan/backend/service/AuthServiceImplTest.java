
package htw.projekt.stundenplan.backend.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.auth.RegisterRequest;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.Studienordnung;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;

class AuthServiceImplTest {

    private final BenutzerkontoRepository benutzerkontoRepository =
            mock(BenutzerkontoRepository.class);

    private final StudienordnungRepository studienordnungRepository =
            mock(StudienordnungRepository.class);

    private final AuthServiceImpl authService =
            new AuthServiceImpl(
                    benutzerkontoRepository,
                    studienordnungRepository
            );

    @Test
    void registrierungSpeichertBenutzerkonto() {

        RegisterRequest request = new RegisterRequest(
                "Max Mustermann",
                "MAX@example.com",
                "Test12345!",
                1L
        );

        Studienordnung studienordnung = new Studienordnung();

        when(studienordnungRepository.findById(1L))
                .thenReturn(Optional.of(studienordnung));

        when(benutzerkontoRepository.save(any(Benutzerkonto.class)))
                .thenAnswer(aufruf -> aufruf.getArgument(0));

        RegisterResponse response = authService.register(request);

        ArgumentCaptor<Benutzerkonto> captor =
                ArgumentCaptor.forClass(Benutzerkonto.class);

        verify(benutzerkontoRepository).save(captor.capture());

        Benutzerkonto benutzerkonto = captor.getValue();

        assertEquals("max@example.com", benutzerkonto.getEmail());
        assertEquals("ROLE_STUDENT", benutzerkonto.getRole());
        assertSame(studienordnung, benutzerkonto.getStudienordnung());

        assertTrue(
                new BCryptPasswordEncoder().matches(
                        "Test12345!",
                        benutzerkonto.getPasswordHash()
                )
        );

        assertEquals("max@example.com", response.email());
    }

    @Test
    void doppelteEmailWirdAbgelehnt() {

        RegisterRequest request = new RegisterRequest(
                "Max Mustermann",
                "max@example.com",
                "Test12345!",
                1L
        );

        when(benutzerkontoRepository.existsByEmailIgnoreCase(
                "max@example.com"
        )).thenReturn(true);

        ResponseStatusException fehler = assertThrows(
                ResponseStatusException.class,
                () -> authService.register(request)
        );

        assertEquals(HttpStatus.CONFLICT, fehler.getStatusCode());

        verify(benutzerkontoRepository, never())
                .save(any(Benutzerkonto.class));
    }

    @Test
    void unbekannteStudienordnungWirdAbgelehnt() {

        RegisterRequest request = new RegisterRequest(
                "Max Mustermann",
                "max@example.com",
                "Test12345!",
                999L
        );

        when(studienordnungRepository.findById(999L))
                .thenReturn(Optional.empty());

        ResponseStatusException fehler = assertThrows(
                ResponseStatusException.class,
                () -> authService.register(request)
        );

        assertEquals(HttpStatus.BAD_REQUEST, fehler.getStatusCode());

        verify(benutzerkontoRepository, never())
                .save(any(Benutzerkonto.class));
    }
}
