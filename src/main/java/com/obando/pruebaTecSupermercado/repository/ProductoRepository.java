package com.obando.pruebaTecSupermercado.repository;

import com.obando.pruebaTecSupermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /// Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);
}
