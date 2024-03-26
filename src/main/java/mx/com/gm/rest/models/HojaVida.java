

package mx.com.gm.rest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class HojaVida implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long idHojaVida;    
    private String objetivo;
    private double salario;
    private Integer tipoPostulante;
    private Persona persona;

    
}
