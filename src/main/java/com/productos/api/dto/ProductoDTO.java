package com.productos.api.dto;

import com.productos.model.entity.Producto;

import java.math.BigDecimal;

public record ProductoDTO(Long id, String nombre, String descripcion, BigDecimal precio, Long cantidad) {
    public static ProductoDTO fromEntity(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad());
    }
}
