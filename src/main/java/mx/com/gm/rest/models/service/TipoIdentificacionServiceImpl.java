package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.TipoIdentificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

    @Autowired
    private RestTemplate tiposIRest;

    List<TipoIdentificacion> tiposId;

    @Override
    public List<TipoIdentificacion> findAll() {
        tiposId = Arrays.asList(tiposIRest.getForObject("http://localhost:8585/bolsatrabajo/tipoidentificacion", TipoIdentificacion[].class));
        return tiposId;
    }

    @Override
    public void save(TipoIdentificacion tipoIdentificacion) {
        String createTipoIdUrl = "http://localhost:8585/bolsatrabajo/crear_tipoidentificacion";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoIdentificacion> request = new HttpEntity<>(tipoIdentificacion, headers);
//       
//       //enviamos la petición post
        ResponseEntity<TipoIdentificacion> response = tiposIRest.postForEntity(createTipoIdUrl, request, TipoIdentificacion.class);
    }

    @Override
    public TipoIdentificacion uptadeTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        String updateTipoIdUrl = "http://localhost:8585/bolsatrabajo/update_tipoidentificacion";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoIdentificacion> entity = new HttpEntity<>(tipoIdentificacion, headers);
//       
        //enviamos la petición post

        ResponseEntity<TipoIdentificacion> responseTipoI = tiposIRest.exchange(updateTipoIdUrl + "/" + tipoIdentificacion.getIdTipoIdentificacion(), HttpMethod.PUT, entity, TipoIdentificacion.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return tipoIdentificacion;
    }

    @Override
    public void deleteTipoIdentificacion(Integer id) {
        int a = id;
    }

    @Override
    public TipoIdentificacion findById(Integer id) {
        Iterator it = tiposId.iterator();
        TipoIdentificacion tipoId = null;
        while (it.hasNext()) {
            tipoId = (TipoIdentificacion) it.next();
            if (tipoId.getIdTipoIdentificacion() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return tipoId;
    }
}
