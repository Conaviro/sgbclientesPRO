package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Provincia;
import mx.com.gm.rest.models.service.ProvinciaService;
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
public class ProvinciaWebController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/agregarprovincia")
    public String agregar(Provincia provincia) {
        return "provincia/formProvincia";
    }

    @PostMapping("/guardarprovincia")
    public String guardar(@Valid Provincia provincia, Errors errores) {
        Provincia provinciaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (provincia.getIdProvincia() == null) {
            provinciaService.save(provincia);
        } else {
            provinciaService.updateProvincia(provincia);
        }
        return "redirect:/";
    }

    @GetMapping("/editprovincia/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Provincia provincia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        provincia = provinciaService.findById(id);
        model.addAttribute("provincia", provincia);
        return "provincia/formProvincia";
    }

    @PostMapping("/updateprovincia")
    public String update(@Valid Provincia provincia, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Provincia provinciaEntity = provinciaService.updateProvincia(provincia);
        model.addAttribute("provincia", provinciaEntity);
        return "/";
    }

    @GetMapping("/listaprovincias")
    public String card(Model model) {
        List<Provincia> listaProvincias = provinciaService.findAll();
        model.addAttribute("provincias", listaProvincias);
        return "provincia/listaProvincias";//"index";
    }
}
