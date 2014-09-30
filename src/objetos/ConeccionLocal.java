/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro
 */
public class ConeccionLocal implements Transaccionable{
    private Connection con;
    private Statement st;
    private String driver1="org.apache.derby.jdbc.EmbeddedDriver";

    public Connection getCon() {
        return con;
    }
    
    
    public ConeccionLocal(){
            
              Connection dbConnection = null;
              
              int local=2;
              int remota=1;
              //Integer leido=Integer.parseInt(strUrl);
 //String strUrl = "jdbc:derby://localhost:1527/respaldo;create=true";
              //\\Fincorp-pc\c\Gestion\DBG
              //archivoBase.trim();
              
              /*
              if(remota==leido)strUrl="jdbc:derby:\\\\Fincorp-pc\\c\\Gestion\\DBG\\gestion.db";
              if(local==leido)strUrl="jdbc:derby:C:\\Gestion\\DBG\\gestion.db";
              */
              String strUrl="jdbc:derby:C:\\Gestion\\DBG\\gestion.db";
               //strUrl = archivoBase;
            try {
                Class.forName(driver1).newInstance();
                dbConnection = DriverManager.getConnection (strUrl);
                this.con=dbConnection;
                st=dbConnection.createStatement();
            } catch (InstantiationException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
                Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }

    @Override
    public Boolean guardarRegistro(String sql) {
        Boolean coneccion=true;
        try {
            System.out.println("SENTENCIA "+sql);
            st.executeUpdate(sql);
            //this.st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            coneccion=false;
            FileWriter fichero=null;
            PrintWriter pw=null;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - erroresDeConeccion.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sql);
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
            
        }
        return coneccion;
    }

    @Override
    public Boolean guardarRegistros(ArrayList listadoDeSentencias) {
        String sql="";
        Boolean coneccionCorrecta=true;
        Iterator il=listadoDeSentencias.listIterator();
        while(il.hasNext()){
            sql=(String)il.next();
            try {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
                coneccionCorrecta=false;
            }
        }
        
        return coneccionCorrecta;
    }

    @Override
    public ResultSet leerConjuntoDeRegistros(String sql) {
        ResultSet rs=null;
        try {
            st.execute(sql);
            rs=st.getResultSet();
        } catch (SQLException ex) {
            System.out.println("NO SE CONECTA, ACA CARGA LOS OBJETOS");
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO ENTRO LA CONECCION");
        }
        return rs;
    }
    public static void CrearDb(){
        Connection dbConnection = null;
        try {
            
                String strUrl = "jdbc:derby:C:\\Gestion\\DBG\\gestion.db;create=true";
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                     dbConnection = DriverManager.getConnection (strUrl,"","");
                     String sql="CREATE TABLE APP.inquilinos(id integer generated always as identity (start with 1,increment by 1), NOMBRE   varchar(49) default NULL,dni varchar(15) default null,telefono varchar(30) default null,domicilio varchar(50)default null,propiedad integer default 0,garante integer default 0,cuentacorriente integer default 0,mail varchar(100) default null,observaciones varchar(300) default null,fechaalta date default current_date)";
                     //st=dbConnection.createStatement();
                     PreparedStatement pstm=dbConnection.prepareStatement(sql);
                     pstm.execute();
                     pstm.close();
                     sql="CREATE TABLE APP.contratos (id integer generated always as identity (start with 1,increment by 1),fecha date default current_date,monto1 double default 1.00,fecha1 date default null,monto2 double default 1.00,fecha2 date default null,idinquilino integer default 0,idpropiedad integer default 0,idpropietario integer default 0,idgarante integer default 0,archivo varchar(200) default null,idusuario integer default 0)";
                     PreparedStatement pstm1=dbConnection.prepareStatement(sql);
                     pstm1.execute();
                     sql="create table app.conceptos (id integer generated always as identity (start with 1, increment by 1), descripcion varchar (50), monto double default 0.00)";
                     pstm1=dbConnection.prepareStatement(sql);
                     pstm1.execute();
                     sql="create table app.servidor (id integer generated always as identity (start with 1, increment by 1), host varchar(50),usuario varchar (50), clave varchar (50), puerto integer default 587, stats boolean default true, auth boolean default true)";
                     pstm1=dbConnection.prepareStatement(sql);
                     pstm1.execute();
                     pstm1.close();
                     sql="CREATE TABLE APP.caja (numero integer generated always as identity (start with 1,increment by 1),numeroSucursal  INTEGER not null,numeroUsuario  INTEGER not null,tipoMovimiento  INTEGER not null,saldoinicial double not null,estado INTEGER not null,tipo INTEGER not null,saldofinal double,fechacierre varchar(30),diferencia double)";
                     PreparedStatement pstm2=dbConnection.prepareStatement(sql);
                     pstm2.execute();
                     pstm2.close();
                     sql="CREATE TABLE APP.coeficienteslistas (id integer generated always as identity (start with 1,increment by 1),coeficiente  double not null,descripcion  varchar(30) not null,montocuota double default 0.00)";
                     PreparedStatement pstm3=dbConnection.prepareStatement(sql);
                     pstm3.execute();
                     pstm3.close();
                     sql="CREATE TABLE APP.comprobantes  (numero integer generated always as identity (start with 1,increment by 1),descripcion  varchar(4) not null,destinatarioCondicion  INTEGER not null,descargaStock  INTEGER not null)";
                     PreparedStatement pstm4=dbConnection.prepareStatement(sql);
                     pstm4.execute();
                     pstm4.close();
                     sql="CREATE TABLE APP.depositos (numero integer generated always as identity (start with 1,increment by 1),descripcion  varchar(100) not null,direccion  varchar(200) not null,telefono  varchar(200) not null)";
                     PreparedStatement pstm5=dbConnection.prepareStatement(sql);
                     pstm5.execute();
                     pstm5.close();
                     sql="CREATE TABLE APP.listcli  (COD_CLIENT  varchar(6),RAZON_SOCI  varchar(60),DOMICILIO  varchar(30),COND_VTA INTEGER not null,TELEFONO_1  varchar(30),LISTADEPRECIO INTEGER not null,NUMERODECUIT  varchar(30),CUPODECREDITO  double default 0.00,empresa  varchar(3),codMMd integer generated always as identity (start with 1,increment by 1),saldo double default 0.00,saldoactual double default 0.00 ,TIPO_IVA INTEGER not null,COEFICIENTE INTEGER default 1)";
                     PreparedStatement pstm6=dbConnection.prepareStatement(sql);
                     pstm6.execute();
                     pstm6.close();
                     sql="CREATE TABLE APP.proveedores (numero integer generated always as identity (start with 1,increment by 1),nombre  varchar(19) default NULL,DOMICILIO  varchar(100) default NULL,LOCALIDAD  varchar(8) default NULL,TELEFONO  varchar(10) default NULL,INHABILITADO  INTEGER default 0,mail  varchar(200) not null,saldo double default 0.00,idpropiedad integer default 0,idusuario integer default 0, fechaalta date default current_date,observaciones varchar(300) default null)";
                     PreparedStatement pstm7=dbConnection.prepareStatement(sql);
                     pstm7.execute();
                     pstm7.close();
                     sql="CREATE TABLE APP.sucursal (numero integer generated always as identity (start with 1,increment by 1),descripcion  varchar(100) not null,direccion  varchar(100) not null,telefono  varchar(100) not null,deposito  INTEGER not null)";
                     PreparedStatement pstm8=dbConnection.prepareStatement(sql);
                     pstm8.execute();
                     pstm8.close();
                     sql="CREATE TABLE APP.tipoacceso (numero integer generated always as identity (start with 1,increment by 1),descripcion  varchar(20) not null,nivel  INTEGER not null,menu1  INTEGER not null,menu2  INTEGER not null,menu3  INTEGER not null,menu4  INTEGER not null,menu5  INTEGER not null,menu6  INTEGER not null,menu7  INTEGER not null)";
                     PreparedStatement pstm9=dbConnection.prepareStatement(sql);
                     pstm9.execute();
                     pstm9.close();
                     sql="CREATE TABLE APP.tipocomprobantes  (numero integer generated always as identity (start with 1,increment by 1),descripcion  varchar(50) not null,numeroActivo  INTEGER not null,numeroSucursal  INTEGER not null)";
                     PreparedStatement pstm10=dbConnection.prepareStatement(sql);
                     pstm10.execute();
                     pstm10.close();
                     sql="CREATE TABLE APP.usuarios  (numero integer generated always as identity (start with 1,increment by 1),nombre  varchar(70) not null,direccion  varchar(200) not null,localidad  varchar(70) not null,telefono  varchar(100) not null,mail  varchar(100) not null,nombreUsuario  varchar(100) not null,clave  varchar(100) not null,autorizacion  INTEGER not null,numeroTipoAcceso  INTEGER not null,sucursal  INTEGER not null)";
                     PreparedStatement pstm11=dbConnection.prepareStatement(sql);
                     pstm11.execute();
                     pstm11.close();
                     sql="CREATE TABLE APP.fallas (st varchar(300) not null,estado INTEGER not null,id integer generated always as identity (start with 1,increment by 1))";
                     PreparedStatement pstm12=dbConnection.prepareStatement(sql);
                     pstm12.execute();
                     pstm12.close();
                     sql="CREATE TABLE APP.tipomovimientos(id integer generated always as identity (start with 1,increment by 1),DESCRIPCION varchar(30)not null,DESTINOOPERACION INTEGER not null,MULTIPLOOP INTEGER not null)";
                     PreparedStatement pstm13=dbConnection.prepareStatement(sql);
                     pstm13.execute();
                     pstm13.close();
                     sql="CREATE TABLE APP.movimientosarticulos (id integer generated always as identity (start with 1,increment by 1),tipoMovimiento INTEGER not null,idArticulo INTEGER not null,cantidad double not null,numeroDeposito INTEGER not null,tipoComprobante INTEGER not null,numeroComprobante INTEGER not null,numeroCliente INTEGER NOT NULL,fechaComprobante varchar(30) not null,numeroUsuario INTEGER NOT NULL,precioDeCosto double not null,precioDeVenta double not null,precioServicio double NOT NULL,estado INTEGER)";
                     PreparedStatement pstm14=dbConnection.prepareStatement(sql);
                     pstm14.execute();
                     pstm14.close();
                     sql="CREATE TABLE APP.movimientoscaja (id integer generated always as identity (start with 1,increment by 1),numeroUsuario INTEGER not null,idCliente INTEGER,numeroSucursal INTEGER NOT NULL,numeroComprobante INTEGER NOT NULL,tipoComprobante INTEGER NOT NULL,monto Double,tipoMovimiento INTEGER NOT NULL,idCaja INTEGER NOT NULL,cantidad double,pagado INTEGER NOT NULL,observaciones varchar(100),estado INTEGER,tipoCliente INTEGER)";
                     PreparedStatement pstm15=dbConnection.prepareStatement(sql);
                     pstm15.execute();
                     pstm15.close();
                     sql="CREATE TABLE APP.movimientosclientes (id integer generated always as identity (start with 1,increment by 1),numeroProveedor INTEGER NOT NULL,monto double not null,pagado INTEGER,numeroComprobante INTEGER,idRemito INTEGER,idUsuario INTEGER NOT NULL,idCaja INTEGER NOT NULL,fechaPago varchar(20),tipoComprobante INTEGER,idSucursal INTEGER NOT NULL,estado INTEGER)";
                     PreparedStatement pstm16=dbConnection.prepareStatement(sql);
                     pstm16.execute();
                    pstm16.close();
                    sql="CREATE TABLE APP.movimientosproveedores (id integer generated always as identity (start with 1,increment by 1),numeroProveedor INTEGER NOT NULL,monto double not null,pagado INTEGER,numeroComprobante varchar(30),idRemito INTEGER default 0,idUsuario INTEGER NOT NULL,idCaja INTEGER NOT NULL,fechaPago varchar(20),tipoComprobante INTEGER,idSucursal INTEGER default 1,estado INTEGER)";
                    PreparedStatement pstm17=dbConnection.prepareStatement(sql);
                    pstm17.execute();
                    pstm17.close();
                    sql="CREATE TABLE APP.movimientosusuarios (id integer generated always as identity (start with 1,increment by 1),numeroUsuario INTEGER NOT NULL,tipoAcceso INTEGER,entrada varchar(30) not null,estado INTEGER NOT NULL)";
                    PreparedStatement pstm18=dbConnection.prepareStatement(sql);
                    pstm18.execute();
                    pstm18.close();
                    sql="CREATE TABLE APP.movimientosdesucursales (id integer generated always as identity (start with 1,increment by 1),depOrigen INTEGER,depDestino INTEGER,idArticulo INTEGER not null,cantidad double not null,confirmado INTEGER,numeroRemito INTEGER,idUsuario INTEGER,diferencia double,idUsuarioRecep INTEGER not null)";
                    PreparedStatement pstm19=dbConnection.prepareStatement(sql);
                    pstm19.execute();
                    pstm19.close();
                    sql="insert into APP.usuarios (nombre,direccion,telefono,mail,nombreusuario,clave,autorizacion,numerotipoacceso,localidad,sucursal) values ('administrador','piedras 6738','155451500','contacto@maurodi.com.ar','MM','mm',1,1,'santa fe',1)";
                    PreparedStatement pstm20=dbConnection.prepareStatement(sql);
                    pstm20.execute();;
                    pstm20.close();
                    sql="insert into tipoacceso (descripcion,nivel,menu1,menu2,menu3,menu4,menu5,menu6,menu7) values ('administrador',1,1,1,1,1,1,1,1)";
                    PreparedStatement pstm21=dbConnection.prepareStatement(sql);
                    pstm21.execute();
                    pstm21.close();
                    sql="insert into coeficienteslistas (coeficiente,descripcion,montocuota) values (1,'PRECIO BASE',130.00)";
                    PreparedStatement pstm22=dbConnection.prepareStatement(sql);
                    pstm22.execute();
                    pstm22.close();
                    sql="insert into tipomovimientos (descripcion,destinooperacion,multiploop) values ('VENTAS',1,1),('COMPRAS',1,1),('DEVOLUCION DE VENTAS',1,-1),('RETIRO EFECTIVO',2,-1),('RECEPCION DE MERCADERIAS',1,1),('TRASLADO DE MERCADERIAS',1,1),('INGRESO DE CAJA',2,1),('DEVOLUCION DE COMPRAS',1,1),('SALDO INICIAL',2,1),('CERRAR CAJA',2,-1),('PAGO A PROVEEDORES',2,-1),('GASTOS DE CAJA',2,-1),('COBRO DE CTA CTE CLIENTE',2,1),('AJUSTE DE STOCK',1,1),('AJUSTE DE SALDO',1,1)";
                    PreparedStatement pstm23=dbConnection.prepareStatement(sql);
                    pstm23.execute();
                    pstm23.close();
                    sql="CREATE TABLE APP.movimientosgastosfijos (id integer generated always as identity (start with 1,increment by 1),idProveedor integer not null,monto double not null,fechaVencimiento varchar(20),numeroFactura varchar(20),pagado integer default 0,fecha date default current_date,idCaja integer not null,fechapago date)";
                    PreparedStatement pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.propiedades (id integer generated always as identity (start with 1,increment by 1),direccion varchar(200),localidad varchar(100),rubro integer,idcontrato integer default 0,idpropietario integer,idcuentascorriente integer default 0,idusuario integer,fecha date default current_date)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.cuentascorrientes (id integer generated always as identity (start with 1,increment by 1),idinquilino integer,idresumen integer default 0,monto double default 0.00,idrecibo integer default 0,fechaemision date default current_date,tipomovimiento integer default 0,idusuario integer)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.cuentas (id integer generated always as identity (start with 1,increment by 1),descripcion varchar(50),monto double default 0.00,idedificio integer default 0,idpropiedad integer default 0,fechaalta date default current_date,estado integer default 0,idusuario integer)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.recibos (id integer generated always as identity (start with 1,increment by 1),numero integer default 0,idinquilino integer default 0,idcontrato integer default 0,monto double default 0.00,fecha date default current_date,idusuario integer)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.facturas (id integer generated always as identity (start with 1,increment by 1),numero integer default 0,idpropietario integer default 0,idcontrato integer default 0,monto double,montocomision double,fecha date default current_date, idusuario integer)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.resumenes (id integer generated always as identity (start with 1,increment by 1),idpropiedad integer,numero integer,idgasto integer default 0,descripcion varchar(100) default null,idconcepto integer default 0,montototal double,fecha date default current_date,idusuario integer,fechavencimiento date,estado integer default 0)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.garantes (id integer generated always as identity (start with 1,increment by 1),nombre varchar(100),dni varchar(15),telefono varchar(30) default null,domicilio varchar(50) default null,localidad integer,idpropiedad integer,idcontrato integer,idcuentacorriente integer,mail varchar(100) default null,observaciones varchar(300) default null,fechaalta date default current_date,idusuario integer,iddatos integer default 0)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.operaciones(id integer generated always as identity (start with 1,increment by 1),fecha date default current_date,monto double,contrato integer default 0,idpropietario integer,idfactura integer default 0,idusuario integer,estado integer,idrecibo integer default 0)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();

                    sql="CREATE TABLE APP.comisiones (id integer generated always as identity (start with 1,increment by 1),descripcion varchar(50),porcentaje double)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.localidad (id integer generated always as identity (start with 1,increment by 1),descripcion varchar(50),codigo integer default 0)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.rubro (id integer generated always as identity (start with 1,increment by 1),descripcion varchar(50))";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.edificio (id integer generated always as identity (start with 1,increment by 1),descripcion varchar(50),direccion varchar(100))";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();
                    sql="CREATE TABLE APP.datos (id integer generated always as identity (start with 1,increment by 1),direccion varchar(100),idresponsable integer,tiporesponsable integer)";
                    pstm24=dbConnection.prepareStatement(sql);
                    pstm24.execute();
                    pstm24.close();

                    sql="insert into tipocomprobantes (descripcion,numeroactivo,numerosucursal) values ('ticket',1,1),('fca a',1,1),('factura proveedor A',1,1),('movimiento de caja',1,1),('recibo de pago',1,1),('gasto fijo',1,1),('remito de ajuste de stock',1,1),('movimiento de ajuste de saldo',1,1)";
                    PreparedStatement pstm25=dbConnection.prepareStatement(sql);
                    pstm25.execute();
                    //pstm25.close();
                    sql="insert into listcli (cod_client,razon_soci,domicilio,cond_vta,telefono_1,listadeprecio,numerodecuit,cupodecredito,empresa,tipo_iva,coeficiente) values ('999999','CONSUMIDOR FINAL','NO ASIGNADO',1,'NO ASIGNADO',1,'1',0.00,'SD',1,1)";
                    pstm25=dbConnection.prepareStatement(sql);
                    pstm25.execute();
                    pstm25.close();
                    JOptionPane.showMessageDialog(null,"BASE DE DATOS CORRECTAMENTE CREADA");
                    /*
                    Articulos.RecargarMap();
                    Proveedores.cargarListadoProv();
                    ClientesTango.cargarMap();
                    ListasDePrecios.cargarMap();
                    */ 
                    
                    /*
                    ESTE BLOQUE CONECTA CON LA WEB
                    * 
                    Articulos.BackapearMap();
                    Proveedores.BackapearProveedores();
                    ClientesTango.BackapearClientes();
                    ListasDePrecios.BackapearListasDePrecios();
                    Cajas.BackapearCajas();
                    Cajas.LeerCajaAdministradora();
                    Usuarios.BackapearUsuarios();
                    
                    * 
                    * 
                    */
                    //Sucursales.BackapearSucursales();
                    //Depositos.BackapearDepositos();
                    
                    Articulos.RecargarMap();
                    Proveedores.cargarListadoProv();
                    ClientesTango.cargarMap();
                    ListasDePrecios.cargarMap();
                    
                     
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeccionLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return dbConnection;
    }
}
