package proyectoPagina.proyectoWeb.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyectoPagina.proyectoWeb.enums.EstadoSolicitud;
import proyectoPagina.proyectoWeb.models.Solicitud;
import proyectoPagina.proyectoWeb.services.SolicitudService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/solicitudes")
public class AdminSolicitudController {

    private final SolicitudService solicitudService;

    public AdminSolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/panel")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String panel(Model model) {
        List<Solicitud> todas = solicitudService.todas();

        long total = todas.size();
        long pendientes = todas.stream().filter(s -> s.getEstado() == EstadoSolicitud.PENDIENTE).count();
        long aprobadas = todas.stream().filter(s -> s.getEstado() == EstadoSolicitud.APROBADA).count();
        long rechazadas = todas.stream().filter(s -> s.getEstado() == EstadoSolicitud.RECHAZADA).count();

        model.addAttribute("indicadores", Map.of(
                "total", total,
                "pendientes", pendientes,
                "aprobadas", aprobadas,
                "rechazadas", rechazadas
        ));

        model.addAttribute("solicitudes", todas.stream()
                .sorted((a,b) -> b.getCreadoEn().compareTo(a.getCreadoEn()))
                .collect(Collectors.toList())
        );

        return "admin/solicitudes/panel";
    }
}

