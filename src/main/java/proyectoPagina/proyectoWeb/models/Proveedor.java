package proyectoPagina.proyectoWeb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Proveedor {
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;

}