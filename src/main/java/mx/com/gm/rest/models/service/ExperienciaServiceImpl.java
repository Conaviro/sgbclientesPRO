
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExperienciaServiceImpl implements ExperienciaService{

    @Autowired
    private RestTemplate experienciaRest;

    List<Experiencia> experiencias;
    
    @Override
    public List<Experiencia> findAll() {
        
        experiencias = Arrays.asList(experienciaRest.getForObject("http://localhost:8585/bolsatrabajo/experiencia", Experiencia[].class));

        return experiencias;
    }

    @Override
    public void save(Experiencia experiencia) {
        
        String createExperienciaUrl = "http://localhost:8585/bolsatrabajo/crear_experiencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Experiencia> request = new HttpEntity<>(experiencia, headers);

        ResponseEntity<Experiencia> response = experienciaRest.postForEntity(createExperienciaUrl, request, Experiencia.class);
    }

    @Override
    public Experiencia uptadeExperiencia(Experiencia experiencia) {
        
         String updateExperienciaUrl = "http://localhost:8585/bolsatrabajo/update_experiencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Experiencia> entity = new HttpEntity<>(experiencia, headers);

        ResponseEntity<Experiencia> responseExperiencia = experienciaRest.exchange(updateExperienciaUrl + "/" + experiencia.getIdExperiencia(), HttpMethod.PUT, entity, Experiencia.class);

        return experiencia;
    }

    @Override
    public void deleteExperiencia(Integer id) {
        
        int a = id;

    }

    @Override
    public Experiencia findById(Integer id) {
    Iterator it = experiencias.iterator();
        Experiencia experiencia = null;
        while (it.hasNext()) {
            experiencia = (Experiencia) it.next();
            if (experiencia.getIdExperiencia() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return experiencia;
    }
    
}
