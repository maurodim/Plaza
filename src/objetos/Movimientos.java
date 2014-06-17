/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Movimientos implements Generable{
    private Integer id;
    private Date fecha;
    private Double monto;
    private Contratos contrato;
    private Inquilinos inquilino;
    private Comprobantes resumenes;
    private Comprobantes recibo;
    private Usuarios usuario;
    private Integer estado;

    public Movimientos() {
        contrato=new Contratos();
        inquilino=new Inquilinos();
        resumenes=new Comprobantes();
        recibo=new Comprobantes();
        usuario=new Usuarios();
        
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Contratos getContrato() {
        return contrato;
    }

    public void setContrato(Contratos contrato) {
        this.contrato = contrato;
    }

    public Inquilinos getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilinos inquilino) {
        this.inquilino = inquilino;
    }

    public Comprobantes getResumenes() {
        return resumenes;
    }

    public void setResumenes(Comprobantes resumenes) {
        this.resumenes = resumenes;
    }

    public Comprobantes getRecibo() {
        return recibo;
    }

    public void setRecibo(Comprobantes recibo) {
        this.recibo = recibo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
    
    
}
