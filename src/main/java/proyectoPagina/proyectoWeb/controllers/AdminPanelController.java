package proyectoPagina.proyectoWeb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyectoPagina.proyectoWeb.enums.EstadoSolicitud;
import proyectoPagina.proyectoWeb.services.SolicitudService;

@Controller
@RequestMapping("/admin/solicitudes")
@RequiredArgsConstructor
public class AdminPanelController {

    private final SolicitudService solicitudService;

    @GetMapping("/panel")
    public String panel(Model model) {
        model.addAttribute("solicitudes", solicitudService.getTodas());
        model.addAttribute("total", solicitudService.contarTotal());
        model.addAttribute("pendientes", solicitudService.contarPorEstado(EstadoSolicitud.PENDIENTE));
        model.addAttribute("aprobadas", solicitudService.contarPorEstado(EstadoSolicitud.APROBADA));
        model.addAttribute("rechazadas", solicitudService.contarPorEstado(EstadoSolicitud.RECHAZADA));
        return "admin/panel-solicitudes";
    }
}
