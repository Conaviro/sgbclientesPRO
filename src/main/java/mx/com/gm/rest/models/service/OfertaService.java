package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Oferta;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfertaService {

    public List<Oferta> findAll();

    public void save(Oferta oferta);

    public Oferta uptadeOferta(Oferta oferta);

    public void deleteOferta(Long id);

    public Oferta findById(Long id);

    List<Oferta> buscarDestacadas();
    //Page<Vacante> buscarTodas(Pageable page);

    List<Oferta> buscarByExample(Example<Oferta> example);
}
