package proyectoPagina.proyectoWeb;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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

    @MockitoBean
    private MensajeService mensajeService;

    @MockitoBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser(username = "usuario1", roles = "USER")
    void bandejaEntrada_autenticado_retorna200() throws Exception {
        when(mensajeService.getBandejaEntrada(anyString())).thenReturn(List.of());

        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().isOk());
    }

    @Test
    void bandejaEntrada_sinAutenticacion_retorna401o403() throws Exception {
        mockMvc.perform(get("/api/mensajes/bandeja-entrada"))
                .andExpect(status().is4xxClientError());
    }

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
