
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Grupo;
import mx.com.gm.rest.models.service.GrupoService;
import mx.com.gm.rest.models.service.GrupoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class GrupoWebController {
    
     @Autowired
    private GrupoService grupoService;
     
     @Autowired
    private GrupoServiceImpl grupoServiceImpl;

    @GetMapping("/agregarGrup")
    public String agregar(Grupo grupo) {
        return "grupo/formGrupo";
    }
    
    @PostMapping("/guardarGrup")
    public String guardar(@Valid Grupo grupo, Errors errores) {
        Grupo grupoEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (grupo.getIdGrupo() == null) {
            grupoService.save(grupo);
        } else {
                grupoEnty = grupoService.uptadeGrupo(grupo);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editgrupo/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Grupo grupo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        grupo = grupoService.findById(id);
        model.addAttribute("grupo", grupo);
        return "grupo/formGrupo";
    }

    @GetMapping("/updgrupo")
    public String update(Grupo grupo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Grupo grupoEnty = grupoService.uptadeGrupo(grupo);
        model.addAttribute("grupo", grupoEnty);
        return "/";
    }

    @GetMapping("/listGrup")
    public String card(Model model) {
        List<Grupo> listaGrupo = grupoService.findAll();
        model.addAttribute("grupos", listaGrupo);
        return "grupo/listaGrupos";//"index";
    }
    
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("grupos", grupoServiceImpl.findAll());
    }
    
    
}
