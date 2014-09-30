
package interfaces;

/**
 *
 * @author mauro di
 */
public interface Resumible {
    public Object GenerarNuevoResumen(Object objeto);
    public Object CargarDesdeResumen(Integer id);
    public Double AjustarMontoTotal(Integer id);
}
