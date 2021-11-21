package com.hackaton.padrino.WebSecurity;

import com.hackaton.padrino.models.usuario;
import com.hackaton.padrino.repositories.usuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("usuarioRepository")
    usuarioRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final usuario u = ur.findbyUsername(username);
        if(u == null){
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new CustomUserDetails(u);
    }
    
}
