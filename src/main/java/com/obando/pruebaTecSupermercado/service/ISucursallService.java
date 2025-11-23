package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.SucursalDto;
import com.obando.pruebaTecSupermercado.model.Sucursal;

import java.util.List;

public interface ISucursallService {

    List<SucursalDto> traerSucursales();

    SucursalDto crearSucursal(SucursalDto sucursalDto);

    SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto);

    void eliminarSuscursal(Long id);
}
