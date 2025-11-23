package com.obando.pruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;
}
