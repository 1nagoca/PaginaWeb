package proyectoPagina.proyectoWeb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoPagina.proyectoWeb.enums.EstadoFactura;
import proyectoPagina.proyectoWeb.enums.MetodoPago;

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
    private MetodoPago metodoPago;
    private EstadoFactura estado;

}
