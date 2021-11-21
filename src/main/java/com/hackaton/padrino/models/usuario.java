package com.hackaton.padrino.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class usuario {
    @Id
    private Long cedula_usuario;

    private Integer edad;
    private String password;
    private String role;
    private String usuario_email;
    private String usuario_name;
    private String username;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private pitch pitch;

    private Integer c_pitch;

    @OneToMany(mappedBy = "usuario")
    private List<contribucion> contribucion;
    
}
