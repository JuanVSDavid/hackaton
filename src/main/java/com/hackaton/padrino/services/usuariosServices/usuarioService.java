package com.hackaton.padrino.services.usuariosServices;

import java.util.Optional;

import com.hackaton.padrino.models.usuario;

public interface usuarioService {
    public abstract usuario saveUsuario(usuario u);
    public abstract void deleteUsuario(Long id) throws Exception;
    public abstract usuario updateUsuario(usuario u);
    public abstract Optional<usuario> findUsuario(Long id);
    public abstract usuario getUserByUsername(String username);
    public abstract usuario updateUsuarioPitch(usuario u);
}
