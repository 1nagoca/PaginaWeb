package proyectoPagina.proyectoWeb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import proyectoPagina.proyectoWeb.services.UserDetailsServiceImpl;

@SpringBootTest
class ProyectoWebApplicationTests {

    @MockitoBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void contextLoads() {
    }
}
