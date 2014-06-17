/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Operaciones {
    private Integer id;
    private String descripcion;
    private Integer destino;
    private Integer valor;
    private Date fecha;
    private Double monto;
    private Contratos contrato;
    private Propietarios propietario;
    private Comprobantes factura;
    private Usuarios usuario;
    private Integer estado;
    private Comprobantes recibo;
    private static ArrayList listadoOp=new ArrayList();
    private static ArrayList listOp=new ArrayList();

    public Operaciones() {
        contrato=new Contratos();
        propietario=new Propietarios();
        factura=new Comprobantes();
        usuario=new Usuarios();
        recibo=new Comprobantes();
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

    public Propietarios getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietarios propietario) {
        this.propietario = propietario;
    }

    public Comprobantes getFactura() {
        return factura;
    }

    public void setFactura(Comprobantes factura) {
        this.factura = factura;
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

    public Comprobantes getRecibo() {
        return recibo;
    }

    public void setRecibo(Comprobantes recibo) {
        this.recibo = recibo;
    }

    public static ArrayList getListOp() {
        return listOp;
    }

    public static ArrayList getListadoOp() {
        return listadoOp;
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

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
    
    public static void cargarArrayCaja(){
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        * */
            tra=new ConeccionLocal();
        //}
        String sql="select * from tipomovimientos where destinoOperacion=2";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoOp.clear();
            while(rs.next()){
                Operaciones operaciones=new Operaciones();
                operaciones.setId(rs.getInt("id"));
                operaciones.setDescripcion(rs.getString("descripcion"));
                operaciones.setDestino(rs.getInt("destinoOperacion"));
                operaciones.setValor(rs.getInt("multiploOp"));
                listadoOp.add(operaciones);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if(Inicio.coneccionRemota)BackapearOperaciones();
    }
    public static void cargarArray(){
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        * */
            tra=new ConeccionLocal();
        //}
        String sql="select * from tipomovimientos";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listOp.clear();
            while(rs.next()){
                Operaciones operaciones=new Operaciones();
                operaciones.setId(rs.getInt("id"));
                operaciones.setDescripcion(rs.getString("descripcion"));
                operaciones.setDestino(rs.getInt("destinoOperacion"));
                operaciones.setValor(rs.getInt("multiploOp"));
                System.err.println(" LISTADO OPERACIONES "+operaciones.getDescripcion());
                listOp.add(operaciones);
                
            }
            rs.close();
            if(listOp.size()==0){
                 tra=new Conecciones();
        //}
        sql="select * from tipomovimientos";
        rs=tra.leerConjuntoDeRegistros(sql);
        
            listOp.clear();
            while(rs.next()){
                Operaciones operaciones=new Operaciones();
                operaciones.setId(rs.getInt("id"));
                operaciones.setDescripcion(rs.getString("descripcion"));
                operaciones.setDestino(rs.getInt("destinoOperacion"));
                operaciones.setValor(rs.getInt("multiploOp"));
                System.err.println(" LISTADO OPERACIONES "+operaciones.getDescripcion());
                listOp.add(operaciones);
                
            }   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void BackapearOperaciones(){
        String sql="delete from tipomovimientos";
        cargarArray();
        Transaccionable tt=new ConeccionLocal();
        tt.guardarRegistro(sql);
        Iterator itO=listOp.listIterator();
        Operaciones oper=new Operaciones();
        while(itO.hasNext()){
            oper=(Operaciones)itO.next();
            sql="insert into tipomovimientos (id,descripcion,destinooperacion,multiploop) values ("+oper.getId()+",'"+oper.getDescripcion()+"',"+oper.getDestino()+","+oper.getValor()+")";
            tt.guardarRegistro(sql);
        }
    }
}
