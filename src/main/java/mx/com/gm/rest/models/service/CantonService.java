
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Canton;
public interface CantonService {
    
    public List<Canton> findAll();
    public void save(Canton canton);
    public Canton uptadeCanton(Canton canton);
    public void deleteCanton(Integer id);
    public Canton findById(Integer id);
    
}
