/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Generable;
import interfaces.Listables;
import interfaces.Personalizable;
import interfaces.Saldable;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaListado;

/**
 *
 * @author Usuario
 */
public class Inquilinos implements Generable,Listables,Componable,Saldable{
   private Integer id;
   private String nombre;
   private String dni;
   private String telefono;
   private String domicilioRef;
   private Propiedades propiedad;
   private Garantes garantes;
   private CuentaCorriente cuentaCorriente;
   private String mail;
   private String observaciones;
   private Date fechaAlta;
   private Usuarios usuario;
   private ArrayList arrayDatos;
   private String cuit;
   private Integer condicionIva;
   

    public Inquilinos() {
        //propiedad=new Propiedades();
        //garantes=new Garantes();
        //cuentaCorriente=new CuentaCorriente();
        
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(Integer condicionIva) {
        this.condicionIva = condicionIva;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilioRef() {
        return domicilioRef;
    }

    public void setDomicilioRef(String domicilioRef) {
        this.domicilioRef = domicilioRef;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public Garantes getGarantes() {
        return garantes;
    }

    public void setGarantes(Garantes garantes) {
        this.garantes = garantes;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public ArrayList getArrayDatos() {
        return arrayDatos;
    }

    public void setArrayDatos(ArrayList arrayDatos) {
        this.arrayDatos = arrayDatos;
    }
    
    @Override
    public void Alta(Object objeto) {
        Inquilinos inquilino=new Inquilinos();
        inquilino=(Inquilinos)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into inquilinos (nombre,dni,telefono,domicilio,mail,observaciones) values ('"+inquilino.getNombre()+"','"+inquilino.getDni()+"','"+inquilino.getTelefono()+"','"+inquilino.getDomicilioRef()+"','"+inquilino.getMail()+"','"+inquilino.getObservaciones()+"')";
        tra.guardarRegistro(sql);
        
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from inquilinos where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Inquilinos inquilino=new Inquilinos();
        inquilino=(Inquilinos)objeto;
        Transaccionable tra=new ConeccionLocal();
        try{
        String sql="update inquilinos set nombre='"+inquilino.getNombre()+"',dni='"+inquilino.getDni()+"',domicilio='"+inquilino.getDomicilioRef()+"',mail='"+inquilino.getMail()+"',telefono='"+inquilino.getTelefono()+"',observaciones='"+inquilino.getObservaciones()+"',propiedad="+inquilino.getPropiedad().getId()+" where id="+inquilino.getId();
        System.out.println(sql);
        tra.guardarRegistro(sql);
        }catch(java.lang.NullPointerException ex){
            String sql="update inquilinos set nombre='"+inquilino.getNombre()+"',dni='"+inquilino.getDni()+"',domicilio='"+inquilino.getDomicilioRef()+"',mail='"+inquilino.getMail()+"',telefono='"+inquilino.getTelefono()+"',observaciones='"+inquilino.getObservaciones()+"' where id="+inquilino.getId();
        System.out.println(sql);
        tra.guardarRegistro(sql);
        }
    }

    @Override
    public ArrayList Listar() {
        Transaccionable tra=new ConeccionLocal();
        Generable propi=new Propiedades();
        Generable gar=new Garantes();
        Generable cta=new CuentaCorriente();
        ArrayList listado=new ArrayList();
        String sql="select * from inquilinos order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       try {
           while(rs.next()){
               Inquilinos inquilino=new Inquilinos();
               inquilino.setId(rs.getInt("id"));
               inquilino.setNombre(rs.getString("nombre"));
               inquilino.setDni(rs.getString("dni"));
               inquilino.setTelefono(rs.getString("telefono"));
               inquilino.setDomicilioRef(rs.getString("domicilio"));
               inquilino.setMail(rs.getString("mail"));
               inquilino.setObservaciones(rs.getString("observaciones"));
               inquilino.setFechaAlta(rs.getDate("fechaalta"));
               //inquilino.setCuit(rs.getString("cuit"));
               //inquilino.setCondicionIva(rs.getInt("cIva"));
               if(rs.getInt("propiedad") > 0)inquilino.setPropiedad((Propiedades) propi.Cargar(rs.getInt("propiedad")));
               if(rs.getInt("garante") > 0)inquilino.setGarantes((Garantes) gar.Cargar(rs.getInt("garante")));
               if(rs.getInt("cuentacorriente") > 0)inquilino.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("cuentacorriente")));
               listado.add(inquilino);
           }
           rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(Inquilinos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return listado;
    }

    @Override
    public Object Cargar(Integer id) {
    Transaccionable tra=new ConeccionLocal();
        Generable propi=new Propiedades();
        Generable gar=new Garantes();
        Generable cta=new CuentaCorriente();
        ArrayList listado=new ArrayList();
        Inquilinos inquilino=new Inquilinos();
        String sql="select * from inquilinos where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       try {
           while(rs.next()){
               
               inquilino.setId(rs.getInt("id"));
               inquilino.setNombre(rs.getString("nombre"));
               inquilino.setDni(rs.getString("dni"));
               inquilino.setTelefono(rs.getString("telefono"));
               inquilino.setDomicilioRef(rs.getString("domicilio"));
               inquilino.setMail(rs.getString("mail"));
               inquilino.setObservaciones(rs.getString("observaciones"));
               inquilino.setFechaAlta(rs.getDate("fechaalta"));
               if(rs.getInt("propiedad") > 0)inquilino.setPropiedad((Propiedades) propi.Cargar(rs.getInt("propiedad")));
               if(rs.getInt("garante") > 0)inquilino.setGarantes((Garantes) gar.Cargar(rs.getInt("garante")));
               if(rs.getInt("cuentacorriente") > 0)inquilino.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("cuentacorriente")));
               //listado.add(inquilino);
           }
           rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(Inquilinos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return inquilino;    
    }
   
    public Boolean NotificarPorMail(){
        
        return false;
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
        Transaccionable tra=new ConeccionLocal();
        Generable propi=new Propiedades();
        Generable gar=new Garantes();
        Generable cta=new CuentaCorriente();
        ArrayList listado=new ArrayList();
        String sql="select * from inquilinos where propiedad=0 order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       try {
           while(rs.next()){
               Inquilinos inquilino=new Inquilinos();
               inquilino.setId(rs.getInt("id"));
               inquilino.setNombre(rs.getString("nombre"));
               inquilino.setDni(rs.getString("dni"));
               inquilino.setTelefono(rs.getString("telefono"));
               inquilino.setDomicilioRef(rs.getString("domicilio"));
               inquilino.setMail(rs.getString("mail"));
               inquilino.setObservaciones(rs.getString("observaciones"));
               inquilino.setFechaAlta(rs.getDate("fechaalta"));
               if(rs.getInt("propiedad") > 0)inquilino.setPropiedad((Propiedades) propi.Cargar(rs.getInt("propiedad")));
               if(rs.getInt("garante") > 0)inquilino.setGarantes((Garantes) gar.Cargar(rs.getInt("garante")));
               if(rs.getInt("cuentacorriente") > 0)inquilino.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("cuentacorriente")));
               listado.add(inquilino);
           }
           rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(Inquilinos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return listado;
    }

    @Override
    public ArrayList listarPorPropietario(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        MiModeloTablaListado modelo=new MiModeloTablaListado();
        Transaccionable tra=new ConeccionLocal();
         Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        modelo.addColumn("numero");
        modelo.addColumn("nombre");
        modelo.addColumn("dni");
        modelo.addColumn("telefono");
        modelo.addColumn("mail");
        modelo.addColumn("Propiedad");
        modelo.addColumn("saldo");
        Object[] fila=new Object[7];
        String sql="select id,nombre,dni,telefono,mail,(select propiedades.direccion from propiedades where propiedades.id=inquilinos.propiedad)as prop from inquilinos order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                fila[0]=rs.getInt("id");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("dni");
                fila[3]=rs.getString("telefono");
                fila[4]=rs.getString("mail");
                fila[5]=rs.getString("prop");
                fila[6]="aca calculo de cta cte";
                modelo.addRow(fila);
            }
            rs.close();
            /*
             Generable propi=new Propiedades();
        Generable gar=new Garantes();
        Generable cta=new CuentaCorriente();
        ArrayList listado=new ArrayList();
        String sql="select * from inquilinos order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       try {
           while(rs.next()){
               Inquilinos inquilino=new Inquilinos();
               inquilino.setId(rs.getInt("id"));
               inquilino.setNombre(rs.getString("nombre"));
               inquilino.setDni(rs.getString("dni"));
               inquilino.setTelefono(rs.getString("telefono"));
               inquilino.setDomicilioRef(rs.getString("domicilio"));
               inquilino.setMail(rs.getString("mail"));
               inquilino.setObservaciones(rs.getString("observaciones"));
               inquilino.setFechaAlta(rs.getDate("fechaalta"));
               if(rs.getInt("propiedad") > 0)inquilino.setPropiedad((Propiedades) propi.Cargar(rs.getInt("propiedad")));
               if(rs.getInt("garante") > 0)inquilino.setGarantes((Garantes) gar.Cargar(rs.getInt("garante")));
               if(rs.getInt("cuentacorriente") > 0)inquilino.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("cuentacorriente")));
               listado.add(inquilino);
           }
           rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(Inquilinos.class.getName()).log(Level.SEVERE, null, ex);
       }
            */
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
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
    public Double calcularSaldoActual(Integer id) {
        // ACA TENGO QUE VER COMO HAGO CON LO PENDIENTE
        Transaccionable tt=new ConeccionLocal();
        Double saldo=0.00;
        String sql="select * from resumenid where idinquilino="+id+" and estado =0 and fecha > '"+Inicio.fechaDia+"'";
        ResultSet rs=tt.leerConjuntoDeRegistros(sql);
       try {
           while(rs.next()){
               saldo=rs.getDouble("monto");
           }
           rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(Inquilinos.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return saldo;
    }

    @Override
    public Boolean ajustarSaldo(Integer id, Double movimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
