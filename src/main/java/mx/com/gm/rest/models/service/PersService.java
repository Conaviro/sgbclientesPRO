
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Persona;


public interface PersService {
    public List<Persona> findAll();
    public void save(Persona persona);
    public Persona uptadePersona(Persona persona);
    public void deletePersona(Long id);
    public Persona findById(Long id);
}
