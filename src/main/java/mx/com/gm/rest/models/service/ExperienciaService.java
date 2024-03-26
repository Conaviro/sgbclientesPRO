
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Experiencia;

public interface ExperienciaService {
    
    public List<Experiencia> findAll();
    public void save(Experiencia experiencia);
    public Experiencia uptadeExperiencia(Experiencia experiencia);
    public void deleteExperiencia(Integer id);
    public Experiencia findById(Integer id);
}
