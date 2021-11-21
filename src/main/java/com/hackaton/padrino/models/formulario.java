package com.hackaton.padrino.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class formulario {
    private Long id;
    private Long cedula;
    private Double contribucion;
}
