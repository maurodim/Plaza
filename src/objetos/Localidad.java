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
public class Localidad implements Generable{
    private Integer id;
    private String descripcion;
    private String codigo;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public void Alta(Object objeto) {
        Localidad comision=new Localidad();
        comision=(Localidad)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into localidad (descripcion) values ('"+comision.getDescripcion()+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from localidad where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Localidad comision=new Localidad();
        comision=(Localidad)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update localidad set descripcion='"+comision.getDescripcion()+"',codigo='"+comision.getCodigo()+"' where id="+comision.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        String sql="select * from localidad";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Localidad comision=new Localidad();
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setCodigo(rs.getString("codigo"));
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
        Localidad comision=new Localidad();
        String sql="select * from localidad";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                comision.setId(rs.getInt("id"));
                comision.setDescripcion(rs.getString("descripcion"));
                comision.setCodigo(rs.getString("codigo"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comisiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comision;
    }
    
    
}
