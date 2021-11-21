package com.hackaton.padrino.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pitchs")
public class pitch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pitch;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private usuario usuario;

    private String titulo;
    private String url_youtube;
    private String src_foto;
    private String descripcion;
    private Double meta;
    private Double recaudado;
    private String fecha_inicial;
    private String fecha_final;
    private Boolean estado;

    @OneToMany(mappedBy = "pitch", fetch = FetchType.LAZY)
    private List<contribucion> contribuciones;
}
