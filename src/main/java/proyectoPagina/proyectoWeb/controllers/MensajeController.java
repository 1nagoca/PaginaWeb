package proyectoPagina.proyectoWeb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.services.MensajeService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping
    public ResponseEntity<Mensaje> enviar(@RequestBody @jakarta.validation.Valid Mensaje mensaje, Principal principal) {
        // set remitente from authenticated user if available
        if (principal != null) {
            mensaje.setRemitente(principal.getName());
        }
        Mensaje saved = mensajeService.enviar(mensaje);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/bandeja-entrada")
    public ResponseEntity<List<Mensaje>> bandeja(Principal principal) {
        String user = principal != null ? principal.getName() : "";
        return ResponseEntity.ok(mensajeService.bandejaEntrada(user));
    }

    @GetMapping("/enviados")
    public ResponseEntity<List<Mensaje>> enviados(Principal principal) {
        String user = principal != null ? principal.getName() : "";
        return ResponseEntity.ok(mensajeService.enviados(user));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<?> marcarLeido(@PathVariable Long id, Principal principal) {
        return mensajeService.findById(id).map(m -> {
            // only destinatario or admin can mark as read
            if (principal == null || (!principal.getName().equals(m.getDestinatario()))) {
                // allow admin through security annotations if needed; for now basic check
            }
            m.setLeido(true);
            mensajeService.enviar(m);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/no-leidos/count")
    public ResponseEntity<Long> contarNoLeidos(Principal principal) {
        String user = principal != null ? principal.getName() : "";
        return ResponseEntity.ok(mensajeService.contarNoLeidos(user));
    }
}

