/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.web;


import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.HojaVida;
import mx.com.gm.rest.models.service.HojaVidaService;
import mx.com.gm.rest.models.service.OficioService;
import mx.com.gm.servicio.PersonaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
        
/*
 *
 * @author karla
 */
@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class HojaVidaWebController {
    
    @Autowired
    private HojaVidaService hojaVidaService;
    
    @Autowired
    private OficioService oficioService;
     
     @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @GetMapping("/agregarHojaVida")
    public String agregar(HojaVida hojaVida) {
        return "hojaVida/formHojaVida";
    }
    
    @PostMapping("/guardarHojaVida")
    public String guardar(@Valid HojaVida hojaVida, Errors errores) {
        HojaVida subAreaEnty = null;

        if (errores.hasErrors()) {
            return "/";
        }
        if (hojaVida.getIdHojaVida()== null) {
            hojaVidaService.save(hojaVida);
        } else {
               
                subAreaEnty = hojaVidaService.updateHojaVida(hojaVida);
        }
        return "redirect:/apiempleado/editpersona/"+hojaVida.getPersona().getIdPersona();//http://localhost:8686/apiempleado/editpersona/1
    }
    
    @GetMapping("/edithojaVida/{id}")
    public String editar(@PathVariable(value = "id") Long id,HojaVida hojaVida, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        hojaVida = hojaVidaService.findById(id);
        model.addAttribute("hojaVida", hojaVida);
        
        return "redirect:/apiempleado/listHojaVida";
    }

    @GetMapping("/updateHojaVida")
    public String update(HojaVida subArea, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        HojaVida hojaVidaEntru = hojaVidaService.updateHojaVida(subArea);
        model.addAttribute("hojaVida", hojaVidaEntru);
        return "/";
    }
    
    @GetMapping("/listHojaVida")
    public String card(Model model) {
        List<HojaVida> listaHojaVida = hojaVidaService.findAll();
        model.addAttribute("hojaVidas", listaHojaVida);
        return "hojaVida/listaHojaVida";//"index";
    }
    
    @GetMapping("/listOficioHoja")
    public String oficioHoja(Model model) {
        List<HojaVida> listaHojaVida = hojaVidaService.findAll();
        model.addAttribute("hojaVidas", listaHojaVida);
        return "hojaVida/listaHojaVida";//"index";
    }
    
    
    
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("oficios", oficioService.findAll());
    }
    
   
}