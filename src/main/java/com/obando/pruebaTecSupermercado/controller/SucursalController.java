package com.obando.pruebaTecSupermercado.controller;

import com.obando.pruebaTecSupermercado.dto.SucursalDto;
import com.obando.pruebaTecSupermercado.service.ISucursallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final ISucursallService sucursallService;

    public SucursalController(ISucursallService sucursallService) {
        this.sucursallService = sucursallService;
    }

    @GetMapping
    public ResponseEntity<List<SucursalDto>> traerSucursales(){
        return ResponseEntity.ok(sucursallService.traerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDto> create(@RequestBody SucursalDto sucursalDto){
        SucursalDto created = sucursallService.crearSucursal(sucursalDto);

        return ResponseEntity.created(URI.create("/api/sucursales/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> update(@PathVariable Long id, @RequestBody SucursalDto sucursalDto){
        return ResponseEntity.ok(sucursallService.actualizarSucursal(id, sucursalDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sucursallService.eliminarSuscursal(id);

        return ResponseEntity.noContent().build();
    }

}

