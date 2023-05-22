package com.productos.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NuevoProducto(@NotNull(message = "El nombre no puede estar nulo") @NotEmpty(message = "El nombre no puede estar vacio") String nombre,
                            String descripcion,
                            @NotNull(message = "El precio no puede estar nulo")  BigDecimal precio,
                            @NotNull(message = "La cantidad no puede ser nula")  Long cantidad) {
}
