package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.SucursalDto;
import com.obando.pruebaTecSupermercado.exception.NotFoundException;
import com.obando.pruebaTecSupermercado.mapper.Mapper;
import com.obando.pruebaTecSupermercado.model.Sucursal;
import com.obando.pruebaTecSupermercado.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursallService{

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<SucursalDto> traerSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public SucursalDto crearSucursal(SucursalDto sucursalDto) {
        Sucursal sucursal = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDto(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        sucursal.setNombre(sucursalDto.getNombre());
        sucursal.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDto(sucursalRepository.save(sucursal));
    }

    @Override
    public void eliminarSuscursal(Long id) {
        if (!sucursalRepository.existsById(id))
            throw  new NotFoundException("Sucursal no encontrada");

        sucursalRepository.deleteById(id);
    }
}
