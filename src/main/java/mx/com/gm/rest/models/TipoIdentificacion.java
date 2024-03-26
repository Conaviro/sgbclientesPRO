
package mx.com.gm.rest.models;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.ug.ec.domain.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TipoIdentificacion implements Serializable {
    
    private Integer idTipoIdentificacion;
    private String nombreTipoIdentificacion;
    private List<Persona> persona = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}
