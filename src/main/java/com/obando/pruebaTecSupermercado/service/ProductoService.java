package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.ProductoDto;
import com.obando.pruebaTecSupermercado.exception.NotFoundException;
import com.obando.pruebaTecSupermercado.model.Producto;
import com.obando.pruebaTecSupermercado.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.obando.pruebaTecSupermercado.mapper.Mapper;

@Service
public class ProductoService implements IProductoService{

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDto> traerProducto() {
        return productoRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        Producto producto = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();

        return Mapper.toDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        producto.setNombre(productoDto.getNombre());
        producto.setCantidad(productoDto.getCantidad());
        producto.setCantidad(productoDto.getCantidad());
        producto.setPrecio(productoDto.getPrecio());

        return Mapper.toDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
    }
}
