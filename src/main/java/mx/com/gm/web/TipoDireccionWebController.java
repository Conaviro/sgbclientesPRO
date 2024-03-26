package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.TipoDireccion;
import mx.com.gm.rest.models.service.TipoDireccionService;
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
public class TipoDireccionWebController {
    
   @Autowired 
   private TipoDireccionService tipoDireccionService;
    
    @GetMapping("/agregartipodir")
    public String agregar(TipoDireccion tipoId) {
        return "tipoDireccion/formTipoDireccion";
    }
    
    @PostMapping("/guardartipodir")
    public String guardar(@Valid TipoDireccion tipoId, Errors errores) {
        TipoDireccion tipoIdEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if ( tipoId.getIdTipoDireccion() == null) {
            tipoDireccionService.save(tipoId);
        }
        else {
            tipoIdEnty = tipoDireccionService.uptadeTipoDireccion(tipoId);
        }
        return "redirect:/";
    }
    
    @GetMapping("/edittipodir/{id}")
    public String editar(@PathVariable(value = "id") Integer id,TipoDireccion tipoDireccion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        tipoDireccion = tipoDireccionService.findById(id);
        model.addAttribute("tipoDireccion", tipoDireccion);
        return "tipoDireccion/formTipoDireccion";
    }
    
    @GetMapping("/updtipodir")
    public String update(TipoDireccion tipoDireccion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        TipoDireccion tipoIdEnty = tipoDireccionService.uptadeTipoDireccion(tipoDireccion);
        model.addAttribute("tipoDireccion", tipoIdEnty);
        return "/";
    }

    @GetMapping("/listtiposdir")
    public String card(Model model) {
        List<TipoDireccion> listaTiposD = tipoDireccionService.findAll();
        model.addAttribute("tiposDir", listaTiposD);
        return "tipoDireccion/listaTipoDireccion";//"index";
    }
}
