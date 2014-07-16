/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Comisiones implements Generable{
    private Integer id;
    private String descripcion;
    private Double porcentaje;

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

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public void Alta(Object objeto) {
        Comisiones comision=new Comisiones();
        comision=(Comisiones)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into comisiones (descripcion,porcentaje) values ('"+comision.getDescripcion()+"',"+comision.getPorcentaje()+")";
        tra.guardarRegistro(sql);
        
        
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete comisiones where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Comisiones comision=new Comisiones();
        comision=(Comisiones)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update comisiones set descripcion='"+comision.getDescripcion()+"',porcentaje="+comision.getPorcentaje()+" where id"+comision.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        String sql="select * from comisiones";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Comisiones comision=new Comisiones();
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setPorcentaje(rs.getDouble("porcentaje"));
                listado.add(comision);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        Comisiones comision=new Comisiones();
        String sql="select * from comisiones";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setPorcentaje(rs.getDouble("porcentaje"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comision;
    }
    
    
}
