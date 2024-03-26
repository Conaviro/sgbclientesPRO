
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Persona;
import mx.com.gm.rest.models.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersServiceImpl implements PersService{

    @Autowired
    private RestTemplate personaRest;

    List<Persona> personas;
    
    @Override
    public List<Persona> findAll() {
        personas = Arrays.asList(personaRest.getForObject("http://localhost:8585/bolsatrabajo/persona", Persona[].class));

        return personas;
    }

    @Override
    public void save(Persona persona) {
        String createPersonaUrl = "http://localhost:8585/bolsatrabajo/crear_persona";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Persona> request = new HttpEntity<>(persona, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Persona> response = personaRest.postForEntity(createPersonaUrl, request, Persona.class);

    }

    @Override
    public Persona uptadePersona(Persona persona) {
        String updatePersonaUrl = "http://localhost:8585/bolsatrabajo/update_persona";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Persona> entity = new HttpEntity<>(persona,headers);
//       
        //enviamos la petición post

        ResponseEntity<Persona> responseTipoV = personaRest.exchange(updatePersonaUrl + "/" + persona.getIdPersona(), HttpMethod.PUT, entity,Persona.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return persona;
    }

    @Override
    public void deletePersona(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

    @Override
    public Persona findById(Long id) {
        Iterator it = personas.iterator();
        Persona pers = null;
        
        while (it.hasNext()) {
            pers = (Persona) it.next();
            if (pers.getIdPersona() == id) {  
                break;
            }
        }

        return pers;
    }
    
}
