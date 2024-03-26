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
import mx.com.gm.rest.models.SubArea;
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
 * @author USER
 */
@Service
public class SubAreaServiceImpl implements SubAreaService{

    @Autowired
    private RestTemplate SubAreasRest;

    List<SubArea> subAreas;
    
    @Override
    public List<SubArea> findAll() {
        subAreas = Arrays.asList(SubAreasRest.getForObject("http://localhost:8585/bolsatrabajo/subarea", SubArea[].class));

        return subAreas;
    }

    @Override
    public void save(SubArea subArea) {
        String createAreaUrl = "http://localhost:8585/bolsatrabajo/crear_subarea";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<SubArea> request = new HttpEntity<>(subArea, headers);

        ResponseEntity<SubArea> response = SubAreasRest.postForEntity(createAreaUrl, request, SubArea.class);

    }

    @Override
    public SubArea uptadeSubArea(SubArea subArea) {
        String updateAreaUrl = "http://localhost:8585/bolsatrabajo/update_subarea";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<SubArea> entity = new HttpEntity<>(subArea, headers);

        ResponseEntity<SubArea> responseArea = SubAreasRest.exchange(updateAreaUrl + "/" + subArea.getIdSubArea(), HttpMethod.PUT, entity, SubArea.class);

        return subArea;
    }

    @Override
    public void deleteSubArea(Integer id) {
        int a = id;
    }

    @Override
    public SubArea findById(Integer id) {
        Iterator it = subAreas.iterator();
        SubArea subArea = null;
        while (it.hasNext()) {
            subArea = (SubArea) it.next();
            if (subArea.getIdSubArea() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return subArea;
    }
    
}
