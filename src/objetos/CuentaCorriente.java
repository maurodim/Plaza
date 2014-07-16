/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Facturar;
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
public class CuentaCorriente implements Generable{
    private Integer id;
    private Inquilinos inquilino;
    private Double monto;
    private Comprobantes recibo;
    private Integer tipoMovimiento;
    private Usuarios usuario;
    private Resumenes resumen;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public Resumenes getResumen() {
        return resumen;
    }

    public void setResumen(Resumenes resumen) {
        this.resumen = resumen;
    }
    

    public CuentaCorriente() {
        inquilino=new Inquilinos();
        recibo=new Comprobantes();
        usuario=new Usuarios();
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inquilinos getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilinos inquilino) {
        this.inquilino = inquilino;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Comprobantes getRecibo() {
        return recibo;
    }

    public void setRecibo(Comprobantes recibo) {
        this.recibo = recibo;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Alta(Object objeto) {
        CuentaCorriente cta=new CuentaCorriente();
        cta=(CuentaCorriente)objeto;
        String sql="insert into cuentascorrientes (idinquilino,idresumen,monto,idrecibo,tipomovimiento,idusuario) values ("+cta.getInquilino().getId()+","+cta.getResumen().getId()+","+cta.getMonto()+","+cta.getRecibo().getNumero()+","+cta.getTipoMovimiento()+","+cta.getUsuario().getNumeroId()+")";
        Transaccionable tra=new ConeccionLocal();
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete cuentacorrientes where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        CuentaCorriente cta=new CuentaCorriente();
        cta=(CuentaCorriente)objeto;
        String sql="update cuentascorrientes set idinquilino="+cta.getInquilino().getId()+",idresumen="+cta.getResumen().getId()+",monto="+cta.getMonto()+",idrecibo="+cta.getRecibo().getNumero()+",tipomovimiento="+cta.getTipoMovimiento()+" where id="+cta.getId();
        Transaccionable tra=new ConeccionLocal();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Generable inq=new Inquilinos();
        Generable res=new Resumenes();
        Facturar fact=new Comprobantes();
        Personalizable per=new Usuarios();
        String sql="select * from cuentascorrientes";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                CuentaCorriente cta=new CuentaCorriente();
                cta.setId(rs.getInt("id"));
                cta.setInquilino((Inquilinos)inq.Cargar(rs.getInt("idinquilino")));
                cta.setResumen((Resumenes)res.Cargar(rs.getInt("idresumen")));
                cta.setRecibo((Comprobantes)fact.cargarPorCodigoAsignado(rs.getInt("idrecibo")));
                cta.setMonto(rs.getDouble("monto"));
                cta.setTipoMovimiento(rs.getInt("tipomovimiento"));
                cta.setFecha(rs.getDate("fecha"));
                listado.add(cta);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        //ArrayList listado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Generable inq=new Inquilinos();
        Generable res=new Resumenes();
        Facturar fact=new Comprobantes();
        Personalizable per=new Usuarios();
        CuentaCorriente cta=new CuentaCorriente();
        String sql="select * from cuentascorrientes where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                cta.setId(rs.getInt("id"));
                cta.setInquilino((Inquilinos)inq.Cargar(rs.getInt("idinquilino")));
                cta.setResumen((Resumenes)res.Cargar(rs.getInt("idresumen")));
                cta.setRecibo((Comprobantes)fact.cargarPorCodigoAsignado(rs.getInt("idrecibo")));
                cta.setMonto(rs.getDouble("monto"));
                cta.setTipoMovimiento(rs.getInt("tipomovimiento"));
                cta.setFecha(rs.getDate("fecha"));
                //listado.add(cta);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cta;
    }
    
    
}
