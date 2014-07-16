/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import interfaces.Personalizable;
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
public class Propietarios implements Generable{
    private Integer id;
    private String nombre;
    private String dni;
    private String telefono;
    private String domicilio;
    private Propiedades propiedad;
    private String mail;
    private String observaciones;
    private Date fechaAlta;
    private Usuarios usuario;
    private Double saldo;
    private String localidad;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    

    public Propietarios() {
        propiedad=new Propiedades();
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public void Alta(Object objeto) {
        Propietarios propietario=new Propietarios();
        propietario=(Propietarios)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into proveedores (nombre,domicilio,localidad,telefono,mail,idusuario,observaciones) values ('"+propietario.getNombre()+"','"+propietario.getDomicilio()+"','"+propietario.getLocalidad()+"','"+propietario.getTelefono()+"','"+propietario.getMail()+"',"+propietario.getUsuario().getNumeroId()+",'"+propietario.getObservaciones()+"')";
        tra.guardarRegistro(sql);
        sql="last_inset_id()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                propietario.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete proveedores where numero="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Propietarios propietario=new Propietarios();
        propietario=(Propietarios)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update proveedores set nombre='"+propietario.getNombre()+"',domicilio='"+propietario.getDomicilio()+"',localidad='"+propietario.getLocalidad()+"',telefono='"+propietario.getTelefono()+"',mail='"+propietario.getMail()+"',observaciones='"+propietario.getObservaciones()+"',saldo="+propietario.getSaldo()+",idpropiedad="+propietario.getPropiedad().getId()+" where numero="+propietario.getId();        
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from proveedores order by nombre";
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Propietarios propietario=new Propietarios();
                propietario.setId(rs.getInt("numero"));
                propietario.setNombre(rs.getString("nombre"));
                propietario.setDomicilio(rs.getString("domicilio"));
                propietario.setLocalidad(rs.getString("localidad"));
                propietario.setTelefono(rs.getString("telefono"));
                propietario.setMail(rs.getString("mail"));
                propietario.setSaldo(rs.getDouble("saldo"));
                propietario.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                propietario.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                listado.add(propietario);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        //ArrayList listado=new ArrayList();
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from proveedores where numero="+id;
        Transaccionable tra=new ConeccionLocal();
        Propietarios propietario=new Propietarios();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propietario.setId(rs.getInt("numero"));
                propietario.setNombre(rs.getString("nombre"));
                propietario.setDomicilio(rs.getString("domicilio"));
                propietario.setLocalidad(rs.getString("localidad"));
                propietario.setTelefono(rs.getString("telefono"));
                propietario.setMail(rs.getString("mail"));
                propietario.setSaldo(rs.getDouble("saldo"));
                propietario.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                propietario.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                //listado.add(propietario);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propietario;
    }
    
    
}
