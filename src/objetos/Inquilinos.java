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
   
    public Boolean NotificarPorMail(){
        
        return false;
    }
   
}
