/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;


import objetos.ClientesTango;
import interfacesGraficas.Inicio;
import interfaces.Transaccionable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import objetos.Articulos;
import objetos.Cajas;
import objetos.ConeccionLocal;
import objetos.Conecciones;
import objetos.ListasDePrecios;
import objetos.Proveedores;
import objetos.Usuarios;

/**
 *
 * @author mauro
 */
public class Actualiza extends Thread{
    @Override
    public void run(){
        Timer timer=new Timer(500000,new ActionListener(){ 
            @Override
    public void actionPerformed(ActionEvent e) 
    { 
        
        System.err.println("COMIENZO DEL CICLO DE RELOJ *******************************");
        //ActOt at=new ActOt();
        //at.start();
        if(ProbarConeccion()){
            //Inicio.coneccionRemota=true;
            //VerificarErrores();
        
            //carga la lista remota
            //Proveedores.cargarListadoProv1();
        
        Articulos.RecargarMap();
        Articulos.BackapearMap();
        Proveedores.BackapearProveedores();
        ClientesTango.BackapearClientes();
        ListasDePrecios.BackapearListasDePrecios();
        Cajas.BackapearCajas();
        Cajas.LeerCajaAdministradora();
        Usuarios.BackapearUsuarios();
        //Sucursales.BackapearSucursales();
        //Depositos.BackapearDepositos();
        
        
        
        Proveedores.cargarListadoProv();
        ClientesTango.cargarMap();
        ListasDePrecios.cargarMap();
        BkDeConeccion.procesosDeCierre();
        /*
         * Usuarios
         * Sucursales
         * Depositos
         * Comprobante
         * ACTUALIZAR EL NUMERO DE CAJA ADMINISTRADORA
         */
        }else{
            Inicio.coneccionRemota=false;
        }
     } 
}); 
        timer.start();
        
        
        
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
    private void VerificarErrores(){
        String sql="select * from fallas where estado=0";
        ArrayList fallas=new ArrayList();
        /*
         * ACA TENGO QUE CARGAR EL ARRAY CON LAS SENTENCIAS DE FALLAS, CARGARLAS Y LUEGO DESCARGARLAS NE MYSQL
         * TAMBIEN DEBO MODIFICAR EL VALOR DE ESTADO EN DERBY
         * 
         */
        Transaccionable tra=new Conecciones();
        Transaccionable tt=new ConeccionLocal();
        ResultSet rr=tt.leerConjuntoDeRegistros(sql);
        String sentencia="";
        try {
            while(rr.next()){
                sentencia=rr.getString("st");
                fallas.add(sentencia);
                
            }
            rr.close();
            Iterator itF=fallas.listIterator();
            while(itF.hasNext()){
                sentencia=(String)itF.next();
                tra.guardarRegistro(sentencia);
                
            }
            sql="update fallas set estado=1 where estado=0";
            tt.guardarRegistro(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
