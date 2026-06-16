package proyectoPagina.proyectoWeb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SolicitudSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cuandoNoAutenticado_postSolicitud_retorna401() throws Exception {
        mockMvc.perform(post("/api/solicitudes")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void cuandoUsuarioNormal_postSolicitud_retorna201() throws Exception {
        mockMvc.perform(post("/api/solicitudes")
                        .contentType("application/json")
                        .content("{\"tipo\":\"SOPORTE\", \"descripcion\":\"ayuda\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void cuandoUsuarioNormal_putAprobar_retorna403() throws Exception {
        mockMvc.perform(put("/api/solicitudes/1/aprobar")
                        .contentType("application/json")
                        .content("observacion"))
                .andExpect(status().isForbidden());
    }
}

