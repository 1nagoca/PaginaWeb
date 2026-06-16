package proyectoPagina.proyectoWeb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.services.SolicitudService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(@RequestBody @jakarta.validation.Valid Solicitud s, Principal principal) {
        if (principal != null) {
            s.setSolicitante(principal.getName());
        }
        Solicitud saved = solicitudService.crear(s);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/mis-solicitudes")
    public ResponseEntity<List<Solicitud>> misSolicitudes(Principal principal) {
        String user = principal != null ? principal.getName() : "";
        return ResponseEntity.ok(solicitudService.misSolicitudes(user));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Solicitud>> todas() {
        return ResponseEntity.ok(solicitudService.todas());
    }

    @PutMapping("/{id}/aprobar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> aprobar(@PathVariable Long id, @RequestBody(required = false) String notas) {
        return solicitudService.findById(id).map(s -> {
            s.setEstado(proyectoPagina.proyectoWeb.enums.EstadoSolicitud.APROBADA);
            s.setNotasAdmin(notas);
            s.setResueltoEn(LocalDateTime.now());
            solicitudService.crear(s);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/rechazar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> rechazar(@PathVariable Long id, @RequestBody(required = false) String notas) {
        return solicitudService.findById(id).map(s -> {
            s.setEstado(proyectoPagina.proyectoWeb.enums.EstadoSolicitud.RECHAZADA);
            s.setNotasAdmin(notas);
            s.setResueltoEn(LocalDateTime.now());
            solicitudService.crear(s);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

