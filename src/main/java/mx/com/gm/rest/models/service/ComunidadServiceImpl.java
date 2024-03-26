package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Comunidad;

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
public class ComunidadServiceImpl implements ComunidadService {

    @Autowired
    private RestTemplate comunidadRest;

    List<Comunidad> comunidads;

    @Override
    public List<Comunidad> findAll() {

        comunidads = Arrays.asList(comunidadRest.getForObject("http://localhost:8585/bolsatrabajo/comunidad", Comunidad[].class));

        return comunidads;
    }

    @Override
    public void save(Comunidad comunidad) {
        String createComunidadUrl = "http://localhost:8585/bolsatrabajo/crear_comunidad";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Comunidad> request = new HttpEntity<>(comunidad, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Comunidad> response = comunidadRest.postForEntity(createComunidadUrl, request, Comunidad.class);
//		

    }

    @Override
    public Comunidad uptadeComunidad(Comunidad comunidad) {

        String updateComunidadUrl = "http://localhost:8585/bolsatrabajo/update_comunidad";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Comunidad> entity = new HttpEntity<>(comunidad,headers);
//       
        //enviamos la petición post

        ResponseEntity<Comunidad> responseCom = comunidadRest.exchange(updateComunidadUrl + "/" + comunidad.getIdComunidad(), HttpMethod.PUT, entity, Comunidad.class);

    return comunidad;
    }

    @Override
    public void deleteComunidad(Integer id) {
        int a = id;
    }

    @Override
    public Comunidad findById(Integer id) {

       
        // comunidad = (Comunidad)comunidads.stream().map(p ->Objects.equals(p.getIdComunidad(), id));

        Iterator it = comunidads.iterator();
        Comunidad comu=null;
        while (it.hasNext()) {
            comu = (Comunidad) it.next();
           if(comu.getIdComunidad()==id)
           {
         
            break;
           }
        }

        return comu;
    }

}
