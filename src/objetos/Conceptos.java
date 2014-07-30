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
public class Conceptos implements Generable{
    private Integer id;
    private String descripcion;
    private Double monto;

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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public void Alta(Object objeto) {
        Conceptos concepto=new Conceptos();
        concepto=(Conceptos)objeto;
        String sql="insert into conceptos(descripcion,monto) values ('"+concepto.getDescripcion()+"',"+concepto.getMonto()+")";
        Transaccionable tra=new ConeccionLocal();
        tra.guardarRegistro(sql);
        sql="select * from conceptos";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        int id=0;
        try {
            while(rs.next()){
                id=rs.getInt("id");
            }
            rs.close();
            concepto.setId(id);
        } catch (SQLException ex) {
            Logger.getLogger(Conceptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Baja(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificacion(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        String sql="select * from conceptos";
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Conceptos concepto=new Conceptos();
                concepto.setId(rs.getInt("id"));
                concepto.setDescripcion(rs.getString("descripcion"));
                concepto.setMonto(rs.getDouble("monto"));
                listado.add(concepto);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conceptos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        String sql="select * from conceptos where id="+id;
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Conceptos concepto=new Conceptos();
        try {
            while(rs.next()){
                
                concepto.setId(rs.getInt("id"));
                concepto.setDescripcion(rs.getString("descripcion"));
                concepto.setMonto(rs.getDouble("monto"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conceptos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return concepto;
    }
    
    
}
