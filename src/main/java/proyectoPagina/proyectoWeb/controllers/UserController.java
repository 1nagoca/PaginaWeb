package proyectoPagina.proyectoWeb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import proyectoPagina.proyectoWeb.enums.Rol;
import proyectoPagina.proyectoWeb.models.User;
import proyectoPagina.proyectoWeb.repositories.UserRepository;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Usuario ya existe");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// default role
		user.setRoles(Set.of(Rol.ROLE_USER));
		user.setEnabled(true);
		User saved = userRepository.save(user);
		saved.setPassword(null);
		return ResponseEntity.ok(saved);
	}
}
