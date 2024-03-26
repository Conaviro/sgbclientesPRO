package mx.com.gm.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
//@Entity
@Table(name = "tb_canton")
public class Canton implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCanton;

    private String nombreCanton;

    @ManyToOne
    @JoinColumn(name = "idprovincia")
    private Provincia provincia;

    private Integer iddireccion; // Campo iddireccion
    private Integer idoferta;   // Campo idoferta

    private static final long serialVersionUID = 1L; // Declaración de serialVersionUID

    public Canton() {
        // Constructor por defecto
    }
}
