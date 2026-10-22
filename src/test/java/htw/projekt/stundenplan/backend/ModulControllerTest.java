package htw.projekt.stundenplan.backend;
//Die Schnittstelle fürs Internet / Web (REST-Endpunkt).
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Startet den Web-Test-Server im Hintergrund
class ModulControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAlleModuleLiefertStatus200() throws Exception {
        mockMvc.perform(get("/api/modules"))// Simuliert den Aufruf im Browser
                .andExpect(status().isOk()); // Erwartet HTTP-Status 200 (Erfolg)
    }
}
