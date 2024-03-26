package mx.com.gm.web;

import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import mx.com.gm.rest.models.Direccion;
import mx.com.gm.rest.models.DireccionPk;
import mx.com.gm.rest.models.Persona;
import mx.com.gm.rest.models.TipoDireccion;
import mx.com.gm.rest.models.service.CantonServiceImpl;
import mx.com.gm.rest.models.service.DireccionService;
import mx.com.gm.rest.models.service.EstadoCivilServiceImpl;
import mx.com.gm.rest.models.service.GeneroServiceImpl;
import mx.com.gm.rest.models.service.ParroquiaServiceImpl;
import mx.com.gm.rest.models.service.ProvinciaServiceImpl;
import mx.com.gm.rest.models.service.TipoDireccionServiceImpl;
import mx.com.gm.rest.models.service.TipoIdentificacionServiceImpl;
import mx.com.gm.rest.models.service.TipoLicenciaServiceImpl;
import mx.com.gm.rest.models.service.TipoVehiculoServiceImpl;
import mx.com.gm.rest.models.service.PersServiceImpl;
//import mx.com.gm.rest.models.service.PersonaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gustavo Jimenez
 */
@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class DireccionWebController {

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private EstadoCivilServiceImpl estadoCivilServiceImpl;

    @Autowired
    private TipoIdentificacionServiceImpl tipoIdentificacionServiceImpl;

    @Autowired
    private GeneroServiceImpl generoServiceImpl;

    @Autowired
    private TipoLicenciaServiceImpl tipoLicenciaServiceImpl;

    @Autowired
    private TipoVehiculoServiceImpl tipoVehiculoServiceImpl;

    @Autowired
    private TipoDireccionServiceImpl tipoDireccionServiceImpl;

    @Autowired
    private CantonServiceImpl cantonServiceImpl;

    @Autowired
    private ParroquiaServiceImpl parroquiaServiceImpl;

    @Autowired
    private ProvinciaServiceImpl provinciaServiceImpl;

    @Autowired
    private PersServiceImpl persServiceImpl;

    /*@Autowired
    private PersonaServiceImpl personaServiceImpl;*/
//    @GetMapping("/agregarDireccion")
//    public String agregar(Direccion direccion) {
//        
//        return "direccion/formDireccion";//direccion/formDireccion
//    }
//
//    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
//    @PostMapping("/guardardireccion")
//    public String guardar(@Valid Direccion direccion, Errors errores) {
//        Direccion direccionEnty = null;
//        if (errores.hasErrors()) {
//            return "/";
//        }
//        if (direccion.getLlave() == null) {
//            direccionService.save(direccion);
//        } else {
//            direccionEnty = direccionService.uptadeDireccion(direccion);
//        }
//        return "redirect:/";
//    }
//
//    // @PostMapping( "/editcomunidad/{id}")
//    // @PutMapping(path = "/editcomunidad/{id}", consumes = {"application/json; charset=utf-8"})
//    @GetMapping("/editdireccion/{id}")
//    public String editar(@PathVariable(value = "id") DireccionPk id,Direccion direccion, Errors errores, Model model) {
//        if (errores.hasErrors()) {
//            return "/";
//        }
//        direccion = direccionService.findById(id);
//        model.addAttribute("comunidad", direccion);
//        return "direccion/formDireccion";
//    }
//    
//    @GetMapping("/agregarDireccion")
//    public String agregar(Direccion direccion) {
//
//        return "direccion/formDireccion";//direccion/formDireccion
//    }
//
//    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
//    @PostMapping("/guardardireccion")
//    public String guardar(@Valid Direccion direccion, Errors errores) {
//        Direccion direccionEnty = null;
//        if (errores.hasErrors()) {
//            return "redirect:/apiempleado/editdireccion/2";
//        }
////        if (direccion.getLlave() == null) {
////            direccionService.save(direccion);
////        } else {
////            direccionEnty = direccionService.uptadeDireccion(direccion);
////        }
//        return "redirect:/apiempleado/editdireccion/2";
//    }

    // @PostMapping( "/editcomunidad/{id}")
    // @PutMapping(path = "/editcomunidad/{id}", consumes = {"application/json; charset=utf-8"})
//    @GetMapping("/editdireccion/{id}")
//    public String editar(@PathVariable(value = "id") Integer id, Direccion direccion,Errors errores, Model model) {
//        if (errores.hasErrors()) {
//            return "/";
//        }
//        DireccionPk direpk = new DireccionPk();
//        Long dato = (long) 1;
//        //direpk.setIdPersona(personaGeneral.getIdPersona());
//        direpk.setIdPersona(dato);
//        direpk.setIdtipoDireccion(id);
//        direccion = direccionService.findById(direpk);
//        direccion.setTipoDireccion(tipoDireccionServiceImpl.findById(id));
//        model.addAttribute("direccion", direccion);
//        return "direccion/EditformDireccion";
//    }

    @GetMapping("/findOne")
    @ResponseBody
    public String findOne( Direccion direccion, Errors errores, Model model) {
     //@RequestParam("valor") Integer id,
        if (errores.hasErrors()) {
            return "/";
        }
//        DireccionPk direccionPk = new DireccionPk();
        Long iD = (long) 1;
//        direccionPk.setIdPersona(iD);
//        direccionPk.setIdtipoDireccion(id);
        Iterator it = persServiceImpl.findById(iD).getDireccion().iterator();//personaGeneral.getDireccion().iterator();
        //Direccion direccion = null;

        while (it.hasNext()) {
            direccion = (Direccion) it.next();
            if (direccion.getLlave().getIdtipoDireccion() == 2) {
                model.addAttribute("direccion", direccion);

                // return "redirect:/apiempleado/editdireccion/2";
                //{id} (id= ${direccionPk})
            }
        }

//        if (direccionPk != null) {
//
//            if (direccion.getLlave() == null) {
//                return direccionService.findById(direccionPk);
//
//            }
//        }
        // model.addAttribute("direccion", direccion);
        //return "redirect:/apiempleado/editdireccion/{id} (id="+ id +")";//addBookRepo.findById(id);
        //return "direccion/EditformDireccion";
        return "redirect:/apiempleado/editdireccion/2";
    }

//    @GetMapping("/findOne")
//    @ResponseBody
//    public String findOne(@RequestParam("valor") Integer id, Direccion direccion, Model model) {
//
////        DireccionPk direccionPk = new DireccionPk();
////        Long iD = (long) 1;
////        direccionPk.setIdPersona(iD);
////        direccionPk.setIdtipoDireccion(id);
//        // Iterator it = personaGeneral.getDireccion().iterator();
//        //Direccion direccion = null;
////        while (it.hasNext()) {
////            direccion = (Direccion) it.next();
////            if (direccion.getLlave().getIdtipoDireccion() == id) { 
////                model.addAttribute("direccion", direccion);
////                
////                return direccion;
////            }
////        }
//        model.addAttribute("direccion", direccion);
//        return "direccion/EditformDireccion";
//    }
//    @PostMapping(value = "/find_direccion", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findDireccion(@RequestBody Direccion idreccion) {
//        Direccion personaDb = direccionService.findById(idreccion.getLlave());
//
//        if (personaDb != null) {
//            return new ResponseEntity<>(personaDb, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @PostMapping(value = "/find_direccionper", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String findDireccionPer(@RequestBody Persona idpersona,Model model) {
//        Direccion direccion = direccionService.findById( idpersona.getDireccion().get(0).getLlave());
//
//        model.addAttribute("direccion", direccion);
//        return "direccion/EditformDireccion";
//    }
    @GetMapping("/ajax/tipDireccs")
    public String ajaxBrands(@RequestParam("tipoDireccion") TipoDireccion tipoDireccion, Direccion direccion, Errors errores, Model model) {

        DireccionPk direccionPk = new DireccionPk();
        Long id = (long) 1;
        direccionPk.setIdPersona(id);
        direccionPk.setIdtipoDireccion(2);

        if (direccionPk != null) {

            if (direccion.getLlave() == null) {
                direccion = direccionService.findById(direccionPk);
            }
        }
//        persona = persService.findById(id);
//        model.addAttribute("persona", persona);
//        model.addAttribute("dat", "hola");

        /////
//                direccion = null;//direccionService.findById(brand);
//		model.addAttribute("direccion", direccion);
        return "direccion/formDireccion";
    }

//     @RequestMapping("/ajax/tipoDireccions")
//	public String ajaxBrands(@RequestParam("tipoDireccion") TipoDireccion tipoDireccion,Direccion direccion, Errors errores, Model model) {
//		//List<String> models = carService.findAllModelsByBrand(brand);
//                
//                
//                
//                direccion = null;//direccionService.findById(brand);
//		model.addAttribute("direccion", direccion);
//		return "direccion/formDireccion :: direccion";
//	}
//    @RequestMapping("/ajax/brands")
//	public String ajaxBrands(@RequestParam("brand") DireccionPk id,Direccion direccion, Errors errores, Model model) {
//		//List<String> models = carService.findAllModelsByBrand(brand);
//                direccion = direccionService.findById(id);
//		model.addAttribute("models", direccion);
//		return "direccion/formDireccion :: models";
//	}
    @GetMapping("/upddireccion")
    public String update(Direccion direccion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Direccion direccionEnty = direccionService.uptadeDireccion(direccion);
        model.addAttribute("direccion", direccionEnty);
        return "/";
    }

    @GetMapping("/listdireccion")
    public String card(Model model) {
        List<Direccion> listaDirec = direccionService.findAll();
        model.addAttribute("direccions", listaDirec);
        return "direccion/listaDireccions";//"index";
    }

    /**
     * Agregamos al Model la lista de Grupos: De esta forma nos evitamos
     * agregarlos en los metodos crear y editar.
     *
     * @return
     */
    @ModelAttribute
    public void setGenericos(Model model) {
//        model.addAttribute("estadoCivils", estadoCivilServiceImpl.findAll());
//        model.addAttribute("generos", generoServiceImpl.findAll());
//        model.addAttribute("tipoIndentificacions", tipoIdentificacionServiceImpl.findAll());
//        model.addAttribute("licencias", tipoLicenciaServiceImpl.findAll());
//        model.addAttribute("tipoVehiculos", tipoVehiculoServiceImpl.findAll());
//        
        model.addAttribute("tipoDireccions", tipoDireccionServiceImpl.findAll());
        model.addAttribute("parroquias", parroquiaServiceImpl.findAll());
        model.addAttribute("cantons", cantonServiceImpl.findAll());
        model.addAttribute("provincias", provinciaServiceImpl.findAll());
    }
    /*@ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("grupos", grupoServiceImpl.findAll());
    }*/

}
