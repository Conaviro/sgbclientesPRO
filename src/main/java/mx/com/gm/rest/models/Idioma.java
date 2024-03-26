package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class Idioma implements Serializable{
  
    private Integer idIdioma;
    private String nombreIdioma;
    private short nivelIdioma;

    private static final long serialVersionUID = 1L;
} 