
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Grupo;


public interface GrupoService {
     public List<Grupo> findAll();
    public void save(Grupo grupo);
    public Grupo uptadeGrupo(Grupo grupo);
    public void deleteGrupo(Integer id);
    public Grupo findById(Integer id);
}
