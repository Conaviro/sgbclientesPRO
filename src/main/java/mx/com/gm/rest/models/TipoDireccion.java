package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class TipoDireccion implements Serializable{
  
    private Integer idTipoDireccion;

    private String nombreTipoDireccion;   

    private static final long serialVersionUID = 1L;

}