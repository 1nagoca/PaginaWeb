package proyectoPagina.proyectoWeb.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import proyectoPagina.proyectoWeb.dto.SolicitudRequest;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.services.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @PostMapping
    public ResponseEntity<Solicitud> radicar(
            @Valid @RequestBody SolicitudRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Solicitud s = solicitudService.radicar(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping("/mis-solicitudes")
    public ResponseEntity<List<Solicitud>> misSolicitudes(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(solicitudService.getMisSolicitudes(userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> todas() {
        return ResponseEntity.ok(solicitudService.getTodas());
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Solicitud> aprobar(
            @PathVariable Long id,
            @RequestParam String observacion) {
        return ResponseEntity.ok(solicitudService.aprobar(id, observacion));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Solicitud> rechazar(
            @PathVariable Long id,
            @RequestParam String observacion) {
        return ResponseEntity.ok(solicitudService.rechazar(id, observacion));
    }
}
