package com.obando.pruebaTecSupermercado.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDto {

    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    private Long idSucursal;
    private List<DetalleVentaDto> detalle;
}
