
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeneroServiceImpl implements GeneroService{

    @Autowired
    private RestTemplate generoRest;
    
    List<Genero> generos;

    
    
    @Override
    public List<Genero> findAll() {
        
       generos = Arrays.asList(generoRest.getForObject("http://localhost:8585/bolsatrabajo/genero", Genero[].class));
           
        return  generos;
        
    }
    

    @Override
    public void save(Genero genero) {
        
        String createGeneroUrl = "http://localhost:8585/bolsatrabajo/crear_genero";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Genero> request = new HttpEntity<>(genero, headers);
    
        ResponseEntity<Genero> response = generoRest.postForEntity(createGeneroUrl, request, Genero.class);

    }

    @Override
    public Genero uptadeGenero(Genero genero) {
        
        String updateGeneroUrl = "http://localhost:8585/bolsatrabajo/update_genero";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Genero> entity = new HttpEntity<>(genero,headers);
        
        ResponseEntity<Genero> responseGen = generoRest.exchange(updateGeneroUrl + "/" + genero.getIdGenero(), HttpMethod.PUT, entity, Genero.class);

        return genero;


    }

    @Override
    public void deleteGenero(Integer id) {
        
        int a = id;
        
    }

    @Override
    public Genero findById(Integer id) {
        
         Iterator it = generos.iterator();
        Genero gene = null;
        while (it.hasNext()) {
            gene = (Genero) it.next();
           if(gene.getIdGenero()==id)
           {
          //  it.remove(); // avoids a ConcurrentModificationException
            break;
           }
        }

        return gene;   
    }
}
