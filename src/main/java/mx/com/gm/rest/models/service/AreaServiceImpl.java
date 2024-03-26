
package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AreaServiceImpl implements AreaService{
    
    @Autowired
    private RestTemplate areaRest;

    List<Area> areas;

    @Override
    public List<Area> findAll() {
        
        areas = Arrays.asList(areaRest.getForObject("http://localhost:8585/bolsatrabajo/area", Area[].class));

        return areas;
    }

    @Override
    public void save(Area area) {
        
        String createAreaUrl = "http://localhost:8585/bolsatrabajo/crear_area";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Area> request = new HttpEntity<>(area, headers);

        ResponseEntity<Area> response = areaRest.postForEntity(createAreaUrl, request, Area.class);
    }

    @Override
    public Area uptadeArea(Area area) {
        
        String updateAreaUrl = "http://localhost:8585/bolsatrabajo/update_area";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Area> entity = new HttpEntity<>(area, headers);

        ResponseEntity<Area> responseArea = areaRest.exchange(updateAreaUrl + "/" + area.getIdArea(), HttpMethod.PUT, entity, Area.class);

        return area;
    }

    @Override
    public void deleteArea(Integer id) {
        
        int a = id;
    }

    @Override
    public Area findById(Integer id) {
     Iterator it = areas.iterator();
        Area area = null;
        while (it.hasNext()) {
            area = (Area) it.next();
            if (area.getIdArea() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return area;
    }
    
}
