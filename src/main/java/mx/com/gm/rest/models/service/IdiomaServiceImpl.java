
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IdiomaServiceImpl implements IdiomaService{
    
    @Autowired
    private RestTemplate idiomaRest;

    List<Idioma> idiomas;

    @Override
    public List<Idioma> findAll() {
        
        idiomas = Arrays.asList(idiomaRest.getForObject("http://localhost:8585/bolsatrabajo/idioma", Idioma[].class));

        return idiomas;
    }

    @Override
    public void save(Idioma idioma) {
        
        String createIdiomaUrl = "http://localhost:8585/bolsatrabajo/crear_idioma";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Idioma> request = new HttpEntity<>(idioma, headers);

        ResponseEntity<Idioma> response = idiomaRest.postForEntity(createIdiomaUrl, request, Idioma.class);
    }

    @Override
    public Idioma uptadeIdioma(Idioma idioma) {
        
        String updateIdiomaUrl = "http://localhost:8585/bolsatrabajo/update_idioma";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Idioma> entity = new HttpEntity<>(idioma, headers);

        ResponseEntity<Idioma> responseIdioma = idiomaRest.exchange(updateIdiomaUrl + "/" + idioma.getIdIdioma(), HttpMethod.PUT, entity, Idioma.class);

        return idioma;
    }

    @Override
    public void deleteIdioma(Integer id) {
        
        int a = id;
    }

    @Override
    public Idioma findById(Integer id) {
     Iterator it = idiomas.iterator();
        Idioma idioma = null;
        while (it.hasNext()) {
            idioma = (Idioma) it.next();
            if (idioma.getIdIdioma() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return idioma;
    }
    
}
