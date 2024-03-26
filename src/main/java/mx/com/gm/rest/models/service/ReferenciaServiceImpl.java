
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Referencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReferenciaServiceImpl implements ReferenciaService{

    @Autowired
    private RestTemplate referenciaRest;

    List<Referencia> referencias;
    
    @Override
    public List<Referencia> findAll() {
        
        referencias = Arrays.asList(referenciaRest.getForObject("http://localhost:8585/bolsatrabajo/referencia", Referencia[].class));

        return referencias;
    }

    @Override
    public void save(Referencia referencia) {
        
        String createReferenciaUrl = "http://localhost:8585/bolsatrabajo/crear_referencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Referencia> request = new HttpEntity<>(referencia, headers);

        ResponseEntity<Referencia> response = referenciaRest.postForEntity(createReferenciaUrl, request, Referencia.class);
    }

    @Override
    public Referencia uptadeReferencia(Referencia referencia) {
        
         String updateReferenciaUrl = "http://localhost:8585/bolsatrabajo/update_referencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Referencia> entity = new HttpEntity<>(referencia, headers);

        ResponseEntity<Referencia> responseReferencia = referenciaRest.exchange(updateReferenciaUrl + "/" + referencia.getIdReferencia(), HttpMethod.PUT, entity, Referencia.class);

        return referencia;
    }

    @Override
    public void deleteReferencia(Integer id) {
        
        int a = id;

    }

    @Override
    public Referencia findById(Integer id) {
    Iterator it = referencias.iterator();
        Referencia referencia = null;
        while (it.hasNext()) {
            referencia = (Referencia) it.next();
            if (referencia.getIdReferencia()== id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return referencia;
    }
    
}
