package proyectoPagina.proyectoWeb.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class Persona {
    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private String ciudad;

   }
