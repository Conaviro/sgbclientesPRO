package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Parroquia;
import mx.com.gm.rest.models.service.ParroquiaService;
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
public class ParroquiaWebController {

    @Autowired
    private ParroquiaService parroquiaService;

    @GetMapping("/agregarparroquia")
    public String agregar(Parroquia parroquia) {
        return "parroquia/formParroquia";
    }

    @PostMapping("/guardarparroquia")
    public String guardar(@Valid Parroquia parroquia, Errors errores) {
        Parroquia parroquiaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (parroquia.getIdParroquia() == null) {
            parroquiaService.save(parroquia);
        } else {
            parroquiaEnty = parroquiaService.updateParroquia(parroquia);
        }
        return "redirect:/";
    }

    @GetMapping("/editparroquia/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Parroquia parroquia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        parroquia = parroquiaService.findById(id);
        model.addAttribute("parroquia", parroquia);
        return "parroquia/formParroquia";
    }

    @GetMapping("/updparroquia")
    public String update(Parroquia parroquia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Parroquia parroquiaEnty = parroquiaService.updateParroquia(parroquia);
        model.addAttribute("parroquia", parroquiaEnty);
        return "/";
    }

    @GetMapping("/listparroquia")
    public String card(Model model) {
        List<Parroquia> listaParroquias = parroquiaService.findAll();
        model.addAttribute("parroquias", listaParroquias);
        return "parroquia/listaParroquias";//"index";
    }
}
