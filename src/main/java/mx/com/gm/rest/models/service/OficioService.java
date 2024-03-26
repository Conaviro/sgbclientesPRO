package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Oficio;

public interface OficioService {
    public List<Oficio> findAll();
    public void save(Oficio oficio);
    public Oficio uptadeOficio (Oficio oficio);
    public void deleteOficio (Integer id);
    public Oficio findById (Integer id);
}
