package proyectoPagina.proyectoWeb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Customer extends Person {
    private String nitEmpresa;
    private String idEmpresa;
}

