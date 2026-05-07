package proyectoPagina.proyectoWeb.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoPagina.proyectoWeb.enums.EstadoPedido;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor


public class Order {
    private Employee employee;
    private Date fecha;
    private EstadoPedido estado;
    private Customer customer;
    private List<DetailOrder> detalles;

}
