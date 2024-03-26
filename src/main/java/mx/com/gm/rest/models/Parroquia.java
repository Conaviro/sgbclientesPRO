package mx.com.gm.rest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

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
@Entity // Agregado para indicar que es una entidad JPA
@Table(name = "parroquias") // Especifica el nombre de la tabla si es diferente del nombre de la clase
public class Parroquia implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParroquia;

    private String nombreParroquia;

    //@ManyToOne
    @JoinColumn(name = "idCanton")
    private Canton canton;

    private Integer idDireccion; // Campo iddireccion

    private static final long serialVersionUID = 1L; // Declaración de serialVersionUID

    public Integer getIdParroquia() {
        return idParroquia;
    }
}
