
package mx.com.gm.rest.models;

//import com.ug.ec.domain.Comunidad;
//import com.ug.ec.domain.Direccion;
//import com.ug.ec.domain.EstadoCivil;
//import com.ug.ec.domain.Genero;
//import com.ug.ec.domain.HojaVida;
//import com.ug.ec.domain.Oferta;
//import com.ug.ec.domain.TipoIdentificacion;
//import com.ug.ec.domain.TipoLicencia;
//import com.ug.ec.domain.TipoVehiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Persona implements Serializable {
    
    
    
    private Long idPersona;   
    private String identificacion;    
    private String nombre;    
    private String apellido;
    private byte[] foto;  
    
   // @Temporal(TemporalType.DATE)
   // @Past
    //@JsonFormat(pattern = "MMM dd, yyyy") 
    
    //@JsonFormat(pattern = "yyyy-MM-dd")

    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date fechaNacimiento; 
    
    private String email;    
    private Short viajar;
    private Short discapacidad;
    private short estatus;      
    private EstadoCivil estadoCivil;    
    private Genero genero;    
    private TipoIdentificacion tipoIdentificacion;    
    private TipoLicencia licencia;   
    private TipoVehiculo tipoVehiculo;   
    private Comunidad comunidad;
    private List<Direccion> direccion;
    
   // private Direccion direccion;// = new ArrayList<>();
//    
//    private List<HojaVida> hojaVida = new ArrayList<>();
//    
//     private Set<Oferta> oferta = new HashSet<Oferta>();
    
    
    private static final long serialVersionUID = 1L;
    
}
