package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.TipoDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TipoDireccionServiceImpl implements TipoDireccionService {

    @Autowired
    private RestTemplate tiposIRest;

    List<TipoDireccion> tiposId;

    @Override
    public List<TipoDireccion> findAll() {
        tiposId = Arrays.asList(tiposIRest.getForObject("http://localhost:8585/bolsatrabajo/tipodireccion", TipoDireccion[].class));
        return tiposId;
    }

    @Override
    public void save(TipoDireccion tipoDireccion) {
        String createTipoIdUrl = "http://localhost:8585/bolsatrabajo/crear_tipodireccion";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoDireccion> request = new HttpEntity<>(tipoDireccion, headers);
//       
//       //enviamos la petición post
        ResponseEntity<TipoDireccion> response = tiposIRest.postForEntity(createTipoIdUrl, request, TipoDireccion.class);
    }

    @Override
    public TipoDireccion uptadeTipoDireccion(TipoDireccion tipoDireccion) {
        String updateTipoIdUrl = "http://localhost:8585/bolsatrabajo/update_tipodireccion";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoDireccion> entity = new HttpEntity<>(tipoDireccion, headers);
//       
        //enviamos la petición post

        ResponseEntity<TipoDireccion> responseTipoI = tiposIRest.exchange(updateTipoIdUrl + "/" + tipoDireccion.getIdTipoDireccion(), HttpMethod.PUT, entity, TipoDireccion.class);

        return tipoDireccion;
    }

    @Override
    public void deleteTipoDireccion(Integer id) {
        int a = id;
    }

    @Override
    public TipoDireccion findById(Integer id) {
        Iterator it = tiposId.iterator();
        TipoDireccion tipoId = null;
        while (it.hasNext()) {
            tipoId = (TipoDireccion) it.next();
            if (tipoId.getIdTipoDireccion() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return tipoId;
    }

}
