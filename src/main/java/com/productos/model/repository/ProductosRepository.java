package com.productos.model.repository;

import com.productos.model.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);

    Page<Producto> findAllByOrderByPrecioDesc(Pageable pageable);

    Page<Producto> findAllByOrderByPrecioAsc(Pageable pageable);
}
