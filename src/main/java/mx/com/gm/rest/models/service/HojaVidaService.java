/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models.service;
import java.util.List;
import mx.com.gm.rest.models.HojaVida;
import mx.com.gm.rest.models.OficioHoja;
/**
 *
 * @author karla
 */
public interface HojaVidaService {
    
    public List<HojaVida> findAll();
    public void save(HojaVida hojaVida);
    public void saveOficioHoja(OficioHoja oficioHoja);
    public HojaVida updateHojaVida(HojaVida hojaVida);
    public void deleteHojaVida(Long id);
    public HojaVida findById(Long id);
    public HojaVida findByPersona(Long id);
    public List<OficioHoja> findByOficioHojaPersona(Long id);
    
}
