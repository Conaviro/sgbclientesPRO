package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Idioma;
import mx.com.gm.rest.models.service.IdiomaService;
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
public class IdiomaWebController {
    
    @Autowired
    private IdiomaService idiomaService;

    @GetMapping("/agregaridioma")
    public String agregar(Idioma idioma) {
        return "idioma/formIdioma";
    }
    
    @PostMapping("/guardaridioma")
    public String guardar(@Valid Idioma idioma, Errors errores) {
        Idioma idiomaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (idioma.getIdIdioma()== null) {
            idiomaService.save(idioma);
        } else {
                idiomaEnty = idiomaService.uptadeIdioma(idioma);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editidioma/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Idioma idioma, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        idioma = idiomaService.findById(id);
        model.addAttribute("idioma", idioma);
        return "idioma/formIdioma";
    }

    @GetMapping("/updidioma")
    public String update(Idioma idioma, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Idioma idiomaEnty = idiomaService.uptadeIdioma(idioma);
        model.addAttribute("idioma", idiomaEnty);
        return "/";
    }

    @GetMapping("/listidioma")
    public String card(Model model) {
        List<Idioma> listaIdioma = idiomaService.findAll();
        model.addAttribute("idiomas", listaIdioma);
        return "idioma/listaIdiomas";//"index";
    }
    
}
