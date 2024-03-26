package mx.com.gm.rest.models;


import java.io.Serializable;
import lombok.Data;


@Data
public class Experiencia implements Serializable{  
   
    private Integer idExperiencia;
    private String descripcion;
    private String tiempoExperiencia;
    
    private static final long serialVersionUID = 1L;
    
} 
