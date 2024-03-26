
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.Empresa;


public interface EmpresaService {
    public List<Empresa> findAll();
    public void save(Empresa empresa);
    public Empresa uptadeEmpresa(Empresa empresa);
    public void deleteEmpresa(Integer id);
    public Empresa findById(Integer id);
}
