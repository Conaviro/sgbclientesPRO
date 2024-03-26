package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Comunidad;
import mx.com.gm.rest.models.service.ComunidadService;
import mx.com.gm.rest.models.service.GrupoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class ComunidadWebController {

    @Autowired
    private ComunidadService comunidadService;

    @Autowired
    private GrupoServiceImpl grupoServiceImpl;

    @GetMapping("/agregar")
    public String agregar(Comunidad comunidad) {
        return "comunidad/formComunidad";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardar")
    public String guardar(@Valid Comunidad comunidad, Errors errores) {
        Comunidad comunidadEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (comunidad.getIdComunidad() == null) {
            comunidadService.save(comunidad);
        } else {
            comunidadEnty = comunidadService.uptadeComunidad(comunidad);
        }
        return "redirect:/";
    }

    // @PostMapping( "/editcomunidad/{id}")
    // @PutMapping(path = "/editcomunidad/{id}", consumes = {"application/json; charset=utf-8"})
    @GetMapping("/editcomunidad/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Comunidad comunidad, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        comunidad = comunidadService.findById(id);
        model.addAttribute("comunidad", comunidad);
        return "comunidad/formComunidad";
    }

    @GetMapping("/updcomunidad")
    public String update(Comunidad comunidad, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Comunidad comunidadEnty = comunidadService.uptadeComunidad(comunidad);
        model.addAttribute("comunidad", comunidadEnty);
        return "/";
    }

    @GetMapping("/list")
    public String card(Model model) {
        List<Comunidad> listaComuni = comunidadService.findAll();;
        model.addAttribute("comunidads", listaComuni);
        return "comunidad/listaComunidads";//"index";
    }

    /**
     * Agregamos al Model la lista de Grupos: De esta forma nos evitamos
     * agregarlos en los metodos crear y editar.
     *
     * @return
     */
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("grupos", grupoServiceImpl.findAll());
    }
}
