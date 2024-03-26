
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Idioma;


public interface IdiomaService {
    public List<Idioma> findAll();
    public void save(Idioma idioma);
    public Idioma uptadeIdioma(Idioma idioma);
    public void deleteIdioma(Integer id);
    public Idioma findById(Integer id);
}
