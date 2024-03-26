
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Genero;
import mx.com.gm.rest.models.service.GeneroService;
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
public class GeneroWebController {
    
    @Autowired
    private GeneroService generoService;

    @GetMapping("/agregarGene")
    public String agregar(Genero genero) {
        return "genero/formGenero";
    }
    
    @PostMapping("/guardarGene")
    public String guardar(@Valid Genero genero, Errors errores) {
        Genero generoEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (genero.getIdGenero() == null) {
            generoService.save(genero);
        } else {
                generoEnty = generoService.uptadeGenero(genero);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editgenero/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Genero genero, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        genero = generoService.findById(id);
        model.addAttribute("genero", genero);
        return "genero/formGenero";
    }

    @GetMapping("/updgenero")
    public String update(Genero genero, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Genero generoEnty = generoService.uptadeGenero(genero);
        model.addAttribute("genero", generoEnty);
        return "/";
    }

    @GetMapping("/listGene")
    public String card(Model model) {
        List<Genero> listaGenero = generoService.findAll();
        model.addAttribute("generos", listaGenero);
        return "genero/listaGeneros";//"index";
    }
    
}
