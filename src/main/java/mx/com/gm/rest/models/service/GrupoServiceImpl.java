package mx.com.gm.rest.models.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import mx.com.gm.rest.models.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private RestTemplate grupodRest;

    List<Grupo> grupos;

    @Override
    public List<Grupo> findAll() {
       grupos = Arrays.asList(grupodRest.getForObject("http://localhost:8585/bolsatrabajo/grupo", Grupo[].class));

        return grupos;
    }

    @Override
    public void save(Grupo grupo) {
        String createGrupoUrl = "http://localhost:8585/bolsatrabajo/crear_grupo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Grupo> request = new HttpEntity<>(grupo, headers);

        ResponseEntity<Grupo> response = grupodRest.postForEntity(createGrupoUrl, request, Grupo.class);

    }

    @Override
    public Grupo uptadeGrupo(Grupo grupo) {
        String updateGrupoUrl = "http://localhost:8585/bolsatrabajo/update_grupo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Grupo> entity = new HttpEntity<>(grupo, headers);

        ResponseEntity<Grupo> responseGru = grupodRest.exchange(updateGrupoUrl + "/" + grupo.getIdGrupo(), HttpMethod.PUT, entity, Grupo.class);

        return grupo;
    }

    @Override
    public void deleteGrupo(Integer id) {
        int a = id;
    }

    @Override
    public Grupo findById(Integer id) {
        Iterator it = grupos.iterator();
        Grupo grup = null;
        while (it.hasNext()) {
            grup = (Grupo) it.next();
            if (grup.getIdGrupo() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return grup;
    }

}
