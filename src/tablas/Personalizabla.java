/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author mauro
 */
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class Personalizabla extends DefaultTableModel{
   @Override
    public Class getColumnClass(int colum){
        if(colum==0)return Boolean.class;
          
        return String.class;
      }     
}
