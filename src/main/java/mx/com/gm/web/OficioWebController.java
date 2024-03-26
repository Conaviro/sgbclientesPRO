package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Oficio;
import mx.com.gm.rest.models.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class OficioWebController {
    
    @Autowired
    private OficioService oficioService;
    
    @GetMapping("/agregaroficio")
    public String agregar(Oficio oficio) {
        return "oficio/formOficio";
    }
    
    @PostMapping("/guardaroficio")
    public String guardar(@Valid Oficio oficio, Errors errores) {
        Oficio oficioEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (oficio.getIdOficio() == null) {
            oficioService.save(oficio);
        } else {
            oficioEnty = oficioService.uptadeOficio(oficio);
        }
        return "redirect:/apiempleado/listoficio";
    }
    
    @GetMapping("/editoficio/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Oficio oficio, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        oficio = oficioService.findById(id);
        model.addAttribute("oficio", oficio);
        return "oficio/formOficio";
    }
    
    @GetMapping("/updoficio")
    public String update(Oficio oficio, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Oficio oficioEnty = oficioService.uptadeOficio(oficio);
        model.addAttribute("oficio", oficioEnty);
        return "redirect:/apiempleado/listoficio";
    }
    @GetMapping("/listoficio")
    public String card(Model model) {
        List<Oficio> listaOfici = oficioService.findAll();
        model.addAttribute("oficios", listaOfici);
        return "oficio/listaOficios";
    }
    
    
}
