/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.com.gm.rest.models.service;
import java.util.List;
import mx.com.gm.rest.models.Contacto;

/**
 *
 * @author Gustavo Jimenez
 */
public interface ContactoService {
    public List<Contacto> findAll();
    public void save(Contacto contacto);
    public Contacto uptadeContacto(Contacto contacto);
    public void deleteContacto(Integer id);
    public Contacto findById(Integer id);
    
}
