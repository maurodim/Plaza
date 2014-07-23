/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfacesGraficas.Inicio;
import interfaces.Transaccionable;
import interfaces.Cajeables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Billetes {
    private static ArrayList listadoBill=new ArrayList();
    private Integer id;
    private String descripcion;
    private Double valor;
    private Double valorIndividual;

    public static ArrayList getListadoBill() {
        return listadoBill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorIndividual() {
        return valorIndividual;
    }

    public void setValorIndividual(Double valorIndividual) {
        this.valorIndividual = valorIndividual;
    }
    
    public static void cargarLista(){
        
        
            listadoBill.clear();
            
                Billetes billetes=new Billetes();
                billetes.setDescripcion("0.10");
                billetes.setId(1);
                billetes.setValor(0.10);
                
                listadoBill.add(billetes);
                billetes.setDescripcion("0.50");
                billetes.setId(2);
                billetes.setValor(0.50);
                
                listadoBill.add(billetes);
                billetes.setDescripcion("1.00");
                billetes.setId(3);
                billetes.setValor(1.00);
                
                listadoBill.add(billetes);
            
                
           
        
    }

    
    
}
