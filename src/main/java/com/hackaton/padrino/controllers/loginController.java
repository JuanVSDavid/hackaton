package com.hackaton.padrino.controllers;

import javax.servlet.http.HttpSession;

import com.hackaton.padrino.models.usuario;
import com.hackaton.padrino.services.usuariosServices.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {

    @Autowired
    @Qualifier("usuarioService")
    private usuarioService us;

    public loginController(usuarioService us) {
        this.us = us;
    }

    @GetMapping("/login")
    public String index() {
        return "Login";
    }

    @PostMapping("/login/register")
    public String register(@ModelAttribute usuario u, HttpSession session) {
        try {
            u.setC_pitch(0);
            if (us.saveUsuario(u) != null) {
                session.setAttribute("message", "Ya eres miembro de esta comunidad :)");
                return "redirect:/login";
            }
            session.setAttribute("alert", "Ya existe ese id de usuario");
            return "redirect:/login";
        } catch (Exception e) {
            session.setAttribute("alert", "Pillo le quitastes los requiered a los compos :)");
        }
        return "redirect:/login";
    }
}
