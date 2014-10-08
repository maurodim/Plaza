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
import interfaces.Resumible;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaListado;

/**
 *
 * @author Usuario
 */
public class Propiedades implements Generable,Listables,Componable,Contratable,Resumible{
    private Integer id;
    private String direccion;
    private String localidad;
    private Rubro rubro;
    private Contratos contrato;
    private Propietarios propietario;
    private CuentaCorriente cuentaCorriente;
    private Usuarios usuario;
    private Date fecha;
    private Integer idResumen;
    private Integer estadoResumen;

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }

    public Integer getEstadoResumen() {
        return estadoResumen;
    }

    public void setEstadoResumen(Integer estadoResumen) {
        this.estadoResumen = estadoResumen;
    }
    

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
        String sql="update propiedades set direccion='"+propiedad.getDireccion()+"',localidad='"+propiedad.getLocalidad()+"',rubro="+propiedad.getRubro().getId()+",idpropietario="+propiedad.getPropietario().getId()+" where id="+propiedad.getId();
        System.out.println(sql);
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        Propietables ppR=new Resumenes();
        Resumenes res=new Resumenes();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        String sql="select * from propiedades order by id";
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
                res=(Resumenes) ppR.cargarPorIdPropiedadSolo(propiedad.getId());
                propiedad.setIdResumen(res.getId());
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
        Propietables prop=new Propietarios();
        Propietables cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where id="+id;
        System.out.println(sql);
        String modificar="";
        int idcontrato;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                if(rs.getInt("idcontrato")==0){
                    idcontrato=Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del contrato","ID contrato"));
                    modificar="update propiedades set idcontrato="+idcontrato+" where id="+propiedad.getId();
                    propiedad.setContrato((Contratos)cont.cargarDesdePropiedad(idcontrato));
                }else{
                propiedad.setContrato((Contratos)cont.cargarDesdePropiedad(rs.getInt("idcontrato")));
                }
                propiedad.setPropietario((Propietarios)prop.cargarDesdePropiedad(rs.getInt("idpropietario")));
                propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                //propiedad.setIdResumen(rs.getInt("idresumen"));
                //propiedad.setEstadoResumen(0);
                //listado.add(propiedad);
            }
            rs.close();
            if(modificar.length() > 2)tra.guardarRegistro(modificar);
            
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
        Propiedades propiedad;
        String sql="select * from propiedades where idcontrato=0";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                propiedad=new Propiedades();
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
        modelo.addColumn("direccion");
        modelo.addColumn("localidad");
        modelo.addColumn("categoria");
        modelo.addColumn("num contrato");
        Object[] fila=new Object[5];
        String sql="select id,direccion,localidad,idcontrato,(select rubro.descripcion from rubro where rubro.id=propiedades.rubro)as idrubro from propiedades order by direccion";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                fila[0]=rs.getInt("id");
                fila[1]=rs.getString("direccion");
                fila[2]=rs.getString("localidad");
                fila[3]=rs.getString("idrubro");
                fila[4]=rs.getString("idcontrato");
                
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
        //ACA DEVUELVO EL OBJETO CON LOS DATOS LLENOS
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorPropietario(Integer id) {
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
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));
                if(rs.getInt("idcontrato") > 0)propiedad.setContrato((Contratos)cont.Cargar(rs.getInt("idcontrato")));
                propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
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
    public DefaultListModel LlenarListConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarDesdeContratos(Integer id) {
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where id="+id;
        System.out.println(sql);
        String modificar="";
        int idcontrato;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));

                propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                //propiedad.setIdResumen(rs.getInt("idresumen"));
                //propiedad.setEstadoResumen(0);
                //listado.add(propiedad);
            }
            rs.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propiedad;
    }

    @Override
    public Object GenerarNuevoResumen(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarDesdeResumen(Integer id) {
        Generable prop=new Propietarios();
        Generable cont=new Contratos();
        Generable cta=new CuentaCorriente();
        //Generable loc=new Localidad();
        Generable rub=new Rubro();
        Personalizable per=new Usuarios();
        Transaccionable tra=new ConeccionLocal();
        Propiedades propiedad=new Propiedades();
        String sql="select * from propiedades where id="+id;
        System.out.println(sql);
        String modificar="";
        int idcontrato;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setDireccion(rs.getString("direccion"));
                propiedad.setLocalidad(rs.getString("localidad"));
                propiedad.setRubro((Rubro)rub.Cargar(rs.getInt("rubro")));

                propiedad.setPropietario((Propietarios)prop.Cargar(rs.getInt("idpropietario")));
                propiedad.setCuentaCorriente((CuentaCorriente)cta.Cargar(rs.getInt("idcuentascorriente")));
                propiedad.setUsuario((Usuarios)per.buscarPorNumero(rs.getInt("idusuario")));
                propiedad.setFecha(rs.getDate("fecha"));
                //propiedad.setIdResumen(rs.getInt("idresumen"));
                //propiedad.setEstadoResumen(0);
                //listado.add(propiedad);
            }
            rs.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propiedad;
    }

    @Override
    public Double AjustarMontoTotal(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object CargarDesdePropiedad(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
