package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data

public class Area implements Serializable{
   
    private Integer idArea;
    private String nombreArea;
  
    private static final long serialVersionUID = 1L;
}