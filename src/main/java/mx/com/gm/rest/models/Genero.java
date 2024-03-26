
package mx.com.gm.rest.models;


//import com.ug.ec.domain.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Genero {
    
    private Integer idGenero;    
    private String nombreGenero;    
    private List<Persona> persona = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    
}
