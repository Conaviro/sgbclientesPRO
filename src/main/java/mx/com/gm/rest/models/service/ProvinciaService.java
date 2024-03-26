package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Provincia;


public interface ProvinciaService {
    public List<Provincia> findAll();
    public void save(Provincia provincia);
    public Provincia updateProvincia(Provincia provincia);
    public void deleteProvincia(Integer id);
    public Provincia findById(Integer id);
}
