package proyectoPagina.proyectoWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import proyectoPagina.proyectoWeb.controllers.MensajeController;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.services.MensajeService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MensajeController.class)
public class MensajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MensajeService mensajeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void cuandoUsuarioAutenticado_getBandeja_retorna200() throws Exception {
        when(mensajeService.bandejaEntrada("user")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/mensajes/bandeja-entrada")).andExpect(status().isOk());
    }

    @Test
    public void cuandoNoAutenticado_getBandeja_retorna401() throws Exception {
        mockMvc.perform(get("/api/mensajes/bandeja-entrada")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void cuandoPostVacio_retorna400() throws Exception {
        mockMvc.perform(post("/api/mensajes")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}

