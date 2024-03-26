
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Area;

public interface AreaService {
    
    public List<Area> findAll();
    public void save(Area area);
    public Area uptadeArea(Area area);
    public void deleteArea(Integer id);
    public Area findById(Integer id);
    
}
