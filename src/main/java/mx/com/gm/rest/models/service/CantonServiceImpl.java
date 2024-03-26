package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Canton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CantonServiceImpl implements CantonService {

    @Autowired
    private RestTemplate cantonRest;

    List<Canton> cantons;

    @Override
    public List<Canton> findAll() {
        cantons = Arrays.asList(cantonRest.getForObject("http://localhost:8585/bolsatrabajo/canton", Canton[].class));
        return cantons;
    }

    @Override
    public void save(Canton canton) {
        String createCantonUrl = "http://localhost:8585/bolsatrabajo/crear_canton";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Canton> request = new HttpEntity<>(canton, headers);

        ResponseEntity<Canton> response = cantonRest.postForEntity(createCantonUrl, request, Canton.class);
    }

    @Override
    public Canton uptadeCanton(Canton canton) {
        String updateCantonaUrl = "http://localhost:8585/bolsatrabajo/update_canton";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Canton> entity = new HttpEntity<>(canton, headers);

        ResponseEntity<Canton> responseCanton = cantonRest.exchange(updateCantonaUrl + "/" + canton.getIdCanton(), HttpMethod.PUT, entity, Canton.class);

        return canton;
    }

    @Override
    public void deleteCanton(Integer id) {
         int a = id;
    }

    @Override
    public Canton findById(Integer id) {
        Iterator it = cantons.iterator();
        Canton canton = null;
        while (it.hasNext()) {
            canton = (Canton) it.next();
            if (canton.getIdCanton() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return canton;
    }

}
