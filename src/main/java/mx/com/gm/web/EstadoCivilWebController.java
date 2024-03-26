
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.EstadoCivil;
import mx.com.gm.rest.models.service.EstadoCivilService;
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
public class EstadoCivilWebController {
    
     @Autowired
    private EstadoCivilService estadoCivilService;

    @GetMapping("/agregarEstad")
    public String agregar(EstadoCivil estadoCivil) {
        return "estadoCivil/formEstadoCivil";
    }
    
    @PostMapping("/guardarEstad")
    public String guardar(@Valid EstadoCivil estadoCivil, Errors errores) {
        EstadoCivil estadoCivilEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (estadoCivil.getIdEstadoCivil() == null) {
            estadoCivilService.save(estadoCivil);
        } else {
            estadoCivilEnty = estadoCivilService.uptadeEstadoCivil(estadoCivil);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editestadoCivil/{id}")
    public String editar(@PathVariable(value = "id") Integer id,EstadoCivil estadoCivil, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        estadoCivil = estadoCivilService.findById(id);
        model.addAttribute("estadoCivil", estadoCivil);
        return "estadoCivil/formEstadoCivil";
    }

    @GetMapping("/updestadoCivil")
    public String update(EstadoCivil estadoCivil, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        EstadoCivil estadoCivilEnty = estadoCivilService.uptadeEstadoCivil(estadoCivil);
        model.addAttribute("estadoCivil", estadoCivilEnty);
        return "/";
    }

    @GetMapping("/listEstad")
    public String card(Model model) {
        List<EstadoCivil> listaEstadoCivil = estadoCivilService.findAll();
        model.addAttribute("estadoCivils", listaEstadoCivil);
        return "estadoCivil/listaEstadoCivils";//"index";
    }
    
}
