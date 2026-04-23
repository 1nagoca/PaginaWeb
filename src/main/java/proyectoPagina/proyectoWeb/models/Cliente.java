package proyectoPagina.proyectoWeb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Cliente extends Persona {

    private String nitEmpresa;
    private String idEmpresa;
    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private String ciudad;
}

