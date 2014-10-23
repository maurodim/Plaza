/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Generable;
import interfaces.HacerPagoResumen;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro di
 */
public class Recibos implements Generable{
    private Integer id;
    private Integer idPropiedad;
    private Integer idContrato;
    private Integer idInquilino;
    private Double monto;
    private String fecha;
    private Integer idUsuario;
    private Integer numero;
    private ArrayList resumenRecibos;
    

    public Recibos() {
        resumenRecibos=new ArrayList();
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Integer idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(Integer idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public ArrayList getResumenRecibos() {
        return resumenRecibos;
    }

    public void setResumenRecibos(ArrayList resumenRecibos) {
        this.resumenRecibos = resumenRecibos;
    }

    @Override
    public void Alta(Object objeto) {
        //ACA DOY DE ALTA EL RECIBO, GENERO LOS MOVIMIENTOS DE DETALLE, DE PAGO DE RESUMEN Y MOVIMIENTO DE CAJA
        
        Recibos recibo=new Recibos();
        recibo=(Recibos)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into recibos (idpropiedad,idcontrato,idinquilino,monto,fecha,idusuario,numero) values ("+recibo.getIdPropiedad()+","+recibo.getIdContrato()+","+recibo.getIdInquilino()+","+recibo.getMonto()+",'"+recibo.getFecha()+"',"+recibo.getIdUsuario()+","+recibo.getNumero()+")";
        tra.guardarRegistro(sql);
        sql="select recibos.id from recibos order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                recibo.setId(rs.getInt("id"));
                //rs.last();
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Recibos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator itR=recibo.getResumenRecibos().listIterator();
        RecibosResumen resumenR=new RecibosResumen();
        HacerPagoResumen ge=new RecibosResumen();
        while(itR.hasNext()){
            resumenR=(RecibosResumen)itR.next();
            resumenR.setIdRecibo(recibo.getId());
            //aca cargo los movimientos en la tabla movimientos clientes
            if(resumenR.getRecargo() > 0){
                sql="insert into movimientosclientes (numeroproveedor,monto,pagado,numerocomprobante,idremito,idusuario,idcaja,tipocomprobante,idsucursal,estado) values ("+recibo.getIdPropiedad()+","+resumenR.getRecargo() * (-1)+",1,"+recibo.getId()+",6,"+Inicio.usuario.getNumeroId()+","+Inicio.caja.getNumero()+",5,1,1)";
                tra.guardarRegistro(sql);
            }
                sql="insert into movimientosclientes (numeroproveedor,monto,pagado,numerocomprobante,idremito,idusuario,idcaja,tipocomprobante,idsucursal,estado) values ("+recibo.getIdPropiedad()+","+resumenR.getSaldoOriginal() * (-1)+",1,"+recibo.getId()+",5,"+Inicio.usuario.getNumeroId()+","+Inicio.caja.getNumero()+",5,1,1)";
                tra.guardarRegistro(sql);
            
            
            ge.MovimientoDePago(resumenR);
        }
        sql="insert into movimientoscaja (numerousuario,idcliente,numerosucursal,numerocomprobante,tipocomprobante,monto,tipomovimiento,idcaja,pagado,estado,tipocliente) values ("+Inicio.usuario.getNumeroId()+","+recibo.getIdInquilino()+",1,"+recibo.getId()+",5,"+recibo.getMonto()+",13,"+Inicio.caja.getNumero()+",1,1,1)";
        tra.guardarRegistro(sql);
        
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object Cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
