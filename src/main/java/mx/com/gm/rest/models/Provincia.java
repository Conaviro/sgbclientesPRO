package mx.com.gm.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
public class Provincia implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvincia;

    private String nombreProvincia;

    @ManyToOne
    @JoinColumn(name = "idPais")
    private Pais pais;

    //private Integer idDireccion; // Campo iddireccion
    //private Integer idOferta;   // Campo idoferta

    private static final long serialVersionUID = 1L; // Declaración de serialVersionUID
    
    
}
