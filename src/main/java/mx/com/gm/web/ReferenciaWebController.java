
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Referencia;
import mx.com.gm.rest.models.service.ReferenciaService;
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
public class ReferenciaWebController {
    
    @Autowired
    private ReferenciaService referenciaService;
     

    @GetMapping("/agregarRef")
    public String agregar(Referencia referencia) {
        return "referencia/formReferencia";
    }
    
    @PostMapping("/guardarRef")
    public String guardar(@Valid Referencia referencia, Errors errores) {
        Referencia referenciaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (referencia.getIdReferencia()== null) {
            referenciaService.save(referencia);
        } else {
                referenciaEnty = referenciaService.uptadeReferencia(referencia);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editRef/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Referencia referencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        referencia = referenciaService.findById(id);
        model.addAttribute("referencia", referencia);
        return "referencia/formReferencia";
    }

    @GetMapping("/updRef")
    public String update(Referencia referencia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Referencia referenciaEnty = referenciaService.uptadeReferencia(referencia);
        model.addAttribute("referencia", referenciaEnty);
        return "/";
    }

    @GetMapping("/listRef")
    public String card(Model model) {
        List<Referencia> listaReferencia = referenciaService.findAll();
        model.addAttribute("referencias", listaReferencia);
        return "referencia/listaReferencias";//"index";
    }
    
}
