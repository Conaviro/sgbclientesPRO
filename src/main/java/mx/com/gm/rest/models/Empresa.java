package mx.com.gm.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Empresa implements Serializable{

    private Integer idEmpresa;
    private String nombreEmpresa;
    private String rasonSocial;
    private String logo;
    private String paginaWeb;
    
    public void reset() {
		this.logo=null;
	}

    private static final long serialVersionUID = 1L;
}