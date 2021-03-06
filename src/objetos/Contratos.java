/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Componable;
import interfaces.Contratable;
import interfaces.Emitible;
import interfaces.Generable;
import interfaces.Montable;
import interfaces.Personalizable;
import interfaces.Propietables;
import interfaces.Resumible;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
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
import tablas.MiModeloTablaCargaHdr;
import tablas.MiModeloTablaListado;

/**
 *
 * @author Usuario
 */
public class Contratos implements Generable,Componable,Emitible,Montable,Propietables{
    private Integer id;
    private Date fecha;
    private Double monto1;
    private String vencimiento1;
    private Double monto2;
    private String vencimiento2;
    private Integer inquilino;
    private Integer propiedad;
    private Integer propietario;
    private Integer garante;
    private String archivo;
    private Integer usuario;

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

    public Integer getInquilino() {
        return inquilino;
    }

    public void setInquilino(Integer inquilino) {
        this.inquilino = inquilino;
    }

    public Integer getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Integer propiedad) {
        this.propiedad = propiedad;
    }

    public Integer getPropietario() {
        return propietario;
    }

    public void setPropietario(Integer propietario) {
        this.propietario = propietario;
    }

    public Integer getGarante() {
        return garante;
    }

    public void setGarante(Integer garante) {
        this.garante = garante;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Alta(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        Contratos contrato=new Contratos();
        contrato=(Contratos)objeto;
        String sql="insert into contratos(monto1,fecha1,monto2,fecha2,idinquilino,idpropiedad,idpropietario,idusuario) values ("+contrato.getMonto1()+",'"+contrato.getVencimiento1()+"',"+contrato.getMonto2()+",'"+contrato.getVencimiento2()+"',"+contrato.getInquilino()+","+contrato.getPropiedad()+","+contrato.getPropietario()+","+contrato.getUsuario()+")";
        tra.guardarRegistro(sql);
        sql="select id from contratos";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                contrato.setId(rs.getInt(1));
            }
            rs.close();
            sql="update propiedades set idcontrato="+contrato.getId()+" where id="+contrato.getPropiedad();
            tra.guardarRegistro(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Propietarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"REINGRESE LOS DATOS, NO SE HA PODIDO GUARDAR EN LA BASE");
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
        String sql="update contratos set monto1="+contrato.getMonto1()+",fecha1='"+contrato.getVencimiento1()+"',monto2="+contrato.getMonto2()+",fecha2='"+contrato.getVencimiento2()+"',idinquilino="+contrato.getInquilino()+",idpropiedad="+contrato.getPropiedad()+",idpropietario="+contrato.getPropietario()+",idgarante="+contrato.getGarante()+",archivo="+contrato.getArchivo()+",idusuario="+contrato.getUsuario()+" where id="+contrato.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        ArrayList listado=new ArrayList();
        Contratable inqui=new Inquilinos();
        Contratable prop=new Propiedades();
        Contratable propi=new Propietarios();
        Personalizable usu=new Usuarios();
        Generable gar=new Garantes();
        String sql="select * from contratos where contratos.fecha2 > '"+Inicio.fechaDia+"'";
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
                contrato.setInquilino(rs.getInt("idinquilino"));
                System.out.println("ID CONTRATO :"+contrato.getId());
                contrato.setPropiedad(rs.getInt("idpropiedad"));
                
                contrato.setPropietario(rs.getInt("idpropietario"));
                try{
                contrato.setGarante(rs.getInt("idgarante"));
                contrato.setArchivo(rs.getString("archivo"));
                }catch(java.lang.UnsupportedOperationException exx){
                    
                }
                contrato.setUsuario(rs.getInt("idusuario"));
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
        Contratable inqui=new Inquilinos();
        Contratable prop=new Propiedades();
        Contratable propi=new Propietarios();
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
                contrato.setInquilino(rs.getInt("idinquilino"));
                contrato.setPropiedad(rs.getInt("idpropiedad"));
                contrato.setPropietario(rs.getInt("idpropietario"));
                try{
                //contrato.setGarante((Garantes)gar.Cargar(rs.getInt("idgarante")));
                }catch(java.lang.UnsupportedOperationException ex){
                    System.err.println(ex);
                }
                contrato.setArchivo(rs.getString("archivo"));
                contrato.setUsuario(rs.getInt("idusuario"));
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

    @Override
    public DefaultTableModel LlenarTablaParaSeleccionar() {
        MiModeloTablaCargaHdr mod=new MiModeloTablaCargaHdr();
        mod.addColumn("FECHA");
        mod.addColumn("PROPIEDAD");
        mod.addColumn("MONTO");
        mod.addColumn("N resumen");
        mod.addColumn("ID contrato");
        Object[] fila=new Object[5];
        Transaccionable tra=new ConeccionLocal();
        Propiedades pp;
        Contratable prop=new Propiedades();
        Personalizable per=new Usuarios();
        Resumenes resumen=new Resumenes();
        Resumible resu=new Resumenes();
        Generable gen=new Resumenes();
        Generable genC=new Contratos();
        Contratos contrato;
        int resumenId=0;
        //String sql="select contratos.id,contratos.monto1,contratos.monto2,contratos.fecha1,contratos.idpropiedad,(select resumenes.id from resumenes where resumenes.IDPROPIEDAD=contratos.idpropiedad and estado=0)as idresumen from contratos where contratos.fecha2 > '"+Inicio.fechaDia+"'";
        String sql="select contratos.id,contratos.monto1,contratos.monto2,contratos.fecha1,contratos.idpropiedad,(contratos.idgarante) as idresumen from contratos where contratos.fecha2 > '"+Inicio.fechaDia+"'";
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Double montot=0.00;
        try {
            while(rs.next()){
                if(rs.getInt("idpropiedad") > 0){
                pp=new Propiedades();
                pp=(Propiedades) prop.CargarDesdeContratos(rs.getInt("idpropiedad"));
                contrato=(Contratos) genC.Cargar(rs.getInt("id"));
                pp.setContrato(contrato.getId());
                fila[0]=true;
                fila[1]=pp.getDireccion();
                if(Inicio.fechaVal.before(rs.getDate("fecha1"))){
                    montot=rs.getDouble("monto1");
                    fila[2]=montot;
                }else{
                    montot=rs.getDouble("monto2");
                    fila[2]=montot;
                }
                if(rs.getInt("idresumen")==0){
                    resumen=new Resumenes();
                    resumen.setPropiedad(pp);
                    if(pp.getId() > 0){
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "la propiedad que no carga es id "+rs.getInt("idpropiedad"));
                    }
                    resumen.setIdGasto(1);
                    resumen.setMontoTotal(montot);
                    resumen.setUsuario(Inicio.usuario.getNumeroId());
                    resumen.setEstado(0);
                    resumen.setIdConcepto(1);
                    resumen.setDescripcion("alquiler");
                    gen.Alta(resumen);
                    resumenId=resumen.getId();
                    fila[3]=resumen.getId();
                }else{
                    resumenId=rs.getInt("idresumen");
                    fila[3]=rs.getInt("idresumen");
                }
                resu.AjustarMontoTotal(resumenId);
                fila[4]=rs.getInt("id");
                mod.addRow(fila);  
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }

    @Override
    public void GuardarArrayParaEmitir(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double LeerMontoActual(Integer id) {
        Double mod=0.00;
        Transaccionable tra=new ConeccionLocal();
        Propiedades pp;
        Generable prop=new Propiedades();
        Personalizable per=new Usuarios();
        String sql="select * from contratos where id="+id;
        System.out.println(sql);
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                
                if(Inicio.fechaVal.before(rs.getDate("fecha1"))){
                    mod=rs.getDouble("monto1");
                }else{
                    mod=rs.getDouble("monto2");
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Resumenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
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
                
                contrato.setUsuario(rs.getInt("idusuario"));
                //listado.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Contratos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrato;
    }
    
    
}
