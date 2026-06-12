package proyectoPagina.proyectoWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import proyectoPagina.proyectoWeb.config.SecurityConfig;
import proyectoPagina.proyectoWeb.controllers.MensajeController;
import proyectoPagina.proyectoWeb.dto.MensajeRequest;
import proyectoPagina.proyectoWeb.services.MensajeService;
import proyectoPagina.proyectoWeb.services.UserDetailsServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MensajeController.class)
@Import(SecurityConfig.class)
class MensajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MensajeService mensajeService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    // Prueba 1: GET /api/mensajes/bandeja-entrada con usuario autenticado -> HTTP 200
    @Test
    @WithMockUser(username = "usuario1", roles = "USER")
    void bandejaEntrada_autenticado_retorna200() throws Exception {
        when(mensajeService.getBandejaEntrada(anyString())).thenReturn(List.of());

        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().isOk());
    }

    // Prueba 2: GET /api/mensajes/bandeja-entrada sin autenticación -> HTTP 401 o 403
    @Test
    void bandejaEntrada_sinAutenticacion_retorna401o403() throws Exception {
        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().is4xxClientError());
    }

    // Prueba 3: POST /api/mensajes con cuerpo vacío -> HTTP 400
    @Test
    @WithMockUser(username = "usuario1", roles = "USER")
    void enviarMensaje_camposVacios_retorna400() throws Exception {
        MensajeRequest req = new MensajeRequest("", "", "");

        mockMvc.perform(post("/api/mensajes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }
}
