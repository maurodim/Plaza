/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author mauro di
 */
public interface Propietables {
    public Object cargarPorIdPropiedad(Integer id);
    public Object cargarPorIdPropiedadSolo(Integer id);
    public ArrayList listarPorIdPropiedad(Integer id);
    public Object cargarDesdePropiedad(Integer id);
    
}
