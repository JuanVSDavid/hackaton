package com.hackaton.padrino.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.hackaton.padrino.models.contribucion;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proyecto")
public class pitchController {

    @Autowired
    @Qualifier("pitchService")
    private pitchService ps;

    @Autowired
    @Qualifier("usuarioService")
    private usuarioService us;

    @Autowired
    @Qualifier("contriService")
    private contriService cs;

    public pitchController(pitchService ps, usuarioService us) {
        this.ps = ps;
        this.us = us;
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

    @GetMapping
    public String getPitch(Model model) {
        // authenticateUser();
        usuario u_2 = getUser();
        model.addAttribute("activePage", "proyecto");
        switch (u_2.getC_pitch()) {
        case 0:
            model.addAttribute("pitch", new pitch());
            model.addAttribute("username", u_2.getUsername());
            model.addAttribute("subir", "/proyecto/upload");
            break;
        case 1:
            model.addAttribute("pitch", u_2.getPitch());
            model.addAttribute("username", u_2.getUsername());
            model.addAttribute("subir", "/proyecto/update");
            break;
        }
        return "pitch";
    }

    @PostMapping("/upload")
    public String uploadPitch(@ModelAttribute pitch p, @RequestParam("file") MultipartFile imagen, Model model,
            HttpSession session) {
        if (getUser().getC_pitch() == 0) {
            if (!imagen.isEmpty()) {
                try {
                    Path directorio_img = Paths.get("src//main//resources//static/imagenes");
                    String absolute = directorio_img.toFile().getAbsolutePath();
                    byte[] bytes_img = imagen.getBytes();
                    Path path = Paths.get(absolute + "//" + imagen.getOriginalFilename());
                    Files.write(path, bytes_img);
                    p.setSrc_foto(imagen.getOriginalFilename());
                } catch (IOException e) {

                }
                String url = p.getUrl_youtube();
                p.setId_pitch(p.getId_pitch());
                p.setUrl_youtube(url);
                usuario u = getUser();
                u.setC_pitch(1);
                us.updateUsuarioPitch(u);
                p.setUsuario(u);
                p.setRecaudado(0.0);
                p.setEstado(true);
                pitch p_2 = ps.savePitch(p);
                return "redirect:/explorar/vista/" + p_2.getId_pitch();
            }
        }
        return "redirect:/pitch";
    }

    @PostMapping("/update")
    public String updatePitch(@ModelAttribute pitch p, @RequestParam("file") MultipartFile imagen, Model model,
            HttpSession session) {
        if (getUser().getC_pitch() == 1) {
            if (!imagen.isEmpty()) {
                try {
                    Path directorio_img = Paths.get("src//main//resources//static/imagenes");
                    String absolute = directorio_img.toFile().getAbsolutePath();
                    File imagen_update = new File(absolute + "/" + getUser().getPitch().getSrc_foto());
                    imagen_update.delete();
                    byte[] bytes_img = imagen.getBytes();
                    Path path = Paths.get(absolute + "//" + imagen.getOriginalFilename());
                    Files.write(path, bytes_img);
                    p.setSrc_foto(imagen.getOriginalFilename());
                } catch (IOException e) {

                }
                String url = p.getUrl_youtube();
                p.setUrl_youtube(url);
                p.setUsuario(getUser());
                pitch p_2 = ps.updatePitch(p);
                return "redirect:/explorar/vista/" + p_2.getId_pitch();
            }
        }
        return "redirect:/pitch";
    }
    @GetMapping("/delete")
    public String delete(){
        usuario u = getUser();
        Long id = u.getPitch().getId_pitch();
        List<contribucion> listc = u.getPitch().getContribuciones();
        for (contribucion cc : listc) {
            cs.deleteContribucion(cc.getId_contribucion());
        }
        ps.deletePitchById(id);
        u.setC_pitch(0);
        us.updateUsuarioPitch(u);
        return "redirect:/explorar";
    }
}
