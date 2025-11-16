package com.obando.pruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {

    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    private Long idSucursal;
    private List<DetalleVentaDto> detalle;
}
