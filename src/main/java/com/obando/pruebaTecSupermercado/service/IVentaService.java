package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.VentaDto;

import java.util.List;

public interface IVentaService {

    List<VentaDto> traerVentas();

    VentaDto crearVenta(VentaDto ventaDto);

    VentaDto actualizarVenta(Long id, VentaDto ventaDto);

    void eliminarVenta(Long id);
}
