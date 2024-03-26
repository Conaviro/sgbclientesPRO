
package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class DireccionPk implements Serializable {
    
    private Long idPersona;
    private int idtipoDireccion;

        
    private static final long serialVersionUID = 1L;
}
