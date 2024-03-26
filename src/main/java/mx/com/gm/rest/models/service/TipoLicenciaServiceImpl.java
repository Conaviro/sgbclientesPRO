package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.TipoLicencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TipoLicenciaServiceImpl implements TipoLicenciaService {

    @Autowired
    private RestTemplate tiposLRest;

    List<TipoLicencia> tiposLicencia;

    @Override
    public List<TipoLicencia> findAll() {
        tiposLicencia = Arrays.asList(tiposLRest.getForObject("http://localhost:8585/bolsatrabajo/tipolicencia", TipoLicencia[].class));
        return tiposLicencia;
    }

    @Override
    public void save(TipoLicencia tipoLicencia) {
        String createTipoLicenciaUrl = "http://localhost:8585/bolsatrabajo/crear_tipolicencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoLicencia> request = new HttpEntity<>(tipoLicencia, headers);
//       
//       //enviamos la petición post
        ResponseEntity<TipoLicencia> response = tiposLRest.postForEntity(createTipoLicenciaUrl, request, TipoLicencia.class);
    }

    @Override
    public TipoLicencia uptadeTipoLicencia(TipoLicencia tipoLicencia) {
        String updateTipoLicenciaUrl = "http://localhost:8585/bolsatrabajo/update_tipolicencia";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoLicencia> entity = new HttpEntity<>(tipoLicencia, headers);
//       
        //enviamos la petición post

        ResponseEntity<TipoLicencia> responseTipoV = tiposLRest.exchange(updateTipoLicenciaUrl + "/" + tipoLicencia.getIdTipoLicencia(), HttpMethod.PUT, entity, TipoLicencia.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return tipoLicencia;
    }

    @Override
    public void deleteTipoLicencia(Integer id) {
        int a = id;
    }

    @Override
    public TipoLicencia findById(Integer id) {
        Iterator it = tiposLicencia.iterator();
        TipoLicencia TipoLic = null;
        while (it.hasNext()) {
            TipoLic = (TipoLicencia) it.next();
            if (TipoLic.getIdTipoLicencia()== id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return TipoLic;
    }
}
