package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Oficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OficioServiceImpl implements OficioService{
    
    @Autowired
    private RestTemplate oficioRest;
    
    List<Oficio> oficios;
    
    @Override
    public List<Oficio> findAll() {
        
        oficios = Arrays.asList(oficioRest.getForObject("http://localhost:8585/bolsatrabajo/oficio", Oficio[].class));
        
        return oficios;
    }

    @Override
    public void save(Oficio oficio) {
        String createOficioUrl = "http://localhost:8585/bolsatrabajo/crear_oficio";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        HttpEntity<Oficio> request = new HttpEntity<>(oficio, headers);
        
        ResponseEntity<Oficio> response = oficioRest.postForEntity(createOficioUrl, request, Oficio.class);
    
    }

    @Override
    public Oficio uptadeOficio(Oficio oficio) {
        
        String updateOficioUrl = "http://localhost:8585/bolsatrabajo/update_oficio";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        HttpEntity<Oficio> entity = new HttpEntity<>(oficio, headers);
        
        ResponseEntity<Oficio> responseOfi = oficioRest.exchange(updateOficioUrl + "/" + oficio.getIdOficio(), HttpMethod.PUT, entity, Oficio.class);
    
        return oficio;
    }

    @Override
    public void deleteOficio(Integer id) {
        int a = id;
    }

    @Override
    public Oficio findById(Integer id) {
        
        Iterator it = oficios.iterator();
        Oficio ofic = null;
        
        while (it.hasNext()) {
            ofic = (Oficio) it.next();
            if (ofic.getIdOficio() == id) {
                // it.remove();
                break;
            }
        }
        
        return ofic;
    }
    
}
