package com.hackaton.padrino.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "contribuciones")
public class contribucion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_contribucion;
    
    @ManyToOne
    @JoinColumn(name = "id_padrino")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pitch")
    private pitch pitch;

    private Double contribucion;
}
