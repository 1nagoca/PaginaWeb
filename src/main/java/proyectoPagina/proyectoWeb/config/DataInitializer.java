package proyectoPagina.proyectoWeb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import proyectoPagina.proyectoWeb.enums.Rol;
import proyectoPagina.proyectoWeb.models.User;
import proyectoPagina.proyectoWeb.repositories.UserRepository;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(Rol.ROLE_ADMIN, Rol.ROLE_USER));
            admin.setEnabled(true);
            userRepository.save(admin);
        }
    }
}

