
package htw.projekt.stundenplan.backend.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import htw.projekt.stundenplan.backend.dto.auth.AuthResponse;
import htw.projekt.stundenplan.backend.dto.auth.RegisterResponse;
import htw.projekt.stundenplan.backend.service.AuthService;

class AuthControllerTest {

    private AuthService authService;
    private MockMvc mockMvc;

    @BeforeEach
    void vorbereiten() {
        authService = mock(AuthService.class);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new AuthController(authService))
                .build();
    }

    @Test
    void registrierungGibtStatus201Zurueck() throws Exception {

        when(authService.register(any()))
                .thenReturn(new RegisterResponse(
                        1L,
                        "max@example.com",
                        "ROLE_STUDENT",
                        1L
                ));

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Max Mustermann",
                      "email": "max@example.com",
                      "password": "Test12345!",
                      "studienordnung_id": 1
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email")
                        .value("max@example.com"))
                .andExpect(jsonPath("$.role")
                        .value("ROLE_STUDENT"));
    }

    @Test
    void loginGibtStatus200Zurueck() throws Exception {

        when(authService.login(any()))
                .thenReturn(new AuthResponse(
                        "test-token",
                        "Bearer",
                        List.of("ROLE_STUDENT")
                ));

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "email": "max@example.com",
                      "password": "Test12345!"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token")
                        .value("test-token"));
    }
}
