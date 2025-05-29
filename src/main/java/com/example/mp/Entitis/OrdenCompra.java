package com.example.mp.Entitis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orden_Compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenCompra extends Base{

    private Double total;
    private LocalDate fechaCompra;

    @ManyToMany
    @JsonIgnoreProperties("ordenCompras")
    @JoinTable(
            name = "ordenCompra_detalle",
            joinColumns = @JoinColumn(name = "ordeCompra_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Product> productlist;

}
