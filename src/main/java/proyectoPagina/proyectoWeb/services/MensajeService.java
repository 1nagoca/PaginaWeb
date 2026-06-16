package proyectoPagina.proyectoWeb.services;

import org.springframework.stereotype.Service;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.repositories.MensajeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    private final MensajeRepository repo;

    public MensajeService(MensajeRepository repo) {
        this.repo = repo;
    }

    public Mensaje enviar(Mensaje m) {
        return repo.save(m);
    }

    public List<Mensaje> bandejaEntrada(String username) {
        return repo.findByDestinatarioOrderByCreadoEnDesc(username);
    }

    public List<Mensaje> enviados(String username) {
        return repo.findByRemitenteOrderByCreadoEnDesc(username);
    }

    public Optional<Mensaje> findById(Long id) {
        return repo.findById(id);
    }

    public long contarNoLeidos(String username) {
        return repo.countByDestinatarioAndLeidoFalse(username);
    }
}

