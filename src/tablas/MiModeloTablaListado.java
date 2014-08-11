/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public class MiModeloTablaListado extends DefaultTableModel{
    @Override
    public Class getColumnClass(int colum){
        
          
        return String.class;
      }    
}
