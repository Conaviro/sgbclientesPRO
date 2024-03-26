
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.TipoLicencia;


public interface TipoLicenciaService {
    public List<TipoLicencia> findAll();
    public void save(TipoLicencia tipoLicencia);
    public TipoLicencia uptadeTipoLicencia(TipoLicencia tipoLicencia);
    public void deleteTipoLicencia(Integer id);
    public TipoLicencia findById(Integer id);
}
