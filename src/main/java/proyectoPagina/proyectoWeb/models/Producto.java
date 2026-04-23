package proyectoPagina.proyectoWeb.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Producto {
    private int cantidad;
    private String nombre;
    private String talla;
    private double precio;
    private String tipo;
}
