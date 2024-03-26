package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Pais;

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
public class PaisServiceImpl implements PaisService{

    @Autowired
    private RestTemplate paisRest;

    List<Pais> paises;

    @Override
    public List<Pais> findAll() {

        paises = Arrays.asList(paisRest.getForObject("http://localhost:8585/bolsatrabajo/pais", Pais[].class));

        return paises;
    }

    @Override
    public void save(Pais pais) {
        String createPaisUrl = "http://localhost:8585/bolsatrabajo/crear_pais";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Pais> request = new HttpEntity<>(pais, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Pais> response = paisRest.postForEntity(createPaisUrl, request, Pais.class);
//		

    }

    @Override
    public Pais uptadePais(Pais pais) {

        String updatePaisUrl = "http://localhost:8585/bolsatrabajo/update_pais";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Pais> entity = new HttpEntity<>(pais, headers);
//       
        //enviamos la petición post

        ResponseEntity<Pais> responsePais = paisRest.exchange(updatePaisUrl + "/" + pais.getIdPais(), HttpMethod.PUT, entity, Pais.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return pais;
    }

    @Override
    public void deletePais(Integer id) {
        int a = id;
    }

    @Override
    public Pais findById(Integer id) {

        Iterator it = paises.iterator();
        Pais pais = null;
        while (it.hasNext()) {
            pais = (Pais) it.next();
            if (pais.getIdPais()== id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return pais;
    }

}
