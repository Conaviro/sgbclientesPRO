
package mx.com.gm.rest.models;


//import com.ug.ec.domain.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TipoVehiculo implements Serializable {
    
    private Integer idTipoVehiculo;    
    private String nombreTipoVehiculo;
    private List<Persona> persona = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}
