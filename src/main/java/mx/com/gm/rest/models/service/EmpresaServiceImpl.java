package mx.com.gm.rest.models.service;

import mx.com.gm.rest.models.Empresa;

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
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private RestTemplate empresaRest;

    List<Empresa> empresas;

    @Override
    public List<Empresa> findAll() {

        empresas = Arrays.asList(empresaRest.getForObject("http://localhost:8585/bolsatrabajo/empresa", Empresa[].class));

        return empresas;
    }

    @Override
    public void save(Empresa empresa) {
        String createEmpresaUrl = "http://localhost:8585/bolsatrabajo/crear_empresa";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Empresa> request = new HttpEntity<>(empresa, headers);
//       
//       //enviamos la petición post
        ResponseEntity<Empresa> response = empresaRest.postForEntity(createEmpresaUrl, request, Empresa.class);
//		

    }

    @Override
    public Empresa uptadeEmpresa(Empresa empresa) {

        String updateEmpresaUrl = "http://localhost:8585/bolsatrabajo/update_empresa";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//       //construimosel json request
        HttpEntity<Empresa> entity = new HttpEntity<>(empresa, headers);
//       
        //enviamos la petición post

        ResponseEntity<Empresa> responseEmp = empresaRest.exchange(updateEmpresaUrl + "/" + empresa.getIdEmpresa(), HttpMethod.PUT, entity, Empresa.class);

//HttpEntity<String> entity = new HttpEntity<>(headers);
        //	ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL+"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
        return empresa;
    }

    @Override
    public void deleteEmpresa(Integer id) {
        int a = id;
    }

    @Override
    public Empresa findById(Integer id) {

        Iterator it = empresas.iterator();
        Empresa empresa = null;
        while (it.hasNext()) {
            empresa = (Empresa) it.next();
            if (empresa.getIdEmpresa() == id) {
                //  it.remove(); // avoids a ConcurrentModificationException
                break;
            }
        }

        return empresa;
    }

}
