
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.EstadoCivil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService{

    @Autowired
     private RestTemplate estadoCivilRest;

    List<EstadoCivil> estadoCivils;
    
    
    @Override
    public List<EstadoCivil> findAll() {
        
        estadoCivils = Arrays.asList(estadoCivilRest.getForObject("http://localhost:8585/bolsatrabajo/estadocivil", EstadoCivil[].class));
           
        return  estadoCivils;
    }

    @Override
    public void save(EstadoCivil estadoCivil) {
        
        String createEstadoCivilUrl = "http://localhost:8585/bolsatrabajo/crear_estadocivil";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<EstadoCivil> request = new HttpEntity<>(estadoCivil, headers);
    
        ResponseEntity<EstadoCivil> response = estadoCivilRest.postForEntity(createEstadoCivilUrl, request, EstadoCivil.class);

    }

    @Override
    public EstadoCivil uptadeEstadoCivil(EstadoCivil estadoCivil) {
        
        String updateEstadoCivilUrl = "http://localhost:8585/bolsatrabajo/update_estadocivil";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<EstadoCivil> entity = new HttpEntity<>(estadoCivil,headers);
        
        ResponseEntity<EstadoCivil> responseEst = estadoCivilRest.exchange(updateEstadoCivilUrl + "/" + estadoCivil.getIdEstadoCivil(), HttpMethod.PUT, entity, EstadoCivil.class);

        return estadoCivil;
    }

    @Override
    public void deleteEstadoCivil(Integer id) {
        
        int a = id;

    }

    @Override
    public EstadoCivil findById(Integer id) {
        
       Iterator it = estadoCivils.iterator();
        EstadoCivil comu=null;
        while (it.hasNext()) {
            comu = (EstadoCivil) it.next();
           if(comu.getIdEstadoCivil()==id)
           {
          //  it.remove(); // avoids a ConcurrentModificationException
            break;
           }
        }

        return comu;    }
    
}
