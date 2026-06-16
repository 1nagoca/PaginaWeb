package proyectoPagina.proyectoWeb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {
    private String nitEmpresa;
    private String idEmpresa;
}

