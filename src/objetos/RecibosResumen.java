/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Generable;
import interfaces.HacerPagoResumen;
import interfaces.Transaccionable;
import java.util.ArrayList;

/**
 *
 * @author mauro di
 */
public class RecibosResumen implements Generable,HacerPagoResumen{
   private Integer id;
   private Integer idRecibo;
   private String descripcion;
   private Double monto;
   private Integer idUsuario;
   private Integer idResumen;
   private Double saldo;
   private Double recargo;
   private Double saldoOriginal;

    public Double getSaldoOriginal() {
        return saldoOriginal;
    }

    public void setSaldoOriginal(Double saldoOriginal) {
        this.saldoOriginal = saldoOriginal;
    }
   

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }
   

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
   

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public void Alta(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public Object PagarResumen(Integer id, Double Monto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object MovimientoDePago(Object objeto) {
        //ACA HAGO LAS CARGA DE MODIFICACION DE LO MOVIMIENTOS
        
        
        RecibosResumen recib=new RecibosResumen();
        recib=(RecibosResumen)objeto;
        System.out.println("recibo resumen "+recib.getIdResumen()+" //monto "+recib.getMonto()+" recibo id "+recib.getIdRecibo());
        int estado=0;
        if(recib.getSaldo() > 0){
            estado=1;
        }else{
            estado=2;
        }
        String sql1="update resumenes set estado="+estado+", saldo="+recib.getSaldo()+" where id="+recib.getIdResumen();
        Transaccionable tran=new ConeccionLocal();
        tran.guardarRegistro(sql1);
        
        
        return recib;
    }
   
}
