
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.TipoIdentificacion;


public interface TipoIdentificacionService {
    public List<TipoIdentificacion> findAll();
    public void save(TipoIdentificacion tipoIdentificacion);
    public TipoIdentificacion uptadeTipoIdentificacion (TipoIdentificacion tipoIdentificacion);
    public void deleteTipoIdentificacion(Integer id);
    public TipoIdentificacion findById(Integer id);
}
