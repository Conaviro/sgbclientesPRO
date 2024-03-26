package mx.com.gm.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Comunidad;
import mx.com.gm.rest.models.Direccion;
import mx.com.gm.rest.models.DireccionPk;
import mx.com.gm.rest.models.HojaVida;
import mx.com.gm.rest.models.Oficio;
import mx.com.gm.rest.models.OficioHoja;
import mx.com.gm.rest.models.OficioHojaKey;
import mx.com.gm.rest.models.Persona;
import mx.com.gm.rest.models.ReportRepository;
import mx.com.gm.rest.models.TipoDireccion;
import mx.com.gm.rest.models.TipoIdentificacion;
import mx.com.gm.rest.models.service.CantonServiceImpl;
import mx.com.gm.rest.models.service.DireccionService;
import mx.com.gm.rest.models.service.EstadoCivilServiceImpl;
import mx.com.gm.rest.models.service.GeneroServiceImpl;
import mx.com.gm.rest.models.service.HojaVidaService;
import mx.com.gm.rest.models.service.OficioService;
import mx.com.gm.rest.models.service.ParroquiaServiceImpl;
import mx.com.gm.rest.models.service.PersService;
import mx.com.gm.rest.models.service.PersServiceImpl;
import mx.com.gm.rest.models.service.ProvinciaServiceImpl;
import mx.com.gm.rest.models.service.TipoDireccionServiceImpl;
import mx.com.gm.rest.models.service.TipoIdentificacionServiceImpl;
import mx.com.gm.rest.models.service.TipoLicenciaServiceImpl;
import mx.com.gm.rest.models.service.TipoVehiculoServiceImpl;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class PersonaWebController {
    
    @Autowired
    private PersServiceImpl persService;//PersonaServices personaService;

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
    private DireccionService direccionService;
    
    @Autowired
    private HojaVidaService hojaVidaService;
    
    @Autowired
    private OficioService oficioService;
    
    private Persona personaGeneral;
    private Direccion direccionGeneral;
    private HojaVida hojaGeneral;
    private List<Oficio> oficioGeneral;
    
    @GetMapping("/agregarPersona")
    public String agregar(Persona persona, Direccion direccion) {
        return "persona/formPersonaDireccion";// formPersona
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardarPersona")
    public String guardar(@Valid Persona persona, Errors errores) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "1999-01-01";
        Date date = sdf.parse(strDate);
        persona.setFechaNacimiento(date);
        
        Comunidad comu = new Comunidad();
        comu.setIdComunidad(1);
        persona.setComunidad(comu);
        
        Persona personaEnty = null;
        if (errores.hasErrors()) {
            return "/";
        }
        if (persona.getIdPersona() == null) {
            persService.save(persona);
        } else {
            personaEnty = persService.uptadePersona(persona);
        }
        return "persona/formPersonaDireccion";//"redirect:/";
    }

    // @PostMapping( "/editcomunidad/{id}")
    // @PutMapping(path = "/editcomunidad/{id}", consumes = {"application/json; charset=utf-8"})
    //@RequestMapping("/editpersona/{id}")  Direccion direccion,  
    @GetMapping("/editpersona/{id}")
    public String editar(@PathVariable(value = "id") Long id, Persona persona, HojaVida hojaVida, Errors errores, Model model) {//Direccion direccion,
        if (errores.hasErrors()) {
            return "/";
        }
        
        personaGeneral = new Persona();
        persona = persService.findById(id);
        
        List<Direccion> direccionPers = direccionService.findByPersona(persona.getIdPersona());
        if (direccionPers.size() > 0) {


            persona.setDireccion(direccionPers);
        }
        try {
            hojaVida = hojaVidaService.findByPersona(persona.getIdPersona());
        oficioGeneral =oficioService.findAll();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
        
        personaGeneral = persona;
        if (direccionPers.size() > 0) {
            model.addAttribute("direccionPk", direccionPers.get(0).getLlave().getIdtipoDireccion());
        } else {
            model.addAttribute("direccionPk", "vacio");
        }

        //////////////////////
//        List<ReportRepository[]> reportDetails =new ArrayList<ReportRepository[]>();
//         reportDetails = ReportRepository.find(persona.getIdPersona());
//        
        //////////////////
        hojaGeneral = new HojaVida();
        hojaGeneral = hojaVida;
        model.addAttribute("persona", persona);
        model.addAttribute("hojaVida", hojaVida);

        // model.addAttribute("direccion", direccion);
        return "persona/formPersonaDireccion";
    }
    
    @GetMapping("/editdireccion/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Direccion direccion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        
        Iterator it = personaGeneral.getDireccion().iterator();
        //Direccion direccion = null;

        while (it.hasNext()) {
            direccion = (Direccion) it.next();
            if (direccion.getLlave().getIdtipoDireccion() == id) {
                model.addAttribute("direccion", direccion);

                // return "redirect:/apiempleado/editdireccion/2";
                //{id} (id= ${direccionPk})
            }
        }

        
        return "direccion/EditformDireccion";
    }
    
    @GetMapping("/agregarDireccion")
    public String agregar(Direccion direccion) {
        
        return "direccion/EditformDireccion";//direccion/formDireccion
    }

    //@PostMapping( value = "/guardar", consumes = "application/json", produces = "application/json")
    @PostMapping("/guardardireccion")
    public String guardar(@Valid Direccion direccion, Errors errores, Model model) {
        Direccion direccionEnty = null;
        if (errores.hasErrors()) {
            return "redirect:/apiempleado/editdireccion/2";
        }
        if (direccion.getLlave().getIdtipoDireccion() == 0) {
            DireccionPk direpk = new DireccionPk();
            
            int dato = 1;
            direpk.setIdPersona(personaGeneral.getIdPersona());
            
            direpk.setIdtipoDireccion(dato);
            direccion.setLlave(direpk);
            //        direccion = direccionService.findById(direpk);
//        direccion.setTipoDireccion(tipoDireccionServiceImpl.findById(id));
//        model.addAttribute("direccion", direccion);
//        return "direccion/EditformDireccion";

            direccionService.save(direccion);
            model.addAttribute("direccion", direccion);
        } else {
            direccionEnty = direccionService.uptadeDireccion(direccion);
            model.addAttribute("direccionEnty", direccion);
            personaGeneral = new Persona();
            personaGeneral = persService.findById(direccionEnty.getLlave().getIdPersona());
            
        }
        // personaGeneral.setDireccion(direccionEnty);

        return "redirect:/apiempleado/editdireccion/" + 1;//direccion.getLlave().getIdtipoDireccion();
    }
    
    private Oficio getOficioPer(Integer id)
    {
        for(Oficio ofi: oficioGeneral)
        {
            if(ofi.getIdOficio()==id)
                return ofi;
        }
        return null;
    }
    
    @GetMapping("/agregarOficioHoja")
    public String agregarOfHo(OficioHoja oficioHoja, Model model) {
        List<OficioHoja> oficioHojalist = hojaVidaService.findByOficioHojaPersona(personaGeneral.getIdPersona());
//        Oficio oficio = null;
//        oficio.setIdOficio(1);
//        oficio.setDescripcion("Perchero");
        int i=0;
        for(OficioHoja ofhoja:oficioHojalist)
        {            
            oficioHojalist.get(i).setOficio(getOficioPer(ofhoja.getId().getIdOficio()));
            i++;
        }
        
        //oficioHojalist.get(0).setOficio(oficio);
        model.addAttribute("oficioHoja", oficioHojalist);
        return "oficio/formOficiosHoja";
    }
    
    @PostMapping("/guardarOficioHoja")
    public String guardarOfHo(@Valid OficioHoja oficioHoja, @RequestParam(name = "idOficio", required = false) Integer idOficio, @RequestParam(name = "descripcion", required = false) String descripcion, Model model, Errors errores) {
        
        if (errores.hasErrors()) {
            return "/";
        }
        if (idOficio != null) {
            Long idHojaVida;
            OficioHojaKey id = new OficioHojaKey();
            id.setIdOficio(idOficio);
            idHojaVida = hojaGeneral.getIdHojaVida();
            id.setIdHojaVida(idHojaVida);
            //oficioHoja=new OficioHoja();
            oficioHoja.setId(id);
            oficioHoja.setDescripcion(descripcion);
            hojaVidaService.saveOficioHoja(oficioHoja);
        }        
        
        return "redirect:/apiempleado/agregarOficioHoja";//apiempleado/editpersona/" + hojaVida.getPersona().getIdPersona();//http://localhost:8686/apiempleado/editpersona/1
    }
    
    @GetMapping("/guardarOficioHoja/{idPersona}")
    public String consultarOfHo(@PathVariable("idPersona") Long idPersona, Errors errores, Model model) {
        
        if (errores.hasErrors()) {
            return "/";
        }
        
        return "redirect:/apiempleado/agregarOficioHoja";//apiempleado/editpersona/" + hojaVida.getPersona().getIdPersona();//http://localhost:8686/apiempleado/editpersona/1
    }

//Direccion direccion,
    @RequestMapping("/contact/{id}")
    public String getContact(@PathVariable(value = "id") Long id, Persona persona, Errors errores, Model model) {
        
        if (errores.hasErrors()) {
            return "/";
        }
//        DireccionPk direccionPk = new DireccionPk();
//
//        direccionPk.setIdPersona(id);
//        direccionPk.setIdtipoDireccion(1);
//
//        if (direccionPk != null) {
//
//            if (direccion.getLlave() == null) {
//                direccion = direccionService.findById(direccionPk);
//            }
//        }
        persona = persService.findById(id);
        model.addAttribute("persona", persona);
        // model.addAttribute("direccion", direccion);
        return "persona/formPersonaDireccion :: tipoDireccion";
        
    }

    //@ResponseBody Direccion @ResponseBody
//    @GetMapping("/findOne")  
//    public String findOne(@RequestParam("valor") Integer id, Direccion direccion, Model model) {
//
////        DireccionPk direccionPk = new DireccionPk();
////        Long iD = (long) 1;
////        direccionPk.setIdPersona(iD);
////        direccionPk.setIdtipoDireccion(id);
//        Iterator it = personaGeneral.getDireccion().iterator();
//        //Direccion direccion = null;
//
//        while (it.hasNext()) {
//            direccion = (Direccion) it.next();
//            if (direccion.getLlave().getIdtipoDireccion() == id) {
//                model.addAttribute("direccion", direccion);
//
//               // return "redirect:/apiempleado/editdireccion/2";
//               //{id} (id= ${direccionPk})
//                	
//            }
//        }
//
////        if (direccionPk != null) {
////
////            if (direccion.getLlave() == null) {
////                return direccionService.findById(direccionPk);
////
////            }
////        }
//       // model.addAttribute("direccion", direccion);
//        return "redirect:/apiempleado/editdireccion/{id} (id="+ id +")";//addBookRepo.findById(id);
//        //return "direccion/EditformDireccion";
//    }
    @GetMapping("/findTwo")
    @ResponseBody
    public Optional<Direccion> findTwo(Integer id) {
        
        DireccionPk direccionPk = new DireccionPk();
        Long iD = (long) 1;
        direccionPk.setIdPersona(iD);
        direccionPk.setIdtipoDireccion(id);
        
        Direccion direccion = new Direccion();
        if (direccionPk != null) {
            
            if (direccion.getLlave() == null) {
                return direccionService.findByIdOp(direccionPk);
                
            }
        }
        // model.addAttribute("direccion", direccion);
        return null;//addBookRepo.findById(id);
    }

    //@RequestParam("tipoDireccion") TipoDireccion tipoDireccion,Direccion direccion, Errors errores,
    //@GetMapping("/ajax/tipoDireccions")
    //,Direccion direccion, Errors errores
//    @RequestMapping(value = "/refreshItem", method = RequestMethod.GET)
//    public String refreshItem(@RequestParam("tipDireccion") TipoDireccion tipDireccion, Model model) {
//
//        DireccionPk direccionPk = new DireccionPk();
//        Long id = (long) 1;
//        direccionPk.setIdPersona(id);
//        direccionPk.setIdtipoDireccion(2);
//
//        Direccion direccion = new Direccion();
//        if (direccionPk != null) {
//
//            if (direccion.getLlave() == null) {
//                direccion = direccionService.findById(direccionPk);
//            }
//        }
////        persona = persService.findById(id);
////        model.addAttribute("persona", persona);
////        model.addAttribute("dat", "hola");
//
//        /////
////                direccion = null;//direccionService.findById(brand);
//        model.addAttribute("direccion", direccion);
//        return "direccion/formDireccion:: tipoDireccion";
//    }
    @PostMapping(value = "/find_direccionper", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String findDireccionPer(@RequestBody Persona idpersona, Model model) {
        Direccion direccion = direccionService.findById(idpersona.getDireccion().get(0).getLlave());
        
        model.addAttribute("direccion", direccion);
        return "direccion/EditformDireccion";
    }
    
    @GetMapping("/updpersona")
    public String update(Persona persona, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "/";
        }
        Persona personaEnty = persService.uptadePersona(persona);
        model.addAttribute("persona", personaEnty);
        return "/";
    }
    
    @GetMapping("/listpersona")
    public String card(Model model) {
        List<Persona> listaPerso = persService.findAll();
        model.addAttribute("personas", listaPerso);
        return "persona/listaPersonas";
    }
    
    @GetMapping("/ajax")
    public String ajax(Model model) {
        
        return "persona/ajax";
    }
    
    @GetMapping("/peticion")
    public String peticion(Model model, @RequestParam("valor") Integer valor, Direccion direccion, Errors errores) {
        
        DireccionPk direccionPk = new DireccionPk();
        Long id = (long) 1;
        direccionPk.setIdPersona(id);
        direccionPk.setIdtipoDireccion(2);
        
        if (direccionPk != null) {
            
            if (direccion.getLlave() == null) {
                direccion = direccionService.findById(direccionPk);
            }
        }
        model.addAttribute("direccion", direccion);
        model.addAttribute("valor", valor);
        return "direccion/formDireccion";//"persona/peticion";
    }

    /////////////////////////////////////////////////
    ///////////////////////////////////////////////
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
//    public String editar(@PathVariable(value = "id") Integer id, Direccion direccion, Errors errores, Model model) {
//        if (errores.hasErrors()) {
//            return "/";
//        }
//        DireccionPk direpk=new DireccionPk();
//        direpk.setIdPersona(personaGeneral.getIdPersona());
//        direpk.setIdtipoDireccion(id);
//        direccion = direccionService.findById(direpk);
//        model.addAttribute("direccion", direccion);
//        return "direccion/EditformDireccion";
//    }
//    
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
    ////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("estadoCivils", estadoCivilServiceImpl.findAll());
        model.addAttribute("generos", generoServiceImpl.findAll());
        model.addAttribute("tipoIndentificacions", tipoIdentificacionServiceImpl.findAll());
        model.addAttribute("licencias", tipoLicenciaServiceImpl.findAll());
        model.addAttribute("tipoVehiculos", tipoVehiculoServiceImpl.findAll());
        
        model.addAttribute("tipoDireccions", tipoDireccionServiceImpl.findAll());
        model.addAttribute("parroquias", parroquiaServiceImpl.findAll());
        model.addAttribute("cantons", cantonServiceImpl.findAll());
        model.addAttribute("provincias", provinciaServiceImpl.findAll());
        model.addAttribute("oficios", oficioService.findAll());
    }
}
