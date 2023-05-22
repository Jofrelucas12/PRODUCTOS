package com.productos.api.dto;

import com.productos.model.entity.Producto;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record ModificarProducto(@NotEmpty(message = "El nombre no puede estar vacio") String nombre,
                                String descripcion,
                                BigDecimal precio,
                                Long cantidad) {
    public Producto modificar(Producto producto) {
        if (null != nombre) {
            producto.setNombre(nombre.toLowerCase());
        }
        if (null != descripcion) {
            producto.setDescripcion(descripcion);
        }
        if (null != precio) {
            producto.setPrecio(precio);
        }
        if (null != cantidad) {
            producto.setCantidad(cantidad);
        }
        return producto;
    }
}
