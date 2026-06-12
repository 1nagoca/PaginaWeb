package proyectoPagina.proyectoWeb.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String remitente; // username or email

    @NotBlank
    private String destinatario; // username or email

    @NotBlank
    private String asunto;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String contenido;

    private boolean leido = false;
    private LocalDateTime creadoEn = LocalDateTime.now();
}

