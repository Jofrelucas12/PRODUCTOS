package com.productos.application.service.imp;

import com.productos.api.dto.ModificarProducto;
import com.productos.api.dto.NuevoProducto;
import com.productos.api.dto.ProductoDTO;
import com.productos.application.service.ProductosService;
import com.productos.model.entity.Producto;
import com.productos.model.repository.ProductosRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductosServiceImp implements ProductosService {

    private ProductosRepository productosRepo;

    @Override
    public Page<ProductoDTO> obtenerProductos(Pageable pageable, String sort) {
        if (sort.equalsIgnoreCase("desc")) {
            return productosRepo.findAllByOrderByPrecioDesc(pageable).map(ProductoDTO::fromEntity);
        } else {
            return productosRepo.findAllByOrderByPrecioAsc(pageable).map(ProductoDTO::fromEntity);
        }
    }

    @Override
    public ProductoDTO obtenerProductoPor(Optional<Long> id, Optional<String> nombre) {
        Producto producto;
        if (id.isPresent()) {
            return ProductoDTO.fromEntity(productosRepo.findById(id.get()).orElseThrow());
        }
        if (nombre.isPresent()) {
            return ProductoDTO.fromEntity(productosRepo.findByNombre(nombre.get().toLowerCase()).orElseThrow());
        }
        throw new RuntimeException("No se indico el atributo por el que se requiere buscar");

    }

    @Override
    public ProductoDTO crearProducto(NuevoProducto nuevoProducto) {
        if (productosRepo.findByNombre(nuevoProducto.nombre().toLowerCase()).isPresent()) {
            throw new IllegalArgumentException("El producto con el nombre " + nuevoProducto.nombre() + " ya existe.");
        }
        return ProductoDTO.fromEntity(productosRepo.save(new Producto(nuevoProducto)));
    }

    @Override
    public ProductoDTO modificarProducto(Long id, ModificarProducto modificarProducto) {
        Producto producto = productosRepo.findById(id).orElseThrow();
        if (productosRepo.findByNombre(modificarProducto.nombre().toLowerCase()).isPresent()) {
            throw new RuntimeException("El producto con el nombre " + modificarProducto.nombre() + " ya existe.");
        }

        return ProductoDTO.fromEntity(productosRepo.save(modificarProducto.modificar(producto)));
    }

    @Override
    public void borrarProducto(Long id) {
        Producto producto = productosRepo.findById(id).orElseThrow();
        productosRepo.delete(producto);
    }

}
