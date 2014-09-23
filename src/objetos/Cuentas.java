/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Generable;
import interfaces.Listables;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaCargaHdr;

/**
 *
 * @author Usuario
 */
public class Cuentas implements Generable,Listables,Componable{
    private Integer id;
    private String descripcion;
    private Edificio edificio;
    private Propiedades propiedad;
    private Date fechaalta;
    private Integer estado;
    private Usuarios usuario;
    private Double monto;
    private Integer idResumen;

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }
    

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
        String sql="insert into cuentas (descripcion,monto,idedificio,idpropiedad,estado,idusuario,idresumen) values ('"+cuenta.getDescripcion()+"',"+cuenta.getMonto()+","+cuenta.getEdificio().getId()+","+cuenta.getPropiedad().getId()+","+cuenta.getEstado()+","+cuenta.getUsuario().getNumeroId()+","+cuenta.getIdResumen()+")";
        tra.guardarRegistro(sql);
        sql="select * from cuentas";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        int id=0;
        try {
            while(rs.next()){
                id=rs.getByte("id");
            }
            rs.close();
            cuenta.setId(id);
        } catch (SQLException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public ArrayList listarPorId(Integer id) {
        ArrayList listado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Generable edif=new Edificio();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from cuentas where idresumen="+id;
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
    public ArrayList listarPoNombre(String parame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorOrdenDeId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorOrdenAlfabetico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorEstado(Integer esta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorPropietario(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComboBoxModel LlenarCombo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarListConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        MiModeloTablaCargaHdr mod=new MiModeloTablaCargaHdr();
        mod.addColumn("Emite ?");
        mod.addColumn("PROPIEDAD");
        mod.addColumn("MONTO");
        mod.addColumn("Num resumen");
        Object[] fila=new Object[4];
        Cuentas cta=new Cuentas();
        Propiedades pp=new Propiedades();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Double total=0.00;
        Iterator itl=listado.listIterator();
            while(itl.hasNext()){
                cta=(Cuentas)itl.next();
                pp=(Propiedades) cta.getPropiedad();
                fila[0]=true;
                fila[1]=pp.getDireccion();
                fila[2]=cta.getMonto();
                fila[3]=cta.getIdResumen();
                total=total + cta.getMonto();
                mod.addRow(fila);
                
                /*
                Resumenes resumen=new Resumenes();
                resumen.setId(rs.getInt("id"));
                resumen.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                resumen.setNumero(rs.getInt("numero"));
                resumen.setIdGasto(rs.getInt("idgasto"));
                resumen.setMontoTotal(rs.getDouble("montototal"));
                resumen.setFecha(rs.getDate("fecha"));
                resumen.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                resumen.setVencimiento(rs.getDate("fechavencimiento"));
                resumen.setEstado(rs.getInt("estado"));
                resumen.setDescripcion(rs.getString("descripcion"));
                */
                
            }
                fila[0]=false;
                fila[1]="";
                fila[2]="TOTAL";
                fila[3]=total;
                mod.addRow(fila);
        
        
        return mod;
    }
    
    
    
}
