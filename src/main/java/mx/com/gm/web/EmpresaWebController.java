package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Empresa;
import mx.com.gm.rest.models.service.EmpresaService;
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
public class EmpresaWebController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/agregarempresa")
    public String agregar(Empresa empresa) {
        return "empresa/formEmpresa";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardarempresa")
    public String guardar(@Valid Empresa empresa, Errors errores) {
        Empresa empresaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (empresa.getIdEmpresa()== null) {
            empresaService.save(empresa);
        } else {
            empresaEnty = empresaService.uptadeEmpresa(empresa);
        }
        return "redirect:/";
    }

    @GetMapping("/editempresa/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Empresa empresa, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        empresa = empresaService.findById(id);
        model.addAttribute("empresa", empresa);
        return "empresa/formEmpresa";
    }

    @GetMapping("/updempresa")
    public String update(Empresa empresa, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Empresa empresaEnty = empresaService.uptadeEmpresa(empresa);
        model.addAttribute("empresa", empresaEnty);
        return "/";
    }

    @GetMapping("/listempresa")
    public String card(Model model) {
        List<Empresa> listaEmpresa = empresaService.findAll();;
        model.addAttribute("empresas", listaEmpresa);
        return "empresa/listaEmpresas";//"index";
    }

}
