package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Provincia;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.Iterator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private RestTemplate provinciaRest;

    List<Provincia> provincias;

    @Override
    public List<Provincia> findAll() {

        provincias = Arrays.asList(provinciaRest.getForObject("http://localhost:8585/bolsatrabajo/provincia", Provincia[].class));

        return provincias;
    }

    @Override
    public void save(Provincia provincia) {
        String createProvinciaUrl = "http://localhost:8585/bolsatrabajo/crear_provincia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Provincia> request = new HttpEntity<>(provincia, headers);

        ResponseEntity<Provincia> response = provinciaRest.postForEntity(createProvinciaUrl, request, Provincia.class);
    }

    @Override
    public Provincia updateProvincia(Provincia provincia) {

        String updateProvinciaUrl = "http://localhost:8585/bolsatrabajo/update_provincia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Provincia> entity = new HttpEntity<>(provincia, headers);

        ResponseEntity<Provincia> responseProvincia = provinciaRest.exchange(updateProvinciaUrl + "/" + provincia.getIdProvincia(), HttpMethod.PUT, entity, Provincia.class);

        return provincia;
    }

    @Override
    public void deleteProvincia(Integer id) {
        int a = id;
    }

    @Override
    public Provincia findById(Integer id) {
      Iterator it = provincias.iterator();
        Provincia prov = null;
        
        while (it.hasNext()) {
            prov = (Provincia) it.next();
            if (prov.getIdProvincia() == id) {  
                break;
            }
        }

        return prov;
    }
}
