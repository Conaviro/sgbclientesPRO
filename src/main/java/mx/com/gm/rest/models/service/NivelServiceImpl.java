/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author karla
 */
@Service
public class NivelServiceImpl implements NivelService{
    
    @Autowired
    private RestTemplate nivelRest;

    List<Nivel> niveles;

    @Override
    public List<Nivel> findAll() {

        niveles = Arrays.asList(nivelRest.getForObject("http://localhost:8585/bolsatrabajo/nivel", Nivel[].class));

        return niveles;
    }

    @Override
    public void save(Nivel contacto) {
        String createNivelUrl = "http://localhost:8585/bolsatrabajo/crear_nivel";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Nivel> request = new HttpEntity<>(contacto, headers);
   
        ResponseEntity<Nivel> response = nivelRest.postForEntity(createNivelUrl, request, Nivel.class);
		

    }

    @Override
    public Nivel updateNivel(Nivel contacto) {

        String updateNivelUrl = "http://localhost:8585/bolsatrabajo/update_nivel";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Nivel> entity = new HttpEntity<>(contacto,headers);

        ResponseEntity<Nivel> responseCon = nivelRest.exchange(updateNivelUrl + "/" + contacto.getIdNivel(), HttpMethod.PUT, entity, Nivel.class);

    return contacto;
    }


    @Override
    public Nivel findById(Integer id) {

      
        Iterator it = niveles.iterator();
        Nivel contac=null;
        while (it.hasNext()) {
            contac = (Nivel) it.next();
           if(contac.getIdNivel()==id)
           {
         
            break;
           }
        }

        return contac;
    }

    @Override
    public void deleteNivel(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
