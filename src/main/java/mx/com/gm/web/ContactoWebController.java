
package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Contacto;
import mx.com.gm.rest.models.service.ContactoService;
import mx.com.gm.rest.models.service.EmpresaServiceImpl;

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

/**
 *
 * @author Gustavo Jimenez
 */

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class ContactoWebController {
    
    @Autowired
    private ContactoService contactoService;

    @Autowired
    private EmpresaServiceImpl empresaServiceImpl;

    @GetMapping("/agregarContac")
    public String agregar(Contacto contacto) {
        return "contacto/formContacto";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardarcontac")
    public String guardar(@Valid Contacto contacto, Errors errores) {
        Contacto contactoEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (contacto.getIdContacto() == null) {
            contactoService.save(contacto);
        } else {
            contactoEnty = contactoService.uptadeContacto(contacto);
        }
        return "redirect:/";
    }

    // @PostMapping( "/editcomunidad/{id}")
    // @PutMapping(path = "/editcomunidad/{id}", consumes = {"application/json; charset=utf-8"})
    @GetMapping("/editcontacto/{id}")
    public String editar(@PathVariable(value = "id") Integer id,Contacto contacto, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        contacto = contactoService.findById(id);
        model.addAttribute("contacto", contacto);
        return "contacto/formContacto";
    }

    @GetMapping("/updcontacto")
    public String update(Contacto contacto, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Contacto contactoEnty = contactoService.uptadeContacto(contacto);
        model.addAttribute("comunidad", contactoEnty);
        return "/";
    }

    @GetMapping("/listcontacto")
    public String card(Model model) {
        List<Contacto> listaContac = contactoService.findAll();
        model.addAttribute("contactos", listaContac);
        return "contacto/listaContactos";//"index";
    }

    /**
     * Agregamos al Model la lista de Grupos: De esta forma nos evitamos
     * agregarlos en los metodos crear y editar.
     *
     * @return
     */
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("empresas", empresaServiceImpl.findAll());
    }
    
}
