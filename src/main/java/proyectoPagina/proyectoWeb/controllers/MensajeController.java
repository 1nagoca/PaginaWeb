package proyectoPagina.proyectoWeb.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import proyectoPagina.proyectoWeb.dto.MensajeRequest;
import proyectoPagina.proyectoWeb.models.Mensaje;
import proyectoPagina.proyectoWeb.services.MensajeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping
    public ResponseEntity<Mensaje> enviar(
            @Valid @RequestBody MensajeRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Mensaje mensaje = mensajeService.enviar(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/bandeja-entrada")
    public ResponseEntity<List<Mensaje>> bandejaEntrada(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(mensajeService.getBandejaEntrada(userDetails.getUsername()));
    }

    @GetMapping("/enviados")
    public ResponseEntity<List<Mensaje>> enviados(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(mensajeService.getEnviados(userDetails.getUsername()));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<Mensaje> marcarLeido(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(mensajeService.marcarLeido(id, userDetails.getUsername()));
    }

    @GetMapping("/no-leidos/count")
    public ResponseEntity<Map<String, Long>> contarNoLeidos(
            @AuthenticationPrincipal UserDetails userDetails) {
        long count = mensajeService.contarNoLeidos(userDetails.getUsername());
        return ResponseEntity.ok(Map.of("count", count));
    }
}
