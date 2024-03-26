package mx.com.gm.rest.models;


import java.io.Serializable;

import lombok.Data;

@Data
public class Referencia implements Serializable{
    
    private Integer idReferencia;
    private String nombreReferencia;
    private String telefonoReferencia;
    private static final long serialVersionUID = 1L;

}
