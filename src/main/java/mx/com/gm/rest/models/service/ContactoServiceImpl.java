/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.gm.rest.models.service;
import mx.com.gm.rest.models.Contacto;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author Gustavo Jimenez
 */

@Service
public class ContactoServiceImpl implements ContactoService {
    
    @Autowired
    private RestTemplate contactoRest;

    List<Contacto> contactos;

    @Override
    public List<Contacto> findAll() {

        contactos = Arrays.asList(contactoRest.getForObject("http://localhost:8585/bolsatrabajo/contacto", Contacto[].class));

        return contactos;
    }

    @Override
    public void save(Contacto contacto) {
        String createContactoUrl = "http://localhost:8585/bolsatrabajo/crear_contacto";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Contacto> request = new HttpEntity<>(contacto, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Contacto> response = contactoRest.postForEntity(createContactoUrl, request, Contacto.class);
//		

    }

    @Override
    public Contacto uptadeContacto(Contacto contacto) {

        String updateContactoUrl = "http://localhost:8585/bolsatrabajo/update_contacto";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Contacto> entity = new HttpEntity<>(contacto,headers);
//       
        //enviamos la petición post

        ResponseEntity<Contacto> responseCon = contactoRest.exchange(updateContactoUrl + "/" + contacto.getIdContacto(), HttpMethod.PUT, entity, Contacto.class);

    return contacto;
    }

    @Override
    public void deleteContacto(Integer id) {
        int a = id;
    }

    @Override
    public Contacto findById(Integer id) {

       
        // comunidad = (Comunidad)comunidads.stream().map(p ->Objects.equals(p.getIdComunidad(), id));

        Iterator it = contactos.iterator();
        Contacto contac=null;
        while (it.hasNext()) {
            contac = (Contacto) it.next();
           if(contac.getIdContacto()==id)
           {
         
            break;
           }
        }

        return contac;
    }
    
    
}
