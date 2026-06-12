package proyectoPagina.proyectoWeb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoPagina.proyectoWeb.enums.EstadoSolicitud;
import proyectoPagina.proyectoWeb.enums.TipoSolicitud;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String solicitante; // username or email

    @Enumerated(EnumType.STRING)
    private TipoSolicitud tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    @Column(columnDefinition = "TEXT")
    private String notasAdmin;

    private LocalDateTime creadoEn = LocalDateTime.now();
    private LocalDateTime resueltoEn;
}

