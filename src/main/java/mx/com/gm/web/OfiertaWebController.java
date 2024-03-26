package mx.com.gm.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.rest.models.Oferta;
import mx.com.gm.rest.models.service.OfertaService;
import mx.com.gm.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/apiempleado")
public class OfiertaWebController {

    @Value("${empleosapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/listoferta")
    public String card(Model model) {
        List<Oferta> listaOferta = ofertaService.findAll();
        model.addAttribute("ofertas", listaOferta);
        return "oferta/listaOfertas";
    }

    @GetMapping("/agregarOferta")
    public String crear(@ModelAttribute Oferta oferta) {
        return "oferta/formOferta";
    }

    @PostMapping("/guardarOferta")
    public String guardar(@ModelAttribute Oferta oferta, BindingResult result, Model model,
            @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes) {

        if (result.hasErrors()) {

            System.out.println("Existieron errores");
            return "oferta/formOferta";
        }

        if (!multiPart.isEmpty()) {
            String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            //String ruta = "C:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // La imagen si se subio				
                oferta.getEmpresa().setLogo(nombreImagen);  // Asignamos el nombre de la imagen
            }
        }
        System.out.println("Ruta: " + ruta);

        // Guadamos el objeto oferta en la bd
        ofertaService.save(oferta);
        attributes.addFlashAttribute("msg", "Los datos de la oferta fueron guardados!");

        return "redirect:/apiempleado/listoferta";
    }
    
    @GetMapping("/editOferta/{id}")
	public String editar(@PathVariable("id") Long idOferta, Model model) {		
		Oferta oferta = ofertaService.findById(idOferta);			
		model.addAttribute("oferta", oferta);
		return "oferta/formOferta";
	}
	
	/**
	 * Método que elimina una Vacante de la base de datos
	 * @param idVacante
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idOferta, RedirectAttributes attributes) {
		
		// Eliminamos la vacante.
		ofertaService.deleteOferta(idOferta); 
			
		attributes.addFlashAttribute("msg", "La vacante fue eliminada!.");
		//return "redirect:/vacantes/index";
		return "redirect:/vacantes/indexPaginate";
	}

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
