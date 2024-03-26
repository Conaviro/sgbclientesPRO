
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.TipoVehiculo;


public interface TipoVehiculoService {
    public List<TipoVehiculo> findAll();
    public void save(TipoVehiculo tipovehiculo);
    public TipoVehiculo uptadeTipoVehiculo(TipoVehiculo tipovehiculo);
    public void deleteTipoVehiculo(Integer id);
    public TipoVehiculo findById(Integer id);
}
