package proyectoPagina.proyectoWeb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyectoPagina.proyectoWeb.dto.SolicitudRequest;
import proyectoPagina.proyectoWeb.enums.EstadoSolicitud;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.models.User;
import proyectoPagina.proyectoWeb.repositories.SolicitudRepository;
import proyectoPagina.proyectoWeb.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UserRepository userRepository;

    public Solicitud radicar(SolicitudRequest req, String username) {
        User solicitante = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Solicitud s = Solicitud.builder()
                .solicitante(solicitante)
                .tipo(req.getTipo())
                .descripcion(req.getDescripcion())
                .build();
        return solicitudRepository.save(s);
    }

    public List<Solicitud> getMisSolicitudes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return solicitudRepository.findBySolicitante(user);
    }

    public List<Solicitud> getTodas() {
        return solicitudRepository.findAll();
    }

    public Solicitud aprobar(Long id, String observacion) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));
        s.setEstado(EstadoSolicitud.APROBADA);
        s.setObservacion(observacion);
        s.setFechaResolucion(LocalDateTime.now());
        return solicitudRepository.save(s);
    }

    public Solicitud rechazar(Long id, String observacion) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));
        s.setEstado(EstadoSolicitud.RECHAZADA);
        s.setObservacion(observacion);
        s.setFechaResolucion(LocalDateTime.now());
        return solicitudRepository.save(s);
    }

    public long contarPorEstado(EstadoSolicitud estado) {
        return solicitudRepository.countByEstado(estado);
    }

    public long contarTotal() {
        return solicitudRepository.count();
    }
}
