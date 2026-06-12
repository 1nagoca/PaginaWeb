package proyectoPagina.proyectoWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoPagina.proyectoWeb.enums.EstadoSolicitud;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.models.User;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findBySolicitante(User solicitante);
    long countByEstado(EstadoSolicitud estado);
}
