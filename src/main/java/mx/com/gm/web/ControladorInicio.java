package mx.com.gm.web;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;

import mx.com.gm.rest.models.Comunidad;
import mx.com.gm.rest.models.Oferta;
import mx.com.gm.rest.models.service.AreaService;
import mx.com.gm.rest.models.service.ComunidadService;
import mx.com.gm.rest.models.service.OfertaService;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
	private AreaService serviceAreas;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private OfertaService serviceOfertas;
    
     @Autowired
    private PasswordEncoder passwordEncoder;
    

    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){

             
        return "index";
    }
    
    /**
	 * Método para realizar búsquedas desde el formulario de búsqueda del HomePage
	 * @param vacante
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Oferta oferta, Model model) {
		
		/**
		 * La busqueda de vacantes desde el formulario debera de ser únicamente en Ofertas con estatus 
		 * "Aprobada". Entonces forzamos ese filtrado.
		 */
		oferta.setEstatus("Aprobada");
		
		// Personalizamos el tipo de busqueda...
		ExampleMatcher matcher  = ExampleMatcher.matching().
			// and descripcion like '%?%'
			withMatcher("detalle", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Oferta> example = Example.of(oferta, matcher);
		List<Oferta> lista = serviceOfertas.buscarByExample(example);
		model.addAttribute("ofertas", lista);
		return "index";
	}
	/**
     * Utileria para encriptar texto con el algorito BCrypt
     * @param texto
     * @return
     */
    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
   	}
	
	/**
	 * Metodo que agrega al modelo datos genéricos para todo el controlador
	 * @param model
	 */
	@ModelAttribute
	public void setGenericos(Model model){
		Oferta vacanteSearch = new Oferta();
                
		//vacanteSearch.getEmpresa().reset();
               
		model.addAttribute("search", vacanteSearch);
                
		model.addAttribute("ofertas", serviceOfertas.buscarDestacadas());	
		 
                model.addAttribute("areas", serviceAreas.findAll());	
               
	}
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
    
//    @GetMapping("/agregar")
//    public String agregar(Comunidad comunidad){
//        return "comunidad";
//    }
//    
//    @PostMapping("/guardar")
//    public String guardar(@Valid Comunidad comunidad, Errors errores){
//        if(errores.hasErrors()){
//            return "modificar";
//        }
//       // comunidadServic. .guardar(comunidad);
//        return "redirect:/";
//    }
//    
//     @GetMapping("/ini")
//    public String card(Model model){
//      List<Comunidad> listaComuni =   comunidadService.listaCom();
//       model.addAttribute("comunidads", listaComuni);
//        return "listaComunidads";//"index";
//    }
    
    
    
    
    
/*
      @GetMapping("/crear_perso")
    public String add(@ModelAttribute Persona persona) {       

        return "persona/formularioPersona";
    }

    @PostMapping("/save_perso")
    public String guardar(@ModelAttribute Persona persona, BindingResult result, Model model, RedirectAttributes attributes) {



        if (result.hasErrors()) {

            System.out.println("Existieron errores");
            return "persona/formularioPersona";
        }
    
        personaService.save(persona);
        attributes.addFlashAttribute("msg", "Los datos fueron guardados!");

        return "redirect:/apisacramento/indexPaginatePersona";
    }

    @GetMapping("/editperso/{id}")
    public String editar(@PathVariable("id") Long idPersona, Model model) {        
        
        Persona persona = personaService.findById(idPersona);
        model.addAttribute("persona", persona);
        return "persona/formularioPersona";
    }
    
    */
    
    /*
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
*/
}
