package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Direccion;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import mx.com.gm.rest.models.DireccionDto;
import mx.com.gm.rest.models.DireccionPk;
import org.modelmapper.ModelMapper;

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
public class DireccionServiceImpl implements DireccionService {
    
    @Autowired
    private RestTemplate direccionRest;
    
//    @Autowired
//    ModelMapper mapper;
    
    List<Direccion> direccions;
    Optional<Direccion> direccionOP;
    
    @Override
    public List<Direccion> findAll() {
        
        direccions = Arrays.asList(direccionRest.getForObject("http://localhost:8585/bolsatrabajo/direccion", Direccion[].class));
        
        return direccions;
    }
    
    @Override
    public void save(Direccion direccion) {
        String createDireccionUrl = "http://localhost:8585/bolsatrabajo/crear_direccion";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Direccion> request = new HttpEntity<>(direccion, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Direccion> response = direccionRest.postForEntity(createDireccionUrl, request, Direccion.class);
//		

    }
    
    @Override
    public Direccion uptadeDireccion(Direccion direccion) {
        
//        DireccionDto direccionDto=null;
//        direccionDto = mapper.map(direccion, DireccionDto.class);
//        
//        if (direccionDto != null) {
//            direccionDto.setBarrio(direccion.getBarrio());
//        }
        
        String updateDireccionUrl = "http://localhost:8585/bolsatrabajo/update_direccion";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Direccion> entity = new HttpEntity<>(direccion, headers);
//       
        //enviamos la petición post
        try {
            ResponseEntity<Direccion> responseDir = direccionRest.exchange(updateDireccionUrl , HttpMethod.PUT, entity, Direccion.class);//+ "/" + direccion.getLlave()
        } catch (Exception e) {
            System.err.print(e);
        }
        
        return direccion;
    }
    
    @Override
    public void deleteDireccion(DireccionPk id) {
        DireccionPk a = id;
    }
    
    @Override
    public Direccion findById(DireccionPk id) {

        // comunidad = (Comunidad)comunidads.stream().map(p ->Objects.equals(p.getIdComunidad(), id));
        if (direccions == null) {
            direccions = Arrays.asList(direccionRest.getForObject("http://localhost:8585/bolsatrabajo/direccion", Direccion[].class));
        }
        
        Iterator it = direccions.iterator();
        Direccion direc = null;
        while (it.hasNext()) {
            direc = (Direccion) it.next();
            if (direc.getLlave().getIdPersona() == id.getIdPersona() && direc.getLlave().getIdtipoDireccion() == id.getIdtipoDireccion()) {
                
                break;
            }
        }
        
        return direc;
    }
    
    @Override
    public List<Direccion> findByPersona(Long id) {
        
       direccions = Arrays.asList(direccionRest.getForObject("http://localhost:8585/bolsatrabajo/find_persona_idDirc/"+ id, Direccion[].class));
       return direccions;
    }
    
    @Override
    public Optional<Direccion> findByIdOp(DireccionPk id) {
        if (direccions == null) {
            direccions = Arrays.asList(direccionRest.getForObject("http://localhost:8585/bolsatrabajo/find_direccion_pk", Direccion[].class));
        }
        
        Optional<Direccion> direccionOP = Optional.of(direccions.get(0));

//        Iterator it = direccions.iterator();
//        Optional<Direccion> direc=null;
//        while (it.hasNext()) {
//            direc = (Optional<Direccion>) it.next();
//           if(direc.get().getLlave().getIdPersona()==id.getIdPersona() && direc.get().getLlave().getIdtipoDireccion()== id.getIdtipoDireccion() )
//           {
//         
//            break;
//           }
//        }
        return direccionOP;
    }
}
