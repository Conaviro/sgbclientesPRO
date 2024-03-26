
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Genero;

public interface GeneroService {
    
    public List<Genero> findAll();
    public void save(Genero genero);
    public Genero uptadeGenero(Genero genero);
    public void deleteGenero(Integer id);
    public Genero findById(Integer id);
    
}
