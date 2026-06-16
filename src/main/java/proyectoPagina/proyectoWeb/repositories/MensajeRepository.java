package proyectoPagina.proyectoWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoPagina.proyectoWeb.models.Mensaje;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByDestinatarioOrderByCreadoEnDesc(String destinatario);
    List<Mensaje> findByRemitenteOrderByCreadoEnDesc(String remitente);
    long countByDestinatarioAndLeidoFalse(String destinatario);
}

