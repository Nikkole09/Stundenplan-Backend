package htw.projekt.stundenplan.backend.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import htw.projekt.stundenplan.backend.dto.ProfessorCreateRequest;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.enums.Rolle;
import htw.projekt.stundenplan.backend.repository.BenutzerkontoRepository;
import htw.projekt.stundenplan.backend.util.PasswordGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Funktion:
 * Überprüft die Erstellung von Professorenkonten
 * und die sichere Speicherung des Initialpassworts.
 *
 * @author Nicole Eisner
 */
class ProfessorServiceImplTest {

    private BenutzerkontoRepository benutzerkontoRepository;
    private PasswordGenerator passwordGenerator;
    private PasswordEncoder passwordEncoder;
    private ProfessorServiceImpl professorService;

    // Festes Passwort ausschließlich für die Tests
    private static final String TESTPASSWORT = "Test1234!AbCdEf?";

    // Benötigte Komponenten vor jedem Test vorbereiten
    @BeforeEach
    void vorbereiten() {

        benutzerkontoRepository = mock(BenutzerkontoRepository.class);
        passwordGenerator = mock(PasswordGenerator.class);

        // Echten BCrypt-PasswordEncoder verwenden
        passwordEncoder = new BCryptPasswordEncoder();

        professorService = new ProfessorServiceImpl(
                benutzerkontoRepository,
                passwordGenerator,
                passwordEncoder
        );
    }

    // Erstellt eine Beispielanfrage für die Tests
    private ProfessorCreateRequest erstelleAnfrage() {

        ProfessorCreateRequest request = new ProfessorCreateRequest();

        request.setName("Test Professor");
        request.setEmail("professor@example.com");

        return request;
    }

    // Prüft die sichere Speicherung des Initialpassworts
    @Test
    void initialpasswortWirdSicherGehasht() {

        when(passwordGenerator.generatePassword())
                .thenReturn(TESTPASSWORT);

        when(benutzerkontoRepository.findByEmail("professor@example.com"))
                .thenReturn(Optional.empty());

        // Gespeichertes Konto simulieren
        Benutzerkonto gespeichert = mock(Benutzerkonto.class);

        when(gespeichert.getId()).thenReturn(1L);

        when(benutzerkontoRepository.save(any(Benutzerkonto.class)))
                .thenReturn(gespeichert);

        // Professorenkonto erstellen
        Long professorId = professorService.createProfessor(erstelleAnfrage());

        // Das zur Speicherung übergebene Konto abfangen
        ArgumentCaptor<Benutzerkonto> captor =
                ArgumentCaptor.forClass(Benutzerkonto.class);

        verify(benutzerkontoRepository).save(captor.capture());
        verify(passwordGenerator).generatePassword();

        Benutzerkonto professor = captor.getValue();

        // Prüfen, dass kein Klartextpasswort gespeichert wird
        assertNotEquals(TESTPASSWORT, professor.getPasswordHash());

        // Prüfen, ob der BCrypt-Hash zum Initialpasswort passt
        assertTrue(passwordEncoder.matches(
                TESTPASSWORT,
                professor.getPasswordHash()
        ));

        // Rolle und verpflichtenden Passwortwechsel überprüfen
        assertEquals(Rolle.ROLE_PROF, professor.getRolle());
        assertTrue(professor.getPasswortAendernPflicht());

        // Prüfen, dass die Konto-ID zurückgegeben wird
        assertEquals(1L, professorId);
    }

    // Prüft, dass bereits registrierte E-Mail-Adressen abgelehnt werden
    @Test
    void doppelteEmailVerhindertKontoerstellung() {

        Benutzerkonto vorhandenesKonto = mock(Benutzerkonto.class);

        when(benutzerkontoRepository.findByEmail("professor@example.com"))
                .thenReturn(Optional.of(vorhandenesKonto));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> professorService.createProfessor(erstelleAnfrage())
        );

        // Erwarteten HTTP-Status überprüfen
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());

        // Es darf kein Passwort generiert und kein Konto gespeichert werden
        verify(passwordGenerator, never()).generatePassword();
        verify(benutzerkontoRepository, never()).save(any());
    }
}