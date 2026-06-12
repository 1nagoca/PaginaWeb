package proyectoPagina.proyectoWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import proyectoPagina.proyectoWeb.enums.TipoSolicitud;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SolicitudRequest {

    @NotNull(message = "El tipo es obligatorio")
    private TipoSolicitud tipo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}
