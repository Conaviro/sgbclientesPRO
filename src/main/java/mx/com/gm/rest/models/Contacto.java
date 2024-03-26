package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Gustavo Jimenez
 */

@Data
public class Contacto implements Serializable{

    private Integer idContacto;
    private String identificacion;
    private String nombre;
    private String apellido;
    private Empresa empresa;
    
    private static final long serialVersionUID = 1L;
    
    
}
