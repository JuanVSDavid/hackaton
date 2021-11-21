package com.hackaton.padrino.controllers;

import com.hackaton.padrino.models.contribucion;
import com.hackaton.padrino.models.formulario;
import com.hackaton.padrino.models.pitch;
import com.hackaton.padrino.models.usuario;
import com.hackaton.padrino.services.contriServices.contriService;
import com.hackaton.padrino.services.pitchServices.pitchService;
import com.hackaton.padrino.services.usuariosServices.usuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class contriController {

    @Autowired
    @Qualifier("contriService")
    private contriService cs;

    @Autowired
    @Qualifier("usuarioService")
    private usuarioService us;

    @Autowired
    @Qualifier("pitchService")
    private pitchService ps;

    public contriController(contriService cs, usuarioService us, pitchService ps) {
        this.cs = cs;
        this.us = us;
        this.ps = ps;
    }

    public usuario getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        String username = userDetails.getUsername();
        return us.getUserByUsername(username);
    }

    @GetMapping("/confirmacion/{message}")
    public String confirmacion(@PathVariable("message") String message, Model model) {
        model.addAttribute("src_img", "/avatar-logos/login.svg");
        if (message.equals("OKEY")) {
            model.addAttribute("message", "El pago se hizo con éxito");
            return "confirmacion";
        }
        model.addAttribute("error", "El pago no se pudo completar, la cédula no coincide");
        return "confirmacion";
    }

    @PostMapping("/contriUpload")
    public String contribucion(@ModelAttribute formulario f, Model model) {
        usuario u = getUser();
        if (u.getCedula_usuario().compareTo(f.getCedula()) == 0) {
            contribucion c = new contribucion();
            c.setUsuario(u);
            pitch p = ps.getPitchById(f.getId()).get();
            c.setPitch(p);
            c.setContribucion(f.getContribucion());
            cs.saveContribucion(c);
            p.setRecaudado(p.getRecaudado() + f.getContribucion());
            ps.updatePitch(p);
            return "redirect:/confirmacion/" + "OKEY";
        }
        return "redirect:/confirmacion/" + "FALSE";
    }
}
