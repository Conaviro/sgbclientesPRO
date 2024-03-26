
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import mx.com.gm.rest.models.Area;

import mx.com.gm.rest.models.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OfertaServiceImpl implements OfertaService {
    
    @Autowired
    private RestTemplate ofertaRest;

    List<Oferta> ofertas;

    @Override
    public List<Oferta> findAll() {
         ofertas = Arrays.asList(ofertaRest.getForObject("http://localhost:8585/bolsatrabajo/oferta", Oferta[].class));

        return ofertas;
    }

    @Override
    public void save(Oferta oferta) {
        //falta actualizar 18022024
        String createOfertaUrl = "http://localhost:8585/bolsatrabajo/crear_area";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Oferta> request = new HttpEntity<>(oferta, headers);

        ResponseEntity<Oferta> response = ofertaRest.postForEntity(createOfertaUrl, request, Oferta.class);
    }

    @Override
    public Oferta uptadeOferta(Oferta oferta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOferta(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Oferta findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Oferta> buscarDestacadas() {
        ofertas = Arrays.asList(ofertaRest.getForObject("http://localhost:8585/bolsatrabajo/buscarDestacadas", Oferta[].class));

        return ofertas;
    }

    @Override
    public List<Oferta> buscarByExample(Example<Oferta> example) {
        ofertas = Arrays.asList(ofertaRest.getForObject("http://localhost:8585/bolsatrabajo/buscarByExample", Oferta[].class));//+ "/" +example

        return ofertas;
    }
    
}
