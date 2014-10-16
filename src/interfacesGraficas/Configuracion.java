/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author mauro di
 */
@Entity
@Table(name = "CONFIGURACION", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c"),
    @NamedQuery(name = "Configuracion.findByNombre", query = "SELECT c FROM Configuracion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Configuracion.findByRecargo", query = "SELECT c FROM Configuracion c WHERE c.recargo = :recargo"),
    @NamedQuery(name = "Configuracion.findByComision", query = "SELECT c FROM Configuracion c WHERE c.comision = :comision"),
    @NamedQuery(name = "Configuracion.findByDireccion", query = "SELECT c FROM Configuracion c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Configuracion.findByTelefono", query = "SELECT c FROM Configuracion c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Configuracion.findByCuit", query = "SELECT c FROM Configuracion c WHERE c.cuit = :cuit"),
    @NamedQuery(name = "Configuracion.findByLogo", query = "SELECT c FROM Configuracion c WHERE c.logo = :logo"),
    @NamedQuery(name = "Configuracion.findByCarpetasinformes", query = "SELECT c FROM Configuracion c WHERE c.carpetasinformes = :carpetasinformes"),
    @NamedQuery(name = "Configuracion.findByCarpetasdocumentos", query = "SELECT c FROM Configuracion c WHERE c.carpetasdocumentos = :carpetasdocumentos"),
    @NamedQuery(name = "Configuracion.findByLocalidad", query = "SELECT c FROM Configuracion c WHERE c.localidad = :localidad"),
    @NamedQuery(name = "Configuracion.findByNota1", query = "SELECT c FROM Configuracion c WHERE c.nota1 = :nota1"),
    @NamedQuery(name = "Configuracion.findByNota2", query = "SELECT c FROM Configuracion c WHERE c.nota2 = :nota2"),
    @NamedQuery(name = "Configuracion.findByNota3", query = "SELECT c FROM Configuracion c WHERE c.nota3 = :nota3"),
    @NamedQuery(name = "Configuracion.findById", query = "SELECT c FROM Configuracion c WHERE c.id = :id")})
public class Configuracion implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Column(name = "NOMBRE", length = 50)
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RECARGO", precision = 52)
    private Double recargo;
    @Column(name = "COMISION", precision = 52)
    private Double comision;
    @Column(name = "DIRECCION", length = 100)
    private String direccion;
    @Column(name = "TELEFONO", length = 50)
    private String telefono;
    @Column(name = "CUIT", length = 50)
    private String cuit;
    @Column(name = "LOGO", length = 100)
    private String logo;
    @Column(name = "CARPETASINFORMES", length = 100)
    private String carpetasinformes;
    @Column(name = "CARPETASDOCUMENTOS", length = 100)
    private String carpetasdocumentos;
    @Column(name = "LOCALIDAD", length = 50)
    private String localidad;
    @Column(name = "NOTA1", length = 300)
    private String nota1;
    @Column(name = "NOTA2", length = 300)
    private String nota2;
    @Column(name = "NOTA3", length = 300)
    private String nota3;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;

    public Configuracion() {
    }

    public Configuracion(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        Double oldRecargo = this.recargo;
        this.recargo = recargo;
        changeSupport.firePropertyChange("recargo", oldRecargo, recargo);
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        Double oldComision = this.comision;
        this.comision = comision;
        changeSupport.firePropertyChange("comision", oldComision, comision);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        changeSupport.firePropertyChange("direccion", oldDireccion, direccion);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        String oldTelefono = this.telefono;
        this.telefono = telefono;
        changeSupport.firePropertyChange("telefono", oldTelefono, telefono);
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        String oldCuit = this.cuit;
        this.cuit = cuit;
        changeSupport.firePropertyChange("cuit", oldCuit, cuit);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        String oldLogo = this.logo;
        this.logo = logo;
        changeSupport.firePropertyChange("logo", oldLogo, logo);
    }

    public String getCarpetasinformes() {
        return carpetasinformes;
    }

    public void setCarpetasinformes(String carpetasinformes) {
        String oldCarpetasinformes = this.carpetasinformes;
        this.carpetasinformes = carpetasinformes;
        changeSupport.firePropertyChange("carpetasinformes", oldCarpetasinformes, carpetasinformes);
    }

    public String getCarpetasdocumentos() {
        return carpetasdocumentos;
    }

    public void setCarpetasdocumentos(String carpetasdocumentos) {
        String oldCarpetasdocumentos = this.carpetasdocumentos;
        this.carpetasdocumentos = carpetasdocumentos;
        changeSupport.firePropertyChange("carpetasdocumentos", oldCarpetasdocumentos, carpetasdocumentos);
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        String oldLocalidad = this.localidad;
        this.localidad = localidad;
        changeSupport.firePropertyChange("localidad", oldLocalidad, localidad);
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        String oldNota1 = this.nota1;
        this.nota1 = nota1;
        changeSupport.firePropertyChange("nota1", oldNota1, nota1);
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        String oldNota2 = this.nota2;
        this.nota2 = nota2;
        changeSupport.firePropertyChange("nota2", oldNota2, nota2);
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        String oldNota3 = this.nota3;
        this.nota3 = nota3;
        changeSupport.firePropertyChange("nota3", oldNota3, nota3);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "interfacesGraficas.Configuracion[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
