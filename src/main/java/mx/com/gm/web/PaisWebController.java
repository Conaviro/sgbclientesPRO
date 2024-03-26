package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Pais;
import mx.com.gm.rest.models.service.PaisService;
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
public class PaisWebController {

    @Autowired
    private PaisService paisService;

    @GetMapping("/agregarpais")
    public String agregar(Pais pais) {
        return "pais/formPais";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardarpais")
    public String guardar(@Valid Pais pais, Errors errores) {
        Pais paisEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (pais.getIdPais()== null) {
            paisService.save(pais);
        } else {
            paisEnty = paisService.uptadePais(pais);
        }
        return "redirect:/";
    }

    @GetMapping("/editpais/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Pais pais, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        pais = paisService.findById(id);
        model.addAttribute("pais", pais);
        return "pais/formPais";
    }

    @GetMapping("/updpais")
    public String update(Pais pais, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Pais paisEnty = paisService.uptadePais(pais);
        model.addAttribute("pais", paisEnty);
        return "/";
    }

    @GetMapping("/listpais")
    public String card(Model model) {
        List<Pais> listaPaises = paisService.findAll();;
        model.addAttribute("paises", listaPaises);
        return "pais/listaPaises";//"index";
    }

}
