/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface Generable {
    public void Alta(Object objeto);
    public void Baja(Integer id);
    public void Modificacion(Object objeto);
    public ArrayList Listar();
    public Object Cargar(Integer id);
   
}
