/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.HojaVida;
import mx.com.gm.rest.models.OficioHoja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author karla
 */
@Service
public class HojaVidaServiceImpl implements HojaVidaService{
    
    @Autowired
    private RestTemplate hojaVidaRest;

    List<HojaVida> hojaVidas;
    
    @Override
    public List<HojaVida> findAll() {
        hojaVidas = Arrays.asList(hojaVidaRest.getForObject("http://localhost:8585/bolsatrabajo/hojavida", HojaVida[].class));

        return hojaVidas;
    }

    @Override
    public void save(HojaVida subArea) {
        String createAreaUrl = "http://localhost:8585/bolsatrabajo/crear_hojavida";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<HojaVida> request = new HttpEntity<>(subArea, headers);

        ResponseEntity<HojaVida> response = hojaVidaRest.postForEntity(createAreaUrl, request, HojaVida.class);

    }

    @Override
    public HojaVida updateHojaVida(HojaVida subArea) {
        String updateAreaUrl = "http://localhost:8585/bolsatrabajo/update_hojavida";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<HojaVida> entity = new HttpEntity<>(subArea, headers);

        ResponseEntity<HojaVida> responseArea = hojaVidaRest.exchange(updateAreaUrl + "/" + subArea.getIdHojaVida(), HttpMethod.PUT, entity, HojaVida.class);

        return subArea;
    }

    @Override
    public void deleteHojaVida(Long id) {
        Long a = id;
    }
    
    @Override
    public HojaVida findById(Long id) {
        Iterator it = hojaVidas.iterator();
        HojaVida hojaVida = null;
        while (it.hasNext()) {
            hojaVida = (HojaVida) it.next();
            if (hojaVida.getIdHojaVida()== id) {

                break;
            }
        }

        return hojaVida;
    }

    @Override
    public HojaVida findByPersona(Long id) {
        HojaVida hojaVida = null;
        hojaVida = hojaVidaRest.getForObject("http://localhost:8585/bolsatrabajo/find_persona_idHoja/"+ id, HojaVida.class);
       return hojaVida;
    }

    @Override
    public void saveOficioHoja(OficioHoja oficioHoja) {
        String createAreaUrl = "http://localhost:8585/bolsatrabajo/crear_oficiohoja";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<OficioHoja> request = new HttpEntity<>(oficioHoja, headers);

        ResponseEntity<OficioHoja> response = hojaVidaRest.postForEntity(createAreaUrl, request, OficioHoja.class);//+ "/" + oficioHoja.getId().getIdOficio()+ "/crear_oficiohoja/" + oficioHoja.getId().getIdHojaVida()

    }

    @Override
    public List<OficioHoja> findByOficioHojaPersona(Long id) {
        List<OficioHoja> oFiciohoja = null;
        oFiciohoja = Arrays.asList(hojaVidaRest.getForObject("http://localhost:8585/bolsatrabajo/find_persona_oficioHoja/"+ id, OficioHoja[].class));
       return oFiciohoja;
    }
    
}
