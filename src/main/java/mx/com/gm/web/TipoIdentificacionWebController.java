package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.TipoIdentificacion;
import mx.com.gm.rest.models.service.TipoIdentificacionService;

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
public class TipoIdentificacionWebController {

    @Autowired
    private TipoIdentificacionService tipoIdentificacionService;


    @GetMapping("/agregartipoId")
    public String agregar(TipoIdentificacion tipoId) {
        return "tipoIdentificacion/formTipoIdentificacion";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardartipoId")
    public String guardar(@Valid TipoIdentificacion tipoId, Errors errores) {
        TipoIdentificacion tipoIdEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if ( tipoId.getIdTipoIdentificacion() == null) {
            tipoIdentificacionService.save(tipoId);
        }
        else {
            tipoIdEnty = tipoIdentificacionService.uptadeTipoIdentificacion(tipoId);
        }
        return "redirect:/";
    }

    
    @GetMapping("/edittipoId/{id}")
    public String editar(@PathVariable(value = "id") Integer id,TipoIdentificacion tipoIdentificacion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        tipoIdentificacion = tipoIdentificacionService.findById(id);
        model.addAttribute("tipoIdentificacion", tipoIdentificacion);
        return "tipoIdentificacion/formTipoIdentificacion";
    }

    @GetMapping("/updtipoId")
    public String update(TipoIdentificacion tipoIdentificacion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        TipoIdentificacion tipoIdEnty = tipoIdentificacionService.uptadeTipoIdentificacion(tipoIdentificacion);
        model.addAttribute("tipoIdentificacion", tipoIdEnty);
        return "/";
    }

    @GetMapping("/listtiposId")
    public String card(Model model) {
        List<TipoIdentificacion> listaTiposI = tipoIdentificacionService.findAll();
        model.addAttribute("tiposId", listaTiposI);
        return "tipoIdentificacion/listaTipoIdentificacion";//"index";
    }
}
