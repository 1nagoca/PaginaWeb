package proyectoPagina.proyectoWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoPagina.proyectoWeb.models.Solicitud;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findBySolicitanteOrderByCreadoEnDesc(String solicitante);
}

