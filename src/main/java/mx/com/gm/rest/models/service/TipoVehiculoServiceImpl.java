package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.TipoVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

    @Autowired
    private RestTemplate tiposVRest;

    List<TipoVehiculo> tiposVehiculo;

    @Override
    public List<TipoVehiculo> findAll() {
        tiposVehiculo = Arrays.asList(tiposVRest.getForObject("http://localhost:8585/bolsatrabajo/tipovehiculo", TipoVehiculo[].class));
        return tiposVehiculo;
    }

    @Override
    public void save(TipoVehiculo tipovehiculo) {
        String createTipoVehiculoUrl = "http://localhost:8585/bolsatrabajo/crear_tipovehiculo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoVehiculo> request = new HttpEntity<>(tipovehiculo, headers);
//       
//       //enviamos la petición post
        ResponseEntity<TipoVehiculo> response = tiposVRest.postForEntity(createTipoVehiculoUrl, request, TipoVehiculo.class);
    }

    @Override
    public TipoVehiculo uptadeTipoVehiculo(TipoVehiculo tipovehiculo) {
         String updateTipoVehiculoUrl = "http://localhost:8585/bolsatrabajo/update_tipovehiculo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<TipoVehiculo> entity = new HttpEntity<>(tipovehiculo,headers);
//       
        //enviamos la petición post

        ResponseEntity<TipoVehiculo> responseTipoV = tiposVRest.exchange(updateTipoVehiculoUrl + "/" + tipovehiculo.getIdTipoVehiculo(), HttpMethod.PUT, entity,TipoVehiculo.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return tipovehiculo;
    }

    @Override
    public void deleteTipoVehiculo(Integer id) {
        int a = id;
    }

    @Override
    public TipoVehiculo findById(Integer id) {
         Iterator it = tiposVehiculo.iterator();
        TipoVehiculo tipoVeh=null;
        while (it.hasNext()) {
            tipoVeh = (TipoVehiculo) it.next();
           if(tipoVeh.getIdTipoVehiculo()==id)
           {
          //  it.remove(); // avoids a ConcurrentModificationException
            break;
           }
        }

        return tipoVeh;
    }
}
