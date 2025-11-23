package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.ProductoDto;

import java.util.List;

public interface IProductoService {

    List<ProductoDto> traerProducto();

    ProductoDto crearProducto(ProductoDto productoDto);

    ProductoDto actualizarProducto(Long id, ProductoDto productoDto);

    void eliminarProducto(Long id);
}
