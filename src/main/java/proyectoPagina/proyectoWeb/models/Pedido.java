package proyectoPagina.proyectoWeb.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor


public class Pedido {
    private Empleado empleado;
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private List<DetallePedido> detallePedido;

}
