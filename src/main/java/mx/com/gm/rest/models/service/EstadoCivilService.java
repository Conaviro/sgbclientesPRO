
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.EstadoCivil;

public interface EstadoCivilService {
    
    public List<EstadoCivil> findAll();
    public void save(EstadoCivil estadoCivil);
    public EstadoCivil uptadeEstadoCivil(EstadoCivil estadoCivil);
    public void deleteEstadoCivil(Integer id);
    public EstadoCivil findById(Integer id);
    
}
