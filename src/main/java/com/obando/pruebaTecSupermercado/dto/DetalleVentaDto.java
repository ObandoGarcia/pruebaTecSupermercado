package com.obando.pruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDto {

    private Long id;
    private String nombreProducto;
    private int cantidad;
    private Double precio;
    private Double subtotal;
}
