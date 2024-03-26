
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Comunidad;


public interface ComunidadService {
    public List<Comunidad> findAll();
    public void save(Comunidad comunidad);
    public Comunidad uptadeComunidad(Comunidad comunidad);
    public void deleteComunidad(Integer id);
    public Comunidad findById(Integer id);
}
