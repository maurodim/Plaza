/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro di
 */
public class Configuracion {
    private String nombre;
    private Double recargo;
    private Double comision;
    private String direccion;
    private String telefono;
    private String cuit;
    private String logo;
    private String carpetasInformes;
    private String carpetasDocumentos;
    private String localidad;
    private String nota1;
    private String nota2;
    private String nota3;

    public Configuracion() {
        Transaccionable tra=new ConeccionLocal();
        String sql="select * from configuracion";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                //Configuracion configuracion=new Configuracion();
                this.nombre=rs.getString("nombre");
                this.recargo=rs.getDouble("recargo");
                this.comision=rs.getDouble("comision");
                this.direccion=rs.getString("direccion");
                this.telefono=rs.getString("telefono");
                this.cuit=rs.getString("cuit");
                this.logo=rs.getString("logo");
                this.carpetasInformes=rs.getString("carpetasinformes");
                this.carpetasDocumentos=rs.getString("carpetasdocumentos");
                this.localidad=rs.getString("localidad");
                this.nota1=rs.getString("nota1");
                this.nota2=rs.getString("nota2");
                this.nota3=rs.getString("nota3");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCarpetasInformes() {
        return carpetasInformes;
    }

    public void setCarpetasInformes(String carpetasInformes) {
        this.carpetasInformes = carpetasInformes;
    }

    public String getCarpetasDocumentos() {
        return carpetasDocumentos;
    }

    public void setCarpetasDocumentos(String carpetasDocumentos) {
        this.carpetasDocumentos = carpetasDocumentos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }
    
    
}
