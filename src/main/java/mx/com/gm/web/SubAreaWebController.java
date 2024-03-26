/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.SubArea;
import mx.com.gm.rest.models.service.AreaServiceImpl;
import mx.com.gm.rest.models.service.SubAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author USER
 */
@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class SubAreaWebController {
    
    @Autowired
    private SubAreaService subAreaService;
     
    @Autowired
    private AreaServiceImpl areaServiceImpl;
    
    @GetMapping("/agregarSubArea")
    public String agregar(SubArea subArea) {
        return "subarea/formSubArea";
    }
    
    @PostMapping("/guardarSubArea")
    public String guardar(@Valid SubArea subArea, Errors errores) {
        SubArea subAreaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (subArea.getIdSubArea()== null) {
            subAreaService.save(subArea);
        } else {
                subAreaEnty = subAreaService.uptadeSubArea(subArea);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editsubarea/{id}")
    public String editar(@PathVariable(value = "id") Integer id,SubArea subArea, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        subArea = subAreaService.findById(id);
        model.addAttribute("subArea", subArea);
        return "subarea/formSubArea";
    }

    @GetMapping("/updsubarea")
    public String update(SubArea subArea, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        SubArea subAreaEnty = subAreaService.uptadeSubArea(subArea);
        model.addAttribute("subarea", subAreaEnty);
        return "/";
    }
    
    @GetMapping("/listSubArea")
    public String card(Model model) {
        List<SubArea> listaSubArea = subAreaService.findAll();
        model.addAttribute("subareas", listaSubArea);
        return "subarea/listaSubAreas";//"index";
    }
    
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("areas", areaServiceImpl.findAll());
    }

}
