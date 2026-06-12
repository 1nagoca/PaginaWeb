package proyectoPagina.proyectoWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.models.User;
import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByReceptor(User receptor);
    List<Mensaje> findByEmisor(User emisor);
    long countByReceptorAndLeidoFalse(User receptor);
}
