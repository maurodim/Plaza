/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Generable;
import interfaces.Personalizable;
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
public class Contratos implements Generable,Componable{
    private Integer id;
    private Date fecha;
    private Double monto1;
    private String vencimiento1;
    private Double monto2;
    private String vencimiento2;
    private Inquilinos inquilino;
    private Propiedades propiedad;
    private Propietarios propietario;
    private Garantes garante;
    private String archivo;
    private Usuarios usuario;

    public Contratos() {
       // inquilino=new Inquilinos();
       // propiedad=new Propiedades();
       // garante=new Garantes();
        
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto1() {
        return monto1;
    }

    public void setMonto1(Double monto1) {
        this.monto1 = monto1;
    }

    public String getVencimiento1() {
        return vencimiento1;
    }

    public void setVencimiento1(String vencimiento1) {
        this.vencimiento1 = vencimiento1;
    }

    public Double getMonto2() {
        return monto2;
    }

    public void setMonto2(Double monto2) {
        this.monto2 = monto2;
    }

    public String getVencimiento2() {
        return vencimiento2;
    }

    public void setVencimiento2(String vencimiento2) {
        this.vencimiento2 = vencimiento2;
    }

    public Inquilinos getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilinos inquilino) {
        this.inquilino = inquilino;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public Propietarios getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietarios propietario) {
        this.propietario = propietario;
    }

    public Garantes getGarante() {
        return garante;
    }

    public void setGarante(Garantes garante) {
        this.garante = garante;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Alta(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Contratos contrato=new Contratos();
        contrato=(Contratos)objeto;
        String sql="insert into contratos(monto1,fecha1,monto2,fecha2,idinquilino,idpropiedad,idpropietario,idusuario) values ("+contrato.getMonto1()+",'"+contrato.getVencimiento1()+"',"+contrato.getMonto2()+",'"+contrato.getVencimiento2()+"',"+contrato.getInquilino().getId()+","+contrato.getPropiedad().getId()+","+contrato.getPropietario().getId()+","+contrato.getUsuario().getNumeroId()+")";
        tra.guardarRegistro(sql);
        sql="select id from contratos";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                contrato.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Baja(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from contratos where id="+id;
        tra.guardarRegistro(sql);
    }

    @Override
    public void Modificacion(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Contratos contrato=new Contratos();
        contrato=(Contratos)objeto;
        String sql="update contratos set monto1="+contrato.getMonto1()+",fecha1='"+contrato.getVencimiento1()+"',monto2="+contrato.getMonto2()+",fecha2='"+contrato.getVencimiento2()+"',idinquilino="+contrato.getInquilino().getId()+",idpropiedad="+contrato.getPropiedad().getId()+",idpropietario="+contrato.getPropietario().getId()+",idgarante="+contrato.getGarante().getId()+",archivo="+contrato.getArchivo()+",idusuario="+contrato.getUsuario().getNumeroId()+" where id="+contrato.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Generable inqui=new Inquilinos();
        Generable prop=new Propiedades();
        Generable propi=new Propietarios();
        Personalizable usu=new Usuarios();
        Generable gar=new Garantes();
        String sql="select * from contratos";
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Contratos contrato=new Contratos();
                contrato.setId(rs.getInt("id"));
                contrato.setFecha(rs.getDate("fecha"));
                contrato.setMonto1(rs.getDouble("monto1"));
                contrato.setVencimiento1(rs.getString("fecha1"));
                contrato.setMonto2(rs.getDouble("monto2"));
                contrato.setVencimiento2(rs.getString("fecha2"));
                contrato.setInquilino((Inquilinos)inqui.Cargar(rs.getInt("idinquilino")));
                contrato.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                contrato.setPropietario((Propietarios)propi.Cargar(rs.getInt("idpropietario")));
                contrato.setGarante((Garantes)gar.Cargar(rs.getInt("idgarante")));
                contrato.setArchivo(rs.getString("archivo"));
                contrato.setUsuario((Usuarios)usu.buscarPorNumero(rs.getInt("idusuario")));
                listado.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Object Cargar(Integer id) {
        //ArrayList listado=new ArrayList();
        Generable inqui=new Inquilinos();
        Generable prop=new Propiedades();
        Generable propi=new Propietarios();
        Personalizable usu=new Usuarios();
        Generable gar=new Garantes();
        Contratos contrato=new Contratos();
        String sql="select * from contratos where id="+id;
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                contrato.setId(rs.getInt("id"));
                contrato.setFecha(rs.getDate("fecha"));
                contrato.setMonto1(rs.getDouble("monto1"));
                contrato.setVencimiento1(rs.getString("fecha1"));
                contrato.setMonto2(rs.getDouble("monto2"));
                contrato.setVencimiento2(rs.getString("fecha2"));
                contrato.setInquilino((Inquilinos)inqui.Cargar(rs.getInt("idinquilino")));
                contrato.setPropiedad((Propiedades)prop.Cargar(rs.getInt("idpropiedad")));
                contrato.setPropietario((Propietarios)propi.Cargar(rs.getInt("idpropietario")));
                try{
                contrato.setGarante((Garantes)gar.Cargar(rs.getInt("idgarante")));
                }catch(java.lang.UnsupportedOperationException ex){
                    System.err.println(ex);
                }
                contrato.setArchivo(rs.getString("archivo"));
                contrato.setUsuario((Usuarios)usu.buscarPorNumero(rs.getInt("idusuario")));
                //listado.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrato;
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
        modelo.addColumn("fecha alta");
        modelo.addColumn("valor 1");
        modelo.addColumn("fecha hasta 1");
        modelo.addColumn("vencimiento");
        modelo.addColumn("valor 2");
        modelo.addColumn("inquilino");
        modelo.addColumn("propiedad");
        modelo.addColumn("propietario");
        Object[] fila=new Object[9];
        String sql="select id,fecha,monto1,fecha1,fecha2,monto2,(select inquilinos.nombre from inquilinos where inquilinos.id=contratos.idinquilino)as inquilino,(select propiedades.direccion from propiedades where propiedades.id=contratos.idpropiedad)as propiedad,(select proveedores.nombre from proveedores where proveedores.numero=contratos.idpropietario)as propietario from contratos order by fecha";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                fila[0]=rs.getInt("id");
                fila[1]=rs.getString("fecha");
                fila[2]=String.valueOf(rs.getDouble("monto1"));
                fila[3]=rs.getString("fecha2");
                fila[4]=rs.getString("fecha1");
                fila[5]=String.valueOf(rs.getDouble("monto2"));
                fila[6]=rs.getString("inquilino");
                fila[7]=rs.getString("propiedad");
                fila[8]=rs.getString("propietario");
                
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
    
    
}
