package com.obando.pruebaTecSupermercado.controller;

import com.obando.pruebaTecSupermercado.dto.ProductoDto;
import com.obando.pruebaTecSupermercado.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getProductos(){
        return ResponseEntity.ok(productoService.traerProducto());
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto){
        ProductoDto nuevoProducto = productoService.crearProducto(productoDto);

        return ResponseEntity.created(URI.create("/api/productos" + nuevoProducto.getId())).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDto productoDto){
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
