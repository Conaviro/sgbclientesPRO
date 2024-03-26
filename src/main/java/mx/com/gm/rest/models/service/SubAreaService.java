/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models.service;

import java.util.List;
import mx.com.gm.rest.models.SubArea;
/**
 *
 * @author USER
 */
public interface SubAreaService {
    
    public List<SubArea> findAll();
    public void save(SubArea subArea);
    public SubArea uptadeSubArea(SubArea subArea);
    public void deleteSubArea(Integer id);
    public SubArea findById(Integer id);
    
}
