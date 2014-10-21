package interfaces;

/**
 *
 * @author mauro di
 */
public interface HacerPagoResumen {
    public Object PagarResumen(Integer id,Double Monto);
    public Object MovimientoDePago(Object objeto);
    
}
