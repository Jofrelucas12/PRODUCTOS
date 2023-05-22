package com.productos.model.entity;

import com.productos.api.dto.NuevoProducto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "UC_PRODUCTO_NOMBRE", columnNames = {"nombre"})
)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1)
    Long id;
    String nombre;
    String descripcion;
    BigDecimal precio;
    Long cantidad;

    public Producto(NuevoProducto nuevoProducto) {
        this.nombre = nuevoProducto.nombre().toLowerCase();
        this.descripcion = nuevoProducto.descripcion();
        this.precio = nuevoProducto.precio();
        this.cantidad = nuevoProducto.cantidad();
    }
}
