/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import Conversores.Numeros;
import interfaces.Componable;
import interfaces.Emitible;
import interfaces.Generable;
import interfaces.Listables;
import interfaces.Montable;
import interfaces.Personalizable;
import interfaces.Propietables;
import interfaces.Resumible;
import interfaces.Saldable;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
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
public class Resumenes implements Generable,Componable,Emitible,Listables,Propietables,Saldable,Resumible{
    private Integer id;
    private Propiedades propiedad;
    private ArrayList gastos;
    private Double montoTotal;
    private Integer numero;
    private Date fecha;
    private Usuarios usuario;
    private String vencimiento;
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

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
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
        Montable mont=new Contratos();
        resumen=(Resumenes)objeto;
        if(resumen.getPropiedad().getId() > 0){
            
        }else{
            System.out.println("la propiedad no esta cargada");
        }
        String sql="insert into resumenes (idpropiedad,idgasto,montototal,idusuario,estado,idconcepto,descripcion) values ("+resumen.getPropiedad().getId()+","+resumen.getIdGasto()+","+resumen.getMontoTotal()+","+resumen.getUsuario().getNumeroId()+","+resumen.getEstado()+","+resumen.getIdConcepto()+",'"+resumen.getDescripcion()+"')";
        tra.guardarRegistro(sql);
        sql="select id from resumenes order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Integer ultimoResumen=0;
        try {
            while(rs.next()){
                ultimoResumen=rs.getInt("id");
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        resumen.setId(ultimoResumen);
        String desc="alquiler";
        sql="insert into cuentas (descripcion,monto,idpropiedad,estado,idusuario,idresumen,vencimiento) values ('"+desc+"',"+mont.LeerMontoActual(resumen.getPropiedad().getContrato().getId())+","+resumen.getPropiedad().getId()+",0,"+Inicio.usuario.getNumeroId()+","+resumen.getId()+","+resumen.getVencimiento()+")";
        System.err.println(sql);
        tra.guardarRegistro(sql);
        objeto=resumen;
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
                resumen.setVencimiento(rs.getString("fechavencimiento"));
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
        Resumible prop=new Propiedades();
        Personalizable per=new Usuarios();
        Resumenes resumen=new Resumenes();
        String sql="select * from resumenes where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                resumen.setId(rs.getInt("id"));
                resumen.setPropiedad((Propiedades)prop.CargarDesdeResumen(rs.getInt("idpropiedad")));
                resumen.setNumero(rs.getInt("numero"));
                resumen.setIdGasto(rs.getInt("idgasto"));
                resumen.setMontoTotal(rs.getDouble("montototal"));
                resumen.setFecha(rs.getDate("fecha"));
                resumen.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                resumen.setVencimiento(rs.getString("fechavencimiento"));
                resumen.setEstado(rs.getInt("estado"));
                
            }
            rs.close();
            Listables ls=new Cuentas();
            resumen.setGastos(ls.listarPorId(resumen.getId()));
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
        mod.addColumn("Num RESUMEN");
        Object[] fila;
        if(id==0){
            mod.addColumn("PROPIEDAD");
            fila=new Object[5];
        }else{
            fila=new Object[4];
        }
        Transaccionable tra=new ConeccionLocal();
        
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="";
        if(id==0){
            sql="select resumenes.fecha,resumenes.descripcion,resumenes.montototal,resumenes.id,(select propiedades.direccion from propiedades where propiedades.id=resumenes.idpropiedad)as propiedad from resumenes order by id desc";
        }else{
            sql="select * from resumenes where estado=0 and idpropiedad="+id+" order by id";
        }
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                fila[0]=rs.getDate("fecha");
                fila[1]=rs.getString("descripcion");
                fila[2]=rs.getDouble("montototal");
                fila[3]=rs.getInt("id");
                if(id==0)fila[4]=rs.getString("propiedad");
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
        MiModeloTablaCargaHdr mod=new MiModeloTablaCargaHdr();
        mod.addColumn("FECHA");
        mod.addColumn("PROPIEDAD");
        mod.addColumn("MONTO");
        mod.addColumn("N RESUMEN");
        Object[] fila=new Object[4];
        Transaccionable tra=new ConeccionLocal();
        Propiedades pp;
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select resumenes.ID,resumenes.IDPROPIEDAD,(resumenes.MONTOTOTAL)as total from resumenes where estado=0";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                pp=new Propiedades();
                pp=(Propiedades) prop.Cargar(rs.getInt("idpropiedad"));
                fila[0]=true;
                fila[1]=pp.getDireccion();
                fila[2]=rs.getDouble("total");
                fila[3]=rs.getInt("id");
                mod.addRow(fila);   
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }

    @Override
    public ArrayList listarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorIdPropiedad(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        //ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Resumenes resumen=new Resumenes();
        String sql="select * from resumenes where idpropiedad="+id+" and estado=0";
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
                resumen.setVencimiento(rs.getString("fechavencimiento"));
                resumen.setEstado(rs.getInt("estado"));
                
            }
            rs.close();
            Listables ls=new Cuentas();
            resumen.setGastos(ls.listarPorId(resumen.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resumen;
    }

    @Override
    public ArrayList listarPorIdPropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorIdPropiedadSolo(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        //ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Resumenes resumen=new Resumenes();
        String sql="select * from resumenes where idpropiedad="+id+" and estado=0";
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
                resumen.setVencimiento(rs.getString("fechavencimiento"));
                resumen.setEstado(rs.getInt("estado"));
                
            }
            rs.close();
            //Listables ls=new Cuentas();
            //resumen.setGastos(ls.listarPorId(resumen.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resumen;
    }

    @Override
    public void GuardarArrayParaEmitir(ArrayList listado) {
        //ACA HAGO UN UPDATE DE A FECHA DE VENCIMIENTO, EL ESTADO DE LOS RESUMENES E ITEMS DE LAS CUENTAS
        //AGREGO TAMBIEN COMO ITEMS EL VALOR DEL ALQUILER
        Transaccionable tra=new ConeccionLocal();
        Iterator it=listado.listIterator();
        Montable mont=new Contratos();
        String sql="";
        Resumenes resumen=new Resumenes();
        Cuentas cuenta=new Cuentas();
        Generable gen=new Resumenes();
        while(it.hasNext()){
            resumen=(Resumenes)it.next();
            sql="update resumenes set fechavencimiento='"+resumen.getVencimiento()+"', estado=1 where id="+resumen.getId();
            tra.guardarRegistro(sql);
            //aca debo leer el monto del alquiler e insertarlo como items de ctas
            //sql="insert into cuentas (descripcion,monto,idpropiedad,estado,idusuario,idresumen,vencimiento) values ('alquiler',"+mont.LeerMontoActual(resumen.getPropiedad().getContrato().getId())+","+resumen.getPropiedad().getId()+",1,"+Inicio.usuario.getNumeroId()+","+resumen.getId()+","+resumen.getVencimiento()+")";
            //tra.guardarRegistro(sql);
            sql="update cuentas set vencimiento='"+resumen.getVencimiento()+"', estado=1 where idresumen="+resumen.getId();
            tra.guardarRegistro(sql);
            
            
        }
    }

    @Override
    public Double calcularSaldoActual(Integer id) {
        //SE CALCULA EL SALDO POR EL ID DEL CONTRATO, ASI SIRVE PARA TODOS LOS PARTICIPANTES
        // INQUILINOS, PROPIETARIOS Y PROPIEDADES
        Double saldo=0.00;
        Double total=0.00;
        Integer dias=0;
        String sql="select * from resumenes where idpropiedad="+id+" and estado=1";
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                if(Inicio.fechaVal.after(rs.getDate("fechavencimiento"))){
                    dias=Numeros.CalcularDiasAFechaActual(rs.getDate("fechavencimiento"));
                    saldo=rs.getDouble("saldo") * Inicio.porcentajeRecargo * dias;
                    System.out.println("SALDO :"+saldo+" propiedad "+id);
                }
                total=total + saldo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    @Override
    public Boolean ajustarSaldo(Integer id, Double movimiento) {
        Boolean correcto=false;
        String sql;
        if(movimiento==0.00){
            sql="update resumenes set saldo="+movimiento+",estado=2 where id="+id;
        }else{
            sql="update resumenes set saldo="+movimiento+" where id="+id;
        }
        Transaccionable tra=new ConeccionLocal();
        correcto=tra.guardarRegistro(sql);
        
        return correcto;
    }

    @Override
    public Object GenerarNuevoResumen(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Resumenes resumen=new Resumenes();
        Montable mon=new Contratos();
        Contratos contrato=new Contratos();
        contrato=(Contratos)objeto;
        String sql="insert into resumenes (idpropiedad,idgasto,montototal,idusuario,estado,idconcepto,descripcion) values ("+resumen.getPropiedad().getId()+","+resumen.getIdGasto()+","+resumen.getMontoTotal()+","+resumen.getUsuario().getNumeroId()+","+resumen.getEstado()+","+resumen.getIdConcepto()+",'"+resumen.getDescripcion()+"')";
        tra.guardarRegistro(sql);
        sql="select id from resumenes order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Integer ultimoResumen=0;
        try {
            while(rs.next()){
                ultimoResumen=rs.getInt("id");
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        resumen.setId(ultimoResumen);
        return resumen;
    }

    @Override
    public Object cargarDesdePropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarDesdeResumen(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double AjustarMontoTotal(Integer id) {
        Double monto=0.00;
        String sql="select * from cuentas where idresumen="+id+" and estado=0";
        System.out.println(sql);
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                monto=monto + rs.getDouble("monto");
            }
            rs.close();
            sql="update resumenes set montototal="+monto+" where id="+id;
            tra.guardarRegistro(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monto;
    }
    
    
}
