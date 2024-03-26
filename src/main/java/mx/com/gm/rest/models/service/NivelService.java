/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models.service;
import java.util.List;
import mx.com.gm.rest.models.Nivel;
/**
 *
 * @author karla
 */
public interface NivelService {
    
    public List<Nivel> findAll();
    public void save(Nivel nivel);
    public Nivel updateNivel(Nivel nivel);
    public void deleteNivel(Integer id);
    public Nivel findById(Integer id);
    
}
