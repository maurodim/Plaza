/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Facturar;
import interfacesGraficas.Inicio;
import interfaces.Transaccionable;
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
public class Comprobantes implements Facturar{
    private int numero;
    private String descripcion;
    private int destinatarioCondicion;
    private int descargaStock;
    private ClientesTango cliente;
    private ArrayList listadoDeArticulos;
    private int tipoComprobante;
    private Date fechaEmision;
    private int tipoMovimiento;
    private Double montoTotal;
    private int usuarioGenerador;
    private int idSucursal;
    private int idDeposito;
    private Integer idCaja;
    private Double montoBruto;
    private Double montoIva;
    private Double montoRet;
    private Integer pagado;
    private static Integer numeroComprobante;
    private static Integer idComp;

    public Integer getPagado() {
        return pagado;
    }

    public void setPagado(Integer pagado) {
        this.pagado = pagado;
    }
    
    

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Double getMontoBruto() {
        return montoBruto;
    }

    public void setMontoBruto(Double montoBruto) {
        this.montoBruto = montoBruto;
    }

    public Double getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(Double montoIva) {
        this.montoIva = montoIva;
    }

    public Double getMontoRet() {
        return montoRet;
    }

    public void setMontoRet(Double montoRet) {
        this.montoRet = montoRet;
    }
    

    public ClientesTango getCliente() {
        return cliente;
    }

    public void setCliente(ClientesTango cliente) {
        this.cliente = cliente;
    }

    public ArrayList getListadoDeArticulos() {
        return listadoDeArticulos;
    }

    public void setListadoDeArticulos(ArrayList listadoDeArticulos) {
        this.listadoDeArticulos = listadoDeArticulos;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getUsuarioGenerador() {
        return usuarioGenerador;
    }

    public void setUsuarioGenerador(int usuarioGenerador) {
        this.usuarioGenerador = usuarioGenerador;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }
    

    public Comprobantes(int numero) {
            this.idCaja=0;
        this.idDeposito=0;
        this.idSucursal=0;
        this.numero=0;
        this.tipoComprobante=0;
        this.tipoMovimiento=0;
        this.usuarioGenerador=0;

        this.numero = numero;

    }

    public Comprobantes() {
        this.idCaja=0;
        this.idDeposito=0;
        this.idSucursal=0;
        this.numero=0;
        this.tipoComprobante=0;
        this.tipoMovimiento=0;
        this.usuarioGenerador=0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDestinatarioCondicion() {
        return destinatarioCondicion;
    }

    public void setDestinatarioCondicion(int destinatarioCondicion) {
        this.destinatarioCondicion = destinatarioCondicion;
    }

    public int getDescargaStock() {
        return descargaStock;
    }

    public void setDescargaStock(int descargaStock) {
        this.descargaStock = descargaStock;
    }
    private static void numeroActual(int tipoComprobante){
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */ 
            tra=new ConeccionLocal();
        //}
        String tc="ticket";
        String fc="fca a";
        String tx="";
        if(tipoComprobante==1){
            tx=tc;
        }else{
            tx=fc;
        }
        String sql="select * from tipocomprobantes where descripcion like '"+tx+"%' and numeroSucursal ="+Inicio.sucursal;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroComprobante=rs.getInt("numeroActivo");
            idComp=rs.getInt("numero");   
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ee){
            Inicio.coneccionRemota=false;
            numeroActual(tipoComprobante);
        }
    }
    @Override
    public Boolean guardar(Object oob) {
        // GUARDA EL OBJETO COMPROBANTE COMO EL O LOS MOVIMIENTOS CORRESPONDIENTES
        // EJ: EN UNA FACTURA DE VENTA VOY A TENER MOVIMIENTO DE STOCK DESCONTANDO
        // EN EL DEPOSITO Y DE CAJA SUMANDO EN CAJA DE LA SUCURSAL
        /*
         * ACA VOY A TENER QUE DISTINGUIR EL TIPO DE MOVIMIENTO, VOY A TENER QUE HACER UN 
         * ITERATOR DE LOS ARTICULOS, PERO NO HACE FALTA PARA EL MOVIMIENTO DE CAJA PUESTO 
         * QUE GRABO EL MONTO TOTAL.
         * 
         */
        Comprobantes comp=(Comprobantes)oob;
        Iterator iComp=comp.listadoDeArticulos.listIterator();
        numeroActual(comp.getTipoComprobante());
        numeroComprobante++;
        comp.setNumero(numeroComprobante);
        Transaccionable tra=new ConeccionLocal();
        Articulos articulo=new Articulos();
        Boolean verif=false;
        String sql="";
        while(iComp.hasNext()){
            articulo=(Articulos)iComp.next();
            Double cantidad=articulo.getCantidad() * -1;
            sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeVenta,precioServicio,preciodecosto) values ("+comp.getTipoMovimiento()+","+articulo.getNumeroId()+","+cantidad+","+Inicio.deposito+","+comp.getTipoComprobante()+","+comp.getNumero()+","+comp.getCliente().getCodigoId()+",'"+comp.getFechaEmision()+"',"+comp.getUsuarioGenerador()+","+articulo.getPrecioUnitario()+","+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+")";
            verif=tra.guardarRegistro(sql);
            
        }
        
            sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+comp.getUsuarioGenerador()+","+comp.getIdSucursal()+","+comp.getNumero()+","+comp.getTipoComprobante()+","+comp.getMontoTotal()+","+comp.getTipoMovimiento()+","+comp.getIdCaja()+","+comp.getCliente().getCodigoId()+",1,"+comp.getPagado()+")";
            tra.guardarRegistro(sql);
            sql="insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idUsuario,idCaja,idSucursal,tipoComprobante) values ("+comp.getCliente().getCodigoId()+","+comp.getMontoTotal()+","+comp.getPagado()+","+numeroComprobante+","+Inicio.usuario.getNumeroId()+","+Inicio.caja.getNumero()+","+Inicio.sucursal+","+comp.getTipoComprobante()+")";
            tra.guardarRegistro(sql);
        
        System.out.println("SE RECEPCIONO BARBARO");
        sql="update tipocomprobantes set numeroActivo="+numeroComprobante+" where numero="+idComp;
        /*
        if(Inicio.coneccionRemota){
            
        }else{
        * */
            tra=new ConeccionLocal();
        //}
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarClientes(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        Integer numeroSiguiente=0;
        String sql="select tipocomprobantes.numeroActivo from tipocomprobantes where numero="+numeroComprobante+" and numeroSucursal="+Inicio.sucursal;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                numeroSiguiente=rs.getInt("numeroActivo");
                numeroSiguiente++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numeroSiguiente;
    }
    private Integer numeroComprobante(Integer tipoComp){
               Transaccionable tra=new Conecciones();
               Integer numeroAct=0;
        String sql="select * from tipocomprobantes where numero="+tipoComp+" and numeroSucursal="+Inicio.sucursal;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroAct=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroAct++;
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
