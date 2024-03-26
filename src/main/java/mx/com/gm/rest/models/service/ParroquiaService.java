package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Parroquia;

public interface ParroquiaService {
    public List<Parroquia> findAll();
    public void save(Parroquia parroquia);
    public Parroquia updateParroquia(Parroquia parroquia);
    public void deleteParroquia(Integer id);
    public Parroquia findById(Integer id);
}
