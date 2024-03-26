
package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class PaginaController {
    
     @GetMapping("/boton")
    public String Bot(){
        return "index";
    }
    
     @GetMapping("/card")
    public String Car(){
        return "cards";
    }
    
}
