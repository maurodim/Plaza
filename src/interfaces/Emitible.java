/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Emitible {
    public DefaultTableModel LlenarTablaParaSeleccionar();
    public void GuardarArrayParaEmitir(ArrayList listado);
}
