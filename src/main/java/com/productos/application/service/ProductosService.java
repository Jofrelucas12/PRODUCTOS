package com.productos.application.service;

import com.productos.api.dto.ModificarProducto;
import com.productos.api.dto.NuevoProducto;
import com.productos.api.dto.ProductoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductosService {
    Page<ProductoDTO> obtenerProductos(Pageable pageable, String sort);

    ProductoDTO obtenerProductoPor(Optional<Long> id, Optional<String> nombre);

    ProductoDTO crearProducto(NuevoProducto nuevoProducto);

    ProductoDTO modificarProducto(Long id, ModificarProducto modificarProducto);

    void borrarProducto(Long id);
}
