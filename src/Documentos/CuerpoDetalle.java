/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

import java.util.ArrayList;

/**
 *
 * @author mauro di
 */
public class CuerpoDetalle {
    private ArrayList detalle;
    private Double total;

    public void setDetalle(ArrayList detalle) {
        this.detalle = detalle;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
    public ArrayList getDetalle() {
        return detalle;
    }

    public Double getTotal() {
        return total;
    }
    
}
