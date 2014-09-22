/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Emitible;
import interfaces.Generable;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Resumenes implements Generable,Componable,Emitible{
    private Integer id;
    private Propiedades propiedad;
    private ArrayList gastos;
    private Double montoTotal;
    private Integer numero;
    private Date fecha;
    private Usuarios usuario;
    private Date vencimiento;
    private Integer estado;
    private Integer idGasto;
    private String descripcion;
    private Integer idConcepto;

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }
    

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public ArrayList getGastos() {
        return gastos;
    }

    public void setGastos(ArrayList gastos) {
        this.gastos = gastos;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void Alta(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Resumenes resumen=new Resumenes();
        resumen=(Resumenes)objeto;
        String sql="insert into resumenes (idpropiedad,idgasto,montototal,idusuario,estado,idconcepto,descripcion) values ("+resumen.getPropiedad().getId()+","+resumen.getIdGasto()+","+resumen.getMontoTotal()+","+resumen.getUsuario().getNumeroId()+","+resumen.getEstado()+","+resumen.getIdConcepto()+",'"+resumen.getDescripcion()+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from resumenes where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Resumenes resumen=new Resumenes();
        resumen=(Resumenes)objeto;
        String sql="update resumenes set idpropiedad="+resumen.getPropiedad().getId()+",numero="+resumen.getNumero()+",idgasto="+resumen.getIdGasto()+",montototal="+resumen.getMontoTotal()+",idusuario="+resumen.getUsuario().getNumeroId()+",fechavencimiento='"+resumen.getVencimiento()+",estado="+resumen.getEstado()+" where id="+resumen.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from resumenes order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
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
                listado.add(resumen);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        //ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Resumenes resumen=new Resumenes();
        String sql="select * from resumenes order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                resumen.setId(rs.getInt("id"));
                resumen.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                resumen.setNumero(rs.getInt("numero"));
                resumen.setIdGasto(rs.getInt("idgasto"));
                resumen.setMontoTotal(rs.getDouble("montototal"));
                resumen.setFecha(rs.getDate("fecha"));
                resumen.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                resumen.setVencimiento(rs.getDate("fechavencimiento"));
                resumen.setEstado(rs.getInt("estado"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resumen;
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        DefaultTableModel mod=new DefaultTableModel();
        mod.addColumn("FECHA");
        mod.addColumn("DESCRIPCION");
        mod.addColumn("MONTO");
        mod.addColumn("USUARIO");
        Object[] fila=new Object[4];
        Transaccionable tra=new ConeccionLocal();
        
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from resumenes where estado=0 and idpropiedad="+id+" order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                fila[0]=rs.getDate("fecha");
                fila[1]=rs.getString("descripcion");
                fila[2]=rs.getDouble("montototal");
                fila[3]="ADMINISTRADOR";
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
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return mod;
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
    public DefaultTableModel LlenarTablaParaSeleccionar() {
        DefaultTableModel mod=new DefaultTableModel();
        mod.addColumn("FECHA");
        mod.addColumn("PROPIEDAD");
        mod.addColumn("MONTO");
        mod.addColumn("USUARIO");
        Object[] fila=new Object[4];
        Transaccionable tra=new ConeccionLocal();
        Propiedades pp;
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select resumenes.IDPROPIEDAD,sum(resumenes.MONTOTOTAL)as total from resumenes where estado=0 group by resumenes.IDPROPIEDAD";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                pp=new Propiedades();
                pp=(Propiedades) prop.Cargar(rs.getInt("idpropiedad"));
                fila[0]=true;
                fila[1]=pp.getDireccion();
                fila[2]=rs.getDouble("total");
                fila[3]="ADMINISTRADOR";
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
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return mod;
    }
    
    
}
