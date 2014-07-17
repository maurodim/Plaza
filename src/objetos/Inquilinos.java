/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
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
public class Inquilinos implements Generable{
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

    public Inquilinos() {
        propiedad=new Propiedades();
        garantes=new Garantes();
        cuentaCorriente=new CuentaCorriente();
        
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
        String sql="insert into inquilinos (nombre,dni,telefono,domicilio,mail,observaciones) values ('"+inquilino.getNombre()+"','"+inquilino.getDni()+"','"+inquilino.getDomicilioRef()+"','"+inquilino.getMail()+"','"+inquilino.getObservaciones()+"')";
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
        String sql="update inquilinos set nombre='"+inquilino.getNombre()+"',dni='"+inquilino.getDni()+"',domicilio='"+inquilino.getDomicilioRef()+"',mail='"+inquilino.getMail()+"',observaciones='"+inquilino.getObservaciones()+"',propiedad="+inquilino.getPropiedad().getId()+",garante="+inquilino.getGarantes().getId()+",cuentacorriente="+inquilino.getCuentaCorriente().getId()+" where id="+inquilino.getId();
        tra.guardarRegistro(sql);
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
   
}
