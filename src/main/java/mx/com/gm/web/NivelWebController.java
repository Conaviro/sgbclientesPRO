/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Nivel;
import mx.com.gm.rest.models.service.EmpresaServiceImpl;
import mx.com.gm.rest.models.service.NivelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author karla
 */
@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class NivelWebController {
    
    
    @Autowired
    private NivelService nivelService;

    @Autowired
    private EmpresaServiceImpl empresaServiceImpl;

    @GetMapping("/nivel")
    public String agregar(Nivel contacto) {
        return "nivel/formNivel";
    }

    @PostMapping("/nivel")
    public String guardar(@Valid Nivel nivel, Errors errores) {
        Nivel nivelEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (nivel.getIdNivel()== null) {
            nivelService.save(nivel);
        } else {
            nivelEnty = nivelService.updateNivel(nivel);
        }
        return "redirect:/";
    }
    
   
    @GetMapping("/niveles")
    public String card(Model model) {
        List<Nivel> niveles = nivelService.findAll();
        model.addAttribute("niveles", niveles);
        return "nivel/listaNiveles";//"index";
    }

    
}