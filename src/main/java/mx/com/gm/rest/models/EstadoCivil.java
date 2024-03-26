
package mx.com.gm.rest.models;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EstadoCivil implements Serializable {
    
    private Integer idEstadoCivil;    
    private String nombreEstadoCivil;    
    private List<Persona> persona = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    
}
