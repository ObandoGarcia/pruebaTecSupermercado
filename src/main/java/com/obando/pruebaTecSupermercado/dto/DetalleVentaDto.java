package com.obando.pruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDto {

    private Long id;
    private String nombreProducto;
    private int cantidad;
    private Double precio;
    private Double subtotal;
}
