package proyectoPagina.proyectoWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import proyectoPagina.proyectoWeb.dto.SolicitudRequest;
import proyectoPagina.proyectoWeb.enums.TipoSolicitud;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.services.SolicitudService;
import proyectoPagina.proyectoWeb.services.UserDetailsServiceImpl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SolicitudSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SolicitudService solicitudService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    // Prueba 1: POST /api/solicitudes sin autenticación -> HTTP 401 o 403
    @Test
    void crearSolicitud_sinAutenticacion_retorna401o403() throws Exception {
        SolicitudRequest req = new SolicitudRequest(TipoSolicitud.SOPORTE, "Necesito soporte");

        mockMvc.perform(post("/api/solicitudes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().is4xxClientError());
    }

    // Prueba 2: POST /api/solicitudes con usuario autenticado (sin rol especial) -> HTTP 201
    @Test
    @WithMockUser(username = "usuario1", roles = "USER")
    void crearSolicitud_usuarioAutenticado_retorna201() throws Exception {
        SolicitudRequest req = new SolicitudRequest(TipoSolicitud.SOPORTE, "Necesito soporte");

        Solicitud mockSolicitud = new Solicitud();
        when(solicitudService.radicar(any(SolicitudRequest.class), anyString())).thenReturn(mockSolicitud);

        mockMvc.perform(post("/api/solicitudes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    // Prueba 3: PUT /api/solicitudes/{id}/aprobar con rol USER (sin ADMIN) -> HTTP 403
    @Test
    @WithMockUser(username = "usuario1", roles = "USER")
    void aprobarSolicitud_sinRolAdmin_retorna403() throws Exception {
        mockMvc.perform(put("/api/solicitudes/999/aprobar")
                .param("observacion", "aprobado"))
                .andExpect(status().isForbidden());
    }
}
