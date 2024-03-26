/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.rest.models;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author USER
 */
@Data
public class SubArea implements Serializable{
    
    private Integer idSubArea;
    private String nombreSubArea;
    private Area area;
    
    private static final long serialVersionUID = 1L;
}
