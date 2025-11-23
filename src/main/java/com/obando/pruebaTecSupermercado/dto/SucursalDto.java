package com.obando.pruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDto {

    private Long id;
    private String nombre;
    private String direccion;
}
