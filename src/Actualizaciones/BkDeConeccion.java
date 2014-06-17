/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;



import interfacesGraficas.Inicio;
import interfaces.Backpeable;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import objetos.ConeccionLocal;
import objetos.Conecciones;
import objetos.Menus;
import objetos.Usuarios;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author mauro
 */
public class BkDeConeccion implements Backpeable{
    private long elements;
    private long attributes;
    private long caracteres;
    private long espaciosEnBlanco;
    
    public static Boolean guardarSentencias(String sql){
        Boolean verif=false;
        Transaccionable tra=new Conecciones();
        
            verif=tra.guardarRegistro(sql);
        
        return verif;
    }
    public static Boolean procesosDeCierre(){
        Boolean ver=false;
        Transaccionable tra=new Conecciones();
        Transaccionable tt=new ConeccionLocal();
        String sql="select * from movimientosarticulos where estado is null";
        String sentencia="";
        ResultSet rs=tt.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                sentencia="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeCosto,precioDeVenta,precioServicio) values ("+rs.getInt("tipomovimiento")+","+rs.getInt("idarticulo")+","+rs.getDouble("cantidad")+","+rs.getInt("numerodeposito")+","+rs.getInt("tipocomprobante")+","+rs.getInt("numerocomprobante")+","+rs.getInt("numerocliente")+",'"+rs.getString("fechacomprobante")+"',"+rs.getInt("numerousuario")+","+rs.getDouble("preciodecosto")+","+rs.getDouble("preciodeventa")+","+rs.getDouble("precioservicio")+")";
                tra.guardarRegistro(sentencia);
                
            }
            rs.close();
            sql="update movimientosarticulos set estado=1";
            tt.guardarRegistro(sql);
            sql="select * from movimientoscaja where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia="insert into movimientoscaja (numeroUsuario,idCliente,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,cantidad,pagado,observaciones,tipoCliente) values ("+rs.getInt("numerousuario")+","+rs.getInt("idcliente")+","+rs.getInt("numerosucursal")+","+rs.getInt("numerocomprobante")+","+rs.getInt("tipocomprobante")+","+rs.getDouble("monto")+","+rs.getInt("tipomovimiento")+","+rs.getInt("idcaja")+","+rs.getDouble("cantidad")+","+rs.getInt("pagado")+",'"+rs.getString("observaciones")+"',"+rs.getInt("tipocliente")+")";
                tra.guardarRegistro(sentencia);
            }
            rs.close();
            sql="update movimientoscaja set estado=1";
            tt.guardarRegistro(sql);
            sql="select * from movimientosclientes where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia="insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,tipoComprobante,idSucursal) values ("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+","+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+")";
                tra.guardarRegistro(sentencia);
            }
            rs.close();
            sql="update movimientosclientes set estado =1";
            tt.guardarRegistro(sql);
            sql="select * from movimientosproveedores where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia="insert into movimientosproveedores (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,fechaPago,tipoComprobante,idSucursal) values ("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+",'"+rs.getString("fechapago")+"',"+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+")";
                tra.guardarRegistro(sentencia);
            }
            rs.close();
            sql="update movimientosproveedores set estado=1";
            tt.guardarRegistro(sql);
            sql="select * from tipocomprobantes";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia="update tipocomprobantes set numeroActivo="+rs.getInt("numeroactivo")+" where numero="+rs.getInt("numero");
                tra.guardarRegistro(sentencia);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return ver;
    }
    private Boolean ProbarConeccion(){
        Boolean verif=false;
        String sql="select * from articulos limit 0,1";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                verif=true;
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ee){
            return false;
        }
        return verif;
    }
    @Override
    public Hashtable leerArticulos() {
        Hashtable art=new Hashtable();
        
        return art;
    }

    @Override
    public Hashtable leerProveedores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Hashtable leerClientes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerDeposito() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean backapearObjetos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerUsuarios(String nombre, String clave) {
         Usuarios usu=new Usuarios();
        try {
           Inicio.coneccionRemota=ProbarConeccion();
       Transaccionable tras=new ConeccionLocal();
                       String sql="select * from usuarios where nombreUsuario like '"+nombre+"' and clave like '"+clave+"'";
                       System.out.println(sql);
                       ResultSet rs=tras.leerConjuntoDeRegistros(sql);
                   
                           
                      
                        while(rs.next()){
                        usu=new Usuarios();
                        usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
                        usu.setNombre(rs.getString("nombre"));
                        usu.setNumero(rs.getInt("numero"));
                        usu.setNumeroId(rs.getInt("numero"));
                        usu.setSucursal(1);
                        
                             
                        }
                        sql="select * from tipoacceso where numero="+usu.getNivelDeAutorizacion();
                        System.out.println(sql);
                        rs=tras.leerConjuntoDeRegistros(sql);
                        while(rs.next()){
                               usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5"),rs.getBoolean("menu6"),rs.getBoolean("menu7")));                    
                           
                        }
                        rs.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
         return usu;
    }
    }

