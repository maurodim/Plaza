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
public interface Listables {
    public ArrayList listarPorId(Integer id);
    public ArrayList listarPoNombre(String parame);
    public ArrayList listarPorOrdenDeId();
    public ArrayList listarPorOrdenAlfabetico();
    public ArrayList listarPorEstado(Integer esta);
}
