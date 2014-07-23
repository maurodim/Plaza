/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import interfaces.Listables;
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
public class Propiedades implements Generable,Listables{
    private Integer id;
    private String direccion;
    private String localidad;
    private Rubro rubro;
    private Contratos contrato;
    private Propietarios propietario;
    private CuentaCorriente cuentaCorriente;
    private Usuarios usuario;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public Propiedades() {
        //localidad=new Localidad();
        //rubro=new Rubro();
        //contrato=new Contratos();
        //propietario=new Propietarios();
        //cuentaCorriente=new CuentaCorriente();
        //usuario=new Usuarios();
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public Contratos getContrato() {
        return contrato;
    }

    public void setContrato(Contratos contrato) {
        this.contrato = contrato;
    }

    public Propietarios getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietarios propietario) {
        this.propietario = propietario;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Alta(Object objeto) {
        Propiedades propiedad=new Propiedades();
        propiedad=(Propiedades)objeto;
        //Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into propiedades (direccion,localidad,rubro,idusuario) values ('"+propiedad.getDireccion()+"','"+propiedad.getLocalidad()+"',"+propiedad.getRubro().getId()+","+propiedad.getUsuario().getNumeroId()+")";
        tra.guardarRegistro(sql);
        sql="select id from propiedades";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                propiedad.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Baja(Integer id) {
       Transaccionable tra=new ConeccionLocal();
       String sql="delete from propiedades where id="+id;
       tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Propiedades propiedad=new Propiedades();
        propiedad=(Propiedades)objeto;
        //Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        String sql="update propiedades set direccion='"+propiedad.getDireccion()+"',localidad='"+propiedad.getLocalidad()+"',rubro="+propiedad.getRubro().getId()+",idpropietario="+propiedad.getPropietario().getId()+",idcontrato="+propiedad.getContrato().getId()+",idcuentascorriente="+propiedad.getCuentaCorriente().getId()+" where id="+propiedad.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        String sql="select * from propieadades order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Propiedades propiedad=new Propiedades();
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                propiedad.setContrato((Contratos)cont.Cargar(rs.getInt("idcontrato")));
                propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                listado.add(propiedad);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        //ArrayList listado=new ArrayList();
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                propiedad.setContrato((Contratos)cont.Cargar(rs.getInt("idcontrato")));
                propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                //listado.add(propiedad);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propiedad;
    }

    @Override
    public ArrayList listarPorId(Integer id) {
        ArrayList listado=new ArrayList();
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where idpropietario="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                if(rs.getInt("idcontrato") > 0)propiedad.setContrato((Contratos)cont.Cargar(rs.getInt("idcontrato")));
                //propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                if(rs.getInt("idcuentascorriente") > 0)propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                listado.add(propiedad);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
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
        ArrayList listado=new ArrayList();
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where idcontrato=0";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                if(rs.getInt("idcontrato") > 0)propiedad.setContrato((Contratos)cont.Cargar(rs.getInt("idcontrato")));
                //propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                if(rs.getInt("idcuentascorriente") > 0)propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                listado.add(propiedad);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    
    
}
