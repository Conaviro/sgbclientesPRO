
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Pais;


public interface PaisService {
    public List<Pais> findAll();
    public void save(Pais pais);
    public Pais uptadePais(Pais pais);
    public void deletePais(Integer id);
    public Pais findById(Integer id);
}
