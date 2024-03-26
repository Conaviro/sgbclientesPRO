
package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class OficioHoja implements Serializable {
    OficioHojaKey id;
    String descripcion;
    Oficio oficio;
    private static final long serialVersionUID = 1L;
}
