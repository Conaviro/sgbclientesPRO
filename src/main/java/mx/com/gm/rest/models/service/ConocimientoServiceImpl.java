package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Conocimiento;

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
public class ConocimientoServiceImpl implements ConocimientoService {

    @Autowired
    private RestTemplate conocimientoRest;

    List<Conocimiento> conocimientos;

    @Override
    public List<Conocimiento> findAll() {

        conocimientos = Arrays.asList(conocimientoRest.getForObject("http://localhost:8585/bolsatrabajo/conocimiento", Conocimiento[].class));

        return conocimientos;
    }

    @Override
    public void save(Conocimiento conocimiento) {
        String createConocimientoUrl = "http://localhost:8585/bolsatrabajo/crear_conocimiento";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Conocimiento> request = new HttpEntity<>(conocimiento, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Conocimiento> response = conocimientoRest.postForEntity(createConocimientoUrl, request, Conocimiento.class);
//		

    }

    @Override
    public Conocimiento uptadeConocimiento(Conocimiento conocimiento) {

        String updateConocimientoUrl = "http://localhost:8585/bolsatrabajo/update_conocimiento";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Conocimiento> entity = new HttpEntity<>(conocimiento, headers);
//       
        //enviamos la petición post

        ResponseEntity<Conocimiento> responseCom = conocimientoRest.exchange(updateConocimientoUrl + "/" + conocimiento.getIdConocimiento(), HttpMethod.PUT, entity, Conocimiento.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return conocimiento;
    }

    @Override
    public void deleteConocimiento(Integer id) {
        int a = id;
    }

    @Override
    public Conocimiento findById(Integer id) {

        Iterator it = conocimientos.iterator();
        Conocimiento cono = null;
        while (it.hasNext()) {
            cono = (Conocimiento) it.next();
            if (cono.getIdConocimiento() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return cono;
    }

}
