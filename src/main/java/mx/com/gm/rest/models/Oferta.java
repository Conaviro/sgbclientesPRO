
package mx.com.gm.rest.models;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Oferta implements Serializable{
    
    private Long idOferta;
    private String tituloOferta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;
    private int tipoEmpleo;
    private double salario;
    private String lugar;
    private String detalle;
    private Integer destacado;// Valores [0, 1]. 0: No se muestra en la pag. principal | 1: Se muestra en la pagina principal.
    private String estatus; // Valores [Creado, Aprobado, Eliminado].
    private Canton canton;  
    private Provincia provincia;
    private Empresa empresa;
    private Area area;
    
    
    
    private static final long serialVersionUID = 1L;
}
