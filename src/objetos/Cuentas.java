/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Cuentas implements Generable{
    private Integer id;
    private String descripcion;
    private Edificio edificio;
    private Propiedades propiedad;
    private Date fechaalta;
    private Integer estado;
    private Usuarios usuario;
    private Double monto;

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
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

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Alta(Object objeto) {
        Cuentas cuenta=new Cuentas();
        cuenta=(Cuentas)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into cuentas (descripcion,monto,idedificio,idpropiedad,estado,idusuario) values ('"+cuenta.getDescripcion()+"',"+cuenta.getMonto()+","+cuenta.getEdificio().getId()+","+cuenta.getPropiedad().getId()+","+cuenta.getEstado()+","+cuenta.getUsuario().getNumeroId()+")";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from cuentas where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Cuentas cuenta=new Cuentas();
        cuenta=(Cuentas)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update cuentas set descripcion='"+cuenta.getDescripcion()+"',monto="+cuenta.getMonto()+",idedificio="+cuenta.getEdificio().getId()+",idpropiedad="+cuenta.getPropiedad().getId()+",estado="+cuenta.getEstado()+",idusuario="+cuenta.getUsuario().getNumeroId()+" where id="+cuenta.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Generable edif=new Edificio();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from cuentas";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Cuentas cuentas=new Cuentas();
                cuentas.setId(rs.getInt("id"));
                cuentas.setDescripcion(rs.getString("descripcion"));
                cuentas.setMonto(rs.getDouble("monto"));
                if(rs.getInt("idedificio") > 0)cuentas.setEdificio((Edificio)edif.Cargar(rs.getInt("idedificio")));
                cuentas.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                cuentas.setFechaalta(rs.getDate("fechaalta"));
                cuentas.setEstado(rs.getInt("estado"));
                cuentas.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                listado.add(cuentas);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        //ArrayList listado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Generable edif=new Edificio();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Cuentas cuentas=new Cuentas();
        String sql="select * from cuentas where id"+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                cuentas.setId(rs.getInt("id"));
                cuentas.setDescripcion(rs.getString("descripcion"));
                cuentas.setMonto(rs.getDouble("monto"));
                if(rs.getInt("idedificio") > 0)cuentas.setEdificio((Edificio)edif.Cargar(rs.getInt("idedificio")));
                cuentas.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                cuentas.setFechaalta(rs.getDate("fechaalta"));
                cuentas.setEstado(rs.getInt("estado"));
                cuentas.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                //listado.add(cuentas);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cuentas;
    }
    
    
    
}
