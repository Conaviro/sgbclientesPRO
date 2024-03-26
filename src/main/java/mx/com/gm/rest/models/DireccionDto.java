package mx.com.gm.rest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Luis Pilay
 */
@Data
public class DireccionDto implements Serializable{
    
    //private Integer idDireccion;
    private DireccionPk llave;
   // private int idTipoDomicilio;
    private String callePrincipal;
    private String numeroCasa;
    private String calleSecundaria;
    private String barrio;
    private float ubicacionLat;
    private float ubicacionIng;
    private String telefonoDomicilioUno;
    private String extUno;
    private String telefonoDomicilioDos;
    private String extDos;
    private String celular;
    private String webPage;
    //private TipoDireccion tipoDireccion;
    //private Persona persona;
    private Parroquia parroquia;
    private Canton canton;
    private Provincia provincia;
    
    private static final long serialVersionUID = 1L;
    
}
