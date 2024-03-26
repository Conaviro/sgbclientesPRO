
package mx.com.gm.rest.models;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class  ReportRepository {
    private final EntityManagerFactory emf=null;

    public ReportRepository() {
        
        // create an instance of entity manager factory
    }

    public List<Object[]> find(Long idPersona) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager
          .createQuery("SELECT c, s, u FROM  HojaVida c, OficioHoja s, Oficio u" 
          + " WHERE c.idHojaVida = s.OficioHojaKey.hojaVida AND s.OficioHojaKey.oficio = u.idOficio AND u.persona.idPersona=:idPersona");
        query.setParameter("idPersona", idPersona);

        return query.getResultList();
    }
}
