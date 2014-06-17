/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author mauro
 */
public interface Editables {
    public Boolean AltaObjeto(Object objeto);
    public Boolean ModificaionObjeto(Object objeto);
    public Boolean EliminacionDeObjeto(Object objeto);
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto,Double cantidadMovimiento,String observaciones);
    
}
