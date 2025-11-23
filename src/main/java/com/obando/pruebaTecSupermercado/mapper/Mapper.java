package com.obando.pruebaTecSupermercado.mapper;

import com.obando.pruebaTecSupermercado.dto.DetalleVentaDto;
import com.obando.pruebaTecSupermercado.dto.ProductoDto;
import com.obando.pruebaTecSupermercado.dto.SucursalDto;
import com.obando.pruebaTecSupermercado.dto.VentaDto;
import com.obando.pruebaTecSupermercado.model.DetalleVenta;
import com.obando.pruebaTecSupermercado.model.Producto;
import com.obando.pruebaTecSupermercado.model.Sucursal;
import com.obando.pruebaTecSupermercado.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de producto a productoDto
    public static ProductoDto toDto(Producto producto){
        if (producto == null) return null;

        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();
    }

    //Mapeo de sucursal a sucursalDto
    public static SucursalDto toDto(Sucursal sucursal){
        if (sucursal == null) return null;

        return SucursalDto.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

    //Mapeo de venta a ventaDto
    public static VentaDto toDto(Venta venta){
        if (venta == null) return null;

        var detalle = venta.getDetalleVentas()
                .stream()
                .map(detalleVenta ->
                        DetalleVentaDto.builder()
                                .id(detalleVenta.getProducto().getId())
                                .nombreProducto(detalleVenta.getProducto().getNombre())
                                .cantidad(detalleVenta.getCantidad())
                                .precio(detalleVenta.getPrecio())
                                .subtotal(detalleVenta.getPrecio() * detalleVenta.getCantidad())
                                .build()
                ).toList();

        var total = detalle.stream()
                .map(DetalleVentaDto::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDto.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }
}
