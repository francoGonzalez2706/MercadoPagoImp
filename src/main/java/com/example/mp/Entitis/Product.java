package com.example.mp.Entitis;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends Base {
    private String nombre;
    private String descripcion;
    private Double precio;

}
