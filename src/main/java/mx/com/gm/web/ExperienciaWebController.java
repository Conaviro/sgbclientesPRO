
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Experiencia;
import mx.com.gm.rest.models.service.ExperienciaService;
import mx.com.gm.rest.models.service.ExperienciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class ExperienciaWebController {
    
    @Autowired
    private ExperienciaService experienciaService;
     
     @Autowired
    private ExperienciaServiceImpl experienciaServiceImpl;

    @GetMapping("/agregarExperiencia")
    public String agregar(Experiencia experiencia) {
        return "experiencia/formExperiencia";
    }
    
    @PostMapping("/guardarExperiencia")
    public String guardar(@Valid Experiencia experiencia, Errors errores) {
        Experiencia experienciaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (experiencia.getIdExperiencia() == null) {
            experienciaService.save(experiencia);
        } else {
                experienciaEnty = experienciaService.uptadeExperiencia(experiencia);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editexperiencia/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Experiencia experiencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        experiencia = experienciaService.findById(id);
        model.addAttribute("experiencia", experiencia);
        return "experiencia/formExperiencia";
    }

    @GetMapping("/updexperiencia")
    public String update(Experiencia experiencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Experiencia experienciaEnty = experienciaService.uptadeExperiencia(experiencia);
        model.addAttribute("experiencia", experienciaEnty);
        return "/";
    }

    @GetMapping("/listExperiencia")
    public String card(Model model) {
        List<Experiencia> listaExperiencia = experienciaService.findAll();
        model.addAttribute("experiencias", listaExperiencia);
        return "experiencia/listaExperiencias";//"index";
    }
    
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("experiencias", experienciaServiceImpl.findAll());
    }

}
