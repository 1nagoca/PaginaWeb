package proyectoPagina.proyectoWeb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyectoPagina.proyectoWeb.dto.MensajeRequest;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.models.User;
import proyectoPagina.proyectoWeb.repositories.MensajeRepository;
import proyectoPagina.proyectoWeb.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final UserRepository userRepository;

    public Mensaje enviar(MensajeRequest req, String emisorUsername) {
        User emisor = userRepository.findByUsername(emisorUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emisor no encontrado"));
        User receptor = userRepository.findByUsername(req.getDestinatarioUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destinatario no encontrado"));

        Mensaje mensaje = Mensaje.builder()
                .emisor(emisor)
                .receptor(receptor)
                .asunto(req.getAsunto())
                .contenido(req.getContenido())
                .leido(false)
                .build();
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> getBandejaEntrada(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.findByReceptor(user);
    }

    public List<Mensaje> getEnviados(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.findByEmisor(user);
    }

    public Mensaje marcarLeido(Long id, String username) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado"));
        if (!mensaje.getReceptor().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes marcar mensajes de otros usuarios");
        }
        mensaje.setLeido(true);
        return mensajeRepository.save(mensaje);
    }

    public long contarNoLeidos(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mensajeRepository.countByReceptorAndLeidoFalse(user);
    }
}
