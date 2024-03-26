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
 * @author karla
 */
@Data
public class Nivel implements Serializable{
    
    private Integer idNivel;    
    private short nivelEstudio;
    private Estudio estudio;

    
    private static final long serialVersionUID = 1L;
    
}
