package com.hackaton.padrino.controllers;

import com.hackaton.padrino.models.formulario;
import com.hackaton.padrino.models.pitch;
import com.hackaton.padrino.services.pitchServices.pitchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/explorar"})
public class exploreController {
    
    @Autowired
    @Qualifier("pitchService")
    private pitchService ps;

    public exploreController(pitchService ps) {
        this.ps = ps;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("activePage", "explorar");
        model.addAttribute("proyectos", ps.listPitch());
        return "index";
    }

    @GetMapping("/vista/{id}")
    public String vista(@PathVariable Long id, Model model){
        formulario f = new formulario();
        f.setId(id);
        model.addAttribute("formulario", f);
        model.addAttribute("activePage", "explorar");
        pitch p = ps.getPitchById(id).get();
        String url = p.getUrl_youtube();
        p.setUrl_youtube(url.substring(url.indexOf(".be/") + 4));
        model.addAttribute("pitch", p);
        model.addAttribute("list_pitch", p.getContribuciones());
        model.addAttribute("texto", "Tú puedes ser el primero");
        return "vista";
    }
}
