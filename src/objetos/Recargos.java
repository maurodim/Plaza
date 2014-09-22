/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Date;

/**
 *
 * @author mauro di
 */
public class Recargos {
    private Double coeficiente;
    private Double monto;
    private Integer dias;
    private Date fechaVencimiento;
    private Integer id;

    public Double getMonto() {
        return monto;
    }

    public Recargos(Date fechaVencimiento, Integer id) {
        this.fechaVencimiento = fechaVencimiento;
        this.id = id;
        
    }
    
    
}
