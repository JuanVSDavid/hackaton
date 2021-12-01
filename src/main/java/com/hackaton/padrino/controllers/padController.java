package com.hackaton.padrino.controllers;

import com.hackaton.padrino.models.usuario;
import com.hackaton.padrino.services.contriServices.contriService;
import com.hackaton.padrino.services.usuariosServices.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class padController {
    @Autowired
    @Qualifier("contriService")
    private contriService cs;
    @Autowired
    @Qualifier("usuarioService")
    private usuarioService us;

    public usuario getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        String username = userDetails.getUsername();
        return us.getUserByUsername(username);
    }

    @GetMapping("/beneficiarios")
    public String get(Model model) {
        usuario u = getUser();
        model.addAttribute("be", cs.listContriForId_usuario(u.getCedula_usuario()));
        return "beneficiarios";
    }
}
