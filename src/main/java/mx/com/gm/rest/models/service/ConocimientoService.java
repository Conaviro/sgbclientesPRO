
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Conocimiento;


public interface ConocimientoService {
    public List<Conocimiento> findAll();
    public void save(Conocimiento conocimiento);
    public Conocimiento uptadeConocimiento(Conocimiento conocimiento);
    public void deleteConocimiento(Integer id);
    public Conocimiento findById(Integer id);
}
