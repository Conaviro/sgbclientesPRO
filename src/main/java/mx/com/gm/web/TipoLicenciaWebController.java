package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.TipoLicencia;
import mx.com.gm.rest.models.service.TipoLicenciaService;

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
public class TipoLicenciaWebController {

    @Autowired
    private TipoLicenciaService tipoLicenciaService;


    @GetMapping("/agregartipoLic")
    public String agregar(TipoLicencia tipoLicencia) {
        return "tipoLicencia/formTipoLicencia";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardartipoLic")
    public String guardar(@Valid TipoLicencia tipoLicencia, Errors errores) {
        TipoLicencia tipoLicEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if ( tipoLicencia.getIdTipoLicencia()== null) {
            tipoLicenciaService.save(tipoLicencia);
        }
        else {
            tipoLicEnty = tipoLicenciaService.uptadeTipoLicencia(tipoLicencia);
        }
        return "redirect:/";
    }

    
    @GetMapping("/edittipoLic/{id}")
    public String editar(@PathVariable(value = "id") Integer id,TipoLicencia tipoLicencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        tipoLicencia = tipoLicenciaService.findById(id);
        model.addAttribute("tipoLicencia", tipoLicencia);
        return "tipoLicencia/formTipoLicencia";
    }

    @GetMapping("/updtipoLic")
    public String update(TipoLicencia tipoLicencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        TipoLicencia tipoLicEnty = tipoLicenciaService.uptadeTipoLicencia(tipoLicencia);
        model.addAttribute("tipoLicencia", tipoLicEnty);
        return "/";
    }

    @GetMapping("/listtiposLic")
    public String card(Model model) {
        List<TipoLicencia> listaTiposL = tipoLicenciaService.findAll();
        model.addAttribute("tiposLic", listaTiposL);
        return "tipoLicencia/listaTipoLicencia";//"index";
    }
}
