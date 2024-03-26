package mx.com.gm.rest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Grupo implements Serializable {

    private Integer idGrupo;
    private String nombreGrupo;
    //private List<Comunidad> comunidad = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
}
