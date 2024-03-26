package mx.com.gm.rest.cliente;

import java.util.List;
import mx.com.gm.rest.models.Comunidad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "servicio-sgtrabajo")
public interface RestCliente {
//,url="localhost:8585"
    @GetMapping("/comunidad")
    public List<Comunidad> listaComunidad();

}
