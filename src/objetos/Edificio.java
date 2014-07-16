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
public class Edificio implements Generable{
    private Integer id;
    private String descripcion;
    private String domicilio;

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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public void Alta(Object objeto) {
        Edificio comision=new Edificio();
        comision=(Edificio)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into edificio (descripcion,direccion) values ('"+comision.getDescripcion()+"','"+comision.getDomicilio()+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete edificio where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Edificio comision=new Edificio();
        comision=(Edificio)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update edificio set=descripcion'"+comision.getDescripcion()+"',direccion='"+comision.getDomicilio()+"' where id="+comision.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        String sql="select * from edificio";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Edificio comision=new Edificio();
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setDomicilio(rs.getString("direccion"));
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
        Edificio comision=new Edificio();
        String sql="select * from edificio";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setDomicilio(rs.getString("direccion"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comision;
    }
    
    
}
