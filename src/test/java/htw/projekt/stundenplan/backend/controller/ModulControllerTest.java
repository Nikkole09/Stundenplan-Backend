package htw.projekt.stundenplan.backend.controller;

import htw.projekt.stundenplan.backend.repository.ModulRepository;
import htw.projekt.stundenplan.backend.repository.StudienordnungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ModulControllerTest {

    private ModulRepository modulRepository;
    private StudienordnungRepository studienordnungRepository;
    private MockMvc mockMvc;

    @BeforeEach
    void vorbereiten() {
        modulRepository = mock(ModulRepository.class);
        studienordnungRepository = mock(StudienordnungRepository.class);

        mockMvc = MockMvcBuilders
                .standaloneSetup(new ModulController(modulRepository, studienordnungRepository))
                .build();
    }

    @Test
    void studienordnungOhneModuleGibtLeereListeZurueck() throws Exception {
        // Die Studienordnung 3 gibt es im Test, aber sie hat keine Module.
        when(studienordnungRepository.existsById(3L)).thenReturn(true);
        when(modulRepository.findByStudienordnungId(3L)).thenReturn(List.of());

        mockMvc.perform(get("/api/stupo/3/modules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
}