package com.hackaton.padrino.services.usuariosServices;

import java.util.Optional;

import com.hackaton.padrino.models.usuario;
import com.hackaton.padrino.repositories.usuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("usuarioService")
public class IUsuarioService implements usuarioService{

    @Autowired
    @Qualifier("usuarioRepository")
    private usuarioRepository ur;

    @Override
    public usuario saveUsuario(usuario u) {
        if(!ur.existsById(u.getCedula_usuario())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            u.setPassword(encoder.encode(u.getPassword()));
            return ur.save(u);
        }
        return null;
    }

    @Override
    public void deleteUsuario(Long id) throws Exception {
        ur.deleteById(id);
    }

    @Override
    public usuario updateUsuario(usuario u) {
        if(ur.existsById(u.getCedula_usuario())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            u.setPassword(encoder.encode(u.getPassword()));
            return ur.save(u);
        }
        return null;
    }

    @Override
    public Optional<usuario> findUsuario(Long id) {
        return ur.findById(id);
    }

    @Override
    public usuario getUserByUsername(String username) {
        return ur.findbyUsername(username);
    }

    @Override
    public usuario updateUsuarioPitch(usuario u) {
        if(ur.existsById(u.getCedula_usuario())){
            return ur.save(u);
        }
        return null;
    }
    
}
