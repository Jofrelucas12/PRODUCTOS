package com.productos.api;

import com.productos.api.dto.ModificarProducto;
import com.productos.api.dto.NuevoProducto;
import com.productos.api.dto.ProductoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductosAPI {
    @Operation(description = "Devuelve todos los productos paginados ordenados por precio ordenado predeterminadamente de forma ascendentem de requerir lo contrario indicar con el parametro sort con el valor 'desc'.")
    Page<ProductoDTO> obtenerProductos(Pageable pageable, String sort) ;

    @Operation(description = "Devuelve un producto por id o por nombre segun se indique.")
    ProductoDTO obtenerProducto(Optional<Long> id,Optional<String> nombre);
    @Operation(description = "Crea un nuevo producto.")
    ProductoDTO crearProducto(NuevoProducto nuevoProducto);
    @Operation(description = "Modifica un producto por la id indicada.")
    ProductoDTO modificarProducto(Long id, ModificarProducto modificarProducto);
    @Operation(description = "Borra un producto por id.")
    void borrarProducto(Long id);

}
