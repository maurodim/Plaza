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
public class Rubro implements Generable{
    private Integer id;
    private String descripcion;

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

    @Override
    public void Alta(Object objeto) {
        Rubro comision=new Rubro();
        comision=(Rubro)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into rubro (descripcion) values ('"+comision.getDescripcion()+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificacion(Object objeto) {
        Rubro comision=new Rubro();
        comision=(Rubro)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update rubro set descripcion='"+comision.getDescripcion()+"' where id="+comision.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        String sql="select * from rubro";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Rubro comision=new Rubro();
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                
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
        Rubro comision=new Rubro();
        String sql="select * from rubro";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comision;
    }
    
    
}
