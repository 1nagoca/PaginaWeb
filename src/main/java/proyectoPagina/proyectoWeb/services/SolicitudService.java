package proyectoPagina.proyectoWeb.services;

import org.springframework.stereotype.Service;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.repositories.SolicitudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {
    private final SolicitudRepository repo;

    public SolicitudService(SolicitudRepository repo) {
        this.repo = repo;
    }

    public Solicitud crear(Solicitud s) {
        return repo.save(s);
    }

    public List<Solicitud> misSolicitudes(String username) {
        return repo.findBySolicitanteOrderByCreadoEnDesc(username);
    }

    public List<Solicitud> todas() {
        return repo.findAll();
    }

    public Optional<Solicitud> findById(Long id) {
        return repo.findById(id);
    }
}

