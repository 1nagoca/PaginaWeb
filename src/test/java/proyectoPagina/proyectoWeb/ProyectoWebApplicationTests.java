package proyectoPagina.proyectoWeb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import proyectoPagina.proyectoWeb.services.UserDetailsServiceImpl;

@SpringBootTest
class ProyectoWebApplicationTests {

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void contextLoads() {
    }
}
