
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Area;
import mx.com.gm.rest.models.service.AreaService;
import mx.com.gm.rest.models.service.AreaServiceImpl;
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
public class AreaWebController {
    
    @Autowired
    private AreaService areaService;
     
     @Autowired
    private AreaServiceImpl areaServiceImpl;

    @GetMapping("/agregarArea")
    public String agregar(Area area) {
        return "area/formArea";
    }
    
    @PostMapping("/guardarArea")
    public String guardar(@Valid Area area, Errors errores) {
        Area areaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (area.getIdArea() == null) {
            areaService.save(area);
        } else {
                areaEnty = areaService.uptadeArea(area);
        }
        return "redirect:/";
    }
    
    @GetMapping("/editarea/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Area area, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        area = areaService.findById(id);
        model.addAttribute("area", area);
        return "area/formArea";
    }

    @GetMapping("/updarea")
    public String update(Area area, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Area areaEnty = areaService.uptadeArea(area);
        model.addAttribute("area", areaEnty);
        return "/";
    }

    @GetMapping("/listArea")
    public String card(Model model) {
        List<Area> listaArea = areaService.findAll();
        model.addAttribute("areas", listaArea);
        return "area/listaAreas";//"index";
    }
    
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("areas", areaServiceImpl.findAll());
    }
    
    
    
}
