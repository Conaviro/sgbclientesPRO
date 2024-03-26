package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.TipoDireccion;

public interface TipoDireccionService {
    
    public List<TipoDireccion> findAll();
    public void save(TipoDireccion tipoDireccion);
    public TipoDireccion uptadeTipoDireccion(TipoDireccion tipoDireccion);
    public void deleteTipoDireccion(Integer id);
    public TipoDireccion findById(Integer id);
    
}
