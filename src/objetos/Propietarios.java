/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Contratable;
import interfaces.Generable;
import interfaces.Listables;
import interfaces.Personalizable;
import interfaces.Propietables;
import interfaces.Transaccionable;
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
public class Propietarios implements Generable,Componable,Contratable,Propietables{
    private Integer id;
    private String nombre;
    private String dni;
    private String telefono;
    private String domicilio;
    private Integer propiedad;
    private String mail;
    private String observaciones;
    private Date fechaAlta;
    private Integer usuario;
    private Double saldo;
    private String localidad;
    private ArrayList propiedadesP;
    private String cuit;
    private String condIva;

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCondIva() {
        return condIva;
    }

    public void setCondIva(String condIva) {
        this.condIva = condIva;
    }
    

    public ArrayList getPropiedadesP() {
        return propiedadesP;
    }

    public void setPropiedadesP(ArrayList propiedadesP) {
        this.propiedadesP = propiedadesP;
    }

    
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    

    public Propietarios() {
        //propiedad=new Propiedades();
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

    public Integer getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Integer propiedad) {
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

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
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
        String sql="insert into proveedores (nombre,domicilio,localidad,telefono,mail,idusuario,observaciones,cuit,iva) values ('"+propietario.getNombre()+"','"+propietario.getDomicilio()+"','"+propietario.getLocalidad()+"','"+propietario.getTelefono()+"','"+propietario.getMail()+"',"+propietario.getUsuario()+",'"+propietario.getObservaciones()+"','"+propietario.getCuit()+"','"+propietario.getCondIva()+"')";
        System.out.println(sql);
        tra.guardarRegistro(sql);
        sql="select numero from proveedores ";
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
    private Integer leerIdInsertado(){
        String sql="select numero from proveedores order by numero desc limit 0,1";
        return 0;
    }
    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from proveedores where numero="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Propietarios propietario=new Propietarios();
        propietario=(Propietarios)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update proveedores set nombre='"+propietario.getNombre()+"',cuit='"+propietario.getCuit()+"',domicilio='"+propietario.getDomicilio()+"',localidad='"+propietario.getLocalidad()+"',telefono='"+propietario.getTelefono()+"',mail='"+propietario.getMail()+"',observaciones='"+propietario.getObservaciones()+"',saldo="+propietario.getSaldo()+",dni='"+propietario.getDni()+"' where numero="+propietario.getId();        
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
                propietario.setPropiedad(rs.getInt("idpropiedad"));
                propietario.setUsuario(rs.getInt("idusuario"));
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                propietario.setCuit(rs.getString("cuit"));
                propietario.setCondIva(rs.getString("iva"));
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
        Listables lista=new Propiedades();
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
                if(rs.getInt("idpropiedad") > 0)propietario.setPropiedad(rs.getInt("idpropiedad"));
                if(rs.getInt("idusuario") > 0)propietario.setUsuario(rs.getInt("idusuario"));
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                //listado.add(propietario);
                propietario.setCuit(rs.getString("cuit"));
                propietario.setCondIva(rs.getString("iva"));
                propietario.setDni(rs.getString("dni"));
            }
            rs.close();
            try{
            if(propietario.getPropiedad() > 0)propietario.setPropiedadesP(lista.listarPorId(propietario.getId()));
            }catch(java.lang.NullPointerException e){
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propietario;
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        DefaultListModel modelo=new DefaultListModel();
        Transaccionable tra=new ConeccionLocal();
         Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        
        String sql="select * from proveedores order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                
                modelo.addElement(rs.getString("nombre"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modelo;
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        MiModeloTablaListado modelo=new MiModeloTablaListado();
        Transaccionable tra=new ConeccionLocal();
         Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        modelo.addColumn("numero");
        modelo.addColumn("nombre");
        modelo.addColumn("domicilio");
        modelo.addColumn("telefono");
        modelo.addColumn("mail");
        modelo.addColumn("saldo");
        Object[] fila=new Object[6];
        String sql="select * from proveedores order by nombre";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                fila[0]=rs.getInt("numero");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getString("domicilio");
                fila[3]=rs.getString("telefono");
                fila[4]=rs.getString("mail");
                fila[5]=rs.getDouble("saldo");
                modelo.addRow(fila);
            }
            rs.close();
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
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarDesdeContratos(Integer id) {
        Generable prop=new Propiedades();
        Listables lista=new Propiedades();
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
                if(rs.getInt("idusuario") > 0)propietario.setUsuario(rs.getInt("idusuario"));
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                //listado.add(propietario);
                propietario.setCuit(rs.getString("cuit"));
                propietario.setCondIva(rs.getString("iva"));
                propietario.setDni(rs.getString("dni"));
            }
            rs.close();
            try{
            if(propietario.getPropiedad() > 0)propietario.setPropiedadesP(lista.listarPorId(propietario.getId()));
            }catch(java.lang.NullPointerException e){
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propietario;
    }

    @Override
    public Object cargarPorIdPropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorIdPropiedadSolo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorIdPropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarDesdePropiedad(Integer id) {
        Generable prop=new Propiedades();
        Listables lista=new Propiedades();
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
                
                propietario.setFechaAlta(rs.getDate("fechaalta"));
                propietario.setObservaciones(rs.getString("observaciones"));
                //listado.add(propietario);
                propietario.setCuit(rs.getString("cuit"));
                propietario.setCondIva(rs.getString("iva"));
                propietario.setDni(rs.getString("dni"));
            }
            rs.close();
            try{
            if(propietario.getPropiedad() > 0)propietario.setPropiedadesP(lista.listarPorId(propietario.getId()));
            }catch(java.lang.NullPointerException e){
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propietario;
    }

    @Override
    public Object CargarDesdePropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
