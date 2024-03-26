package mx.com.gm.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.TipoVehiculo;
import mx.com.gm.rest.models.service.TipoVehiculoService;

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
public class TipoVehiculoWebController {

    @Autowired
    private TipoVehiculoService tipoVehiculoService;


    @GetMapping("/agregartiposVehiculo")
    public String agregar(TipoVehiculo tipoVehiculo) {
        return "tipoVehiculo/formTipoVehiculo";
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardartiposVehiculo")
    public String guardar(@Valid TipoVehiculo tipoVehiculo, Errors errores) {
        TipoVehiculo tipoVehiculoEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (tipoVehiculo.getIdTipoVehiculo()== null) {
            tipoVehiculoService.save(tipoVehiculo);
        } else {
            tipoVehiculoEnty = tipoVehiculoService.uptadeTipoVehiculo(tipoVehiculo);
        }
        return "redirect:/";
    }

    
    @GetMapping("/edittipovehiculo/{id}")
    public String editar(@PathVariable(value = "id") Integer id,TipoVehiculo tipovehiculo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        tipovehiculo = tipoVehiculoService.findById(id);
        model.addAttribute("tipoVehiculo", tipovehiculo);
        return "tipoVehiculo/formTipoVehiculo";
    }

    @GetMapping("/updtipovehiculo")
    public String update(TipoVehiculo tipovehiculo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        TipoVehiculo tipoVehiculoEnty = tipoVehiculoService.uptadeTipoVehiculo(tipovehiculo);
        model.addAttribute("tipoVehiculo", tipoVehiculoEnty);
        return "/";
    }

    @GetMapping("/listtiposVehiculo")
    public String card(Model model) {
        List<TipoVehiculo> listaTiposV = tipoVehiculoService.findAll();
        model.addAttribute("tiposVehiculo", listaTiposV);
        return "tipoVehiculo/listaTiposVehiculos";//"index";
    }

    /**
     * Agregamos al Model la lista de Grupos: De esta forma nos evitamos
     * agregarlos en los metodos crear y editar.
     *
     * @return
     */
   
}
