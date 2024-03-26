
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Referencia;

public interface ReferenciaService {
    
    public List<Referencia> findAll();
    public void save(Referencia referencia);
    public Referencia uptadeReferencia(Referencia referencia);
    public void deleteReferencia(Integer id);
    public Referencia findById(Integer id);
}
