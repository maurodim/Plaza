/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

/**
 *
 * @author mauro di
 */
public class Encabezado {
    private String textoSuperior;
    private String fecha;
    private String nombre;
    private String idComprobante;
    private String direccion;
    private String localidad;
    private String numeroDeCuit;
    private String telefono;
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    

    public void setTextoSuperior(String textoSuperior) {
        this.textoSuperior = textoSuperior;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setNumeroDeCuit(String numeroDeCuit) {
        this.numeroDeCuit = numeroDeCuit;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
    
    public String getTextoSuperior() {
        return textoSuperior;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getNumeroDeCuit() {
        return numeroDeCuit;
    }

    public String getTelefono() {
        return telefono;
    }
    
    
    
}
