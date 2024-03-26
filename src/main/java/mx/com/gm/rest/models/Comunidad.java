
package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Comunidad implements Serializable{
    
    private Integer idComunidad;    
    private String comunidad;
    private String imagen;    
    private Boolean estatus;
    private Grupo grupo;
    
    private static final long serialVersionUID = 1L;
    
}
