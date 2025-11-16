package com.obando.pruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;
}
