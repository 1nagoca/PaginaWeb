package proyectoPagina.proyectoWeb.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Material {
    private String material;
    private String nombre;
    private String tipo;
    private double cantidadDisponible;
}
