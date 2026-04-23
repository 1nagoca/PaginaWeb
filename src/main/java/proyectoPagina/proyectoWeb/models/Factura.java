package proyectoPagina.proyectoWeb.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Factura {
    private Pedido pedido;
    private Empleado empleado;
    private Date fechaEmision;
    private double total;
    private String metodoPago;
    private String estado;

}
