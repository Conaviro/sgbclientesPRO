package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Parroquia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class ParroquiaServiceImpl implements ParroquiaService {

    @Autowired
    private RestTemplate parroquiaRest;

    List<Parroquia> parroquias;

    @Override
    public List<Parroquia> findAll() {
        parroquias = Arrays.asList(parroquiaRest.getForObject("http://localhost:8585/bolsatrabajo/parroquia", Parroquia[].class));
        return parroquias;
    }

    @Override
    public void save(Parroquia parroquia) {
        String createParroquiaUrl = "http://localhost:8585/bolsatrabajo/crear_parroquia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Parroquia> request = new HttpEntity<>(parroquia, headers);

        ResponseEntity<Parroquia> response = parroquiaRest.postForEntity(createParroquiaUrl, request, Parroquia.class);
    }

    @Override
    public Parroquia updateParroquia(Parroquia parroquia) {
        String updateParroquiaUrl = "http://localhost:8585/bolsatrabajo/update_parroquia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Parroquia> entity = new HttpEntity<>(parroquia, headers);

        ResponseEntity<Parroquia> responseParroquia = parroquiaRest.exchange(updateParroquiaUrl + "/" + parroquia.getIdParroquia(), HttpMethod.PUT, entity, Parroquia.class);

        return parroquia;
    }

    @Override
    public void deleteParroquia(Integer id) {
        int a = id;
    }

    @Override
    public Parroquia findById(Integer id) {
        Iterator it = parroquias.iterator();
        Parroquia parroquia = null;
        while (it.hasNext()) {
            parroquia = (Parroquia) it.next();
            if (parroquia.getIdParroquia() == id) {
                break;
            }
        }
        return parroquia;
    }
}
