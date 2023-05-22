package com.productos.api.imp;

import com.productos.api.ProductosAPI;
import com.productos.api.dto.ModificarProducto;
import com.productos.api.dto.NuevoProducto;
import com.productos.api.dto.ProductoDTO;
import com.productos.application.service.ProductosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/productos")
public class ProductosController implements ProductosAPI {

    private final ProductosService productosService;

    @GetMapping
    public Page<ProductoDTO> obtenerProductos(Pageable pageable, @RequestParam(defaultValue = "asc") String sort) {
        return productosService.obtenerProductos(pageable, sort);
    }

    @GetMapping(path = "/buscar")
    public ProductoDTO obtenerProducto(@RequestParam(name = "id", required = false) Optional<Long> id, @RequestParam(name = "nombre", required = false) Optional<String> nombre) {
        return productosService.obtenerProductoPor(id, nombre);
    }

    @PostMapping
    public ProductoDTO crearProducto(@RequestBody @Valid NuevoProducto nuevoProducto) {
        return productosService.crearProducto(nuevoProducto);
    }

    @PutMapping(path = "/{id}")
    public ProductoDTO modificarProducto(@PathVariable Long id, @RequestBody @Valid ModificarProducto modificarProducto) {
        return productosService.modificarProducto(id, modificarProducto);
    }

    @DeleteMapping(path = "/{id}")
    public void borrarProducto(@PathVariable Long id) {
        productosService.borrarProducto(id);
    }
}
