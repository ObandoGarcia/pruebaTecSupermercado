package com.obando.pruebaTecSupermercado.controller;

import com.obando.pruebaTecSupermercado.dto.VentaDto;
import com.obando.pruebaTecSupermercado.service.IVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> traerVentas(){
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDto> create(@RequestBody VentaDto ventaDto){
        VentaDto created = ventaService.crearVenta(ventaDto);

        return ResponseEntity.created(URI.create("/api/ventas/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public VentaDto actualizar(@PathVariable Long id, @RequestBody VentaDto ventaDto){
        return ventaService.actualizarVenta(id, ventaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }
}
