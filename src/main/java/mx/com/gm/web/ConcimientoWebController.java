package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Conocimiento;
import mx.com.gm.rest.models.service.ConocimientoService;
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
public class ConcimientoWebController {

    @Autowired
    private ConocimientoService conocimientoService;

    @GetMapping("/agregarconocimiento")
    public String agregar(Conocimiento conocimiento) {
        return "conocimiento/formConocimiento";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardarconocimiento")
    public String guardar(@Valid Conocimiento conocimiento, Errors errores) {
        Conocimiento conocimientoEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (conocimiento.getIdConocimiento()== null) {
            conocimientoService.save(conocimiento);
        } else {
            conocimientoEnty = conocimientoService.uptadeConocimiento(conocimiento);
        }
        return "redirect:/";
    }

    @GetMapping("/editconocimiento/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Conocimiento conocimiento, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        conocimiento = conocimientoService.findById(id);
        model.addAttribute("conocimiento", conocimiento);
        return "conocimiento/formConocimiento";
    }

    @GetMapping("/updconocimiento")
    public String update(Conocimiento conocimiento, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Conocimiento conocimientoEnty = conocimientoService.uptadeConocimiento(conocimiento);
        model.addAttribute("conocimiento", conocimientoEnty);
        return "/";
    }

    @GetMapping("/listconocimiento")
    public String card(Model model) {
        List<Conocimiento> listaConoc = conocimientoService.findAll();;
        model.addAttribute("conocimientos", listaConoc);
        return "conocimiento/listaConocimientos";//"index";
    }

}