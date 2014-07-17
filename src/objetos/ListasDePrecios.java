/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Editables;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author hernan
 */
public class ListasDePrecios implements Personalizable,Editables{
    private int numeroLista;
    private String descripcionLista;
    private Double porcentaje;
    private static Hashtable listadoDeListas=new Hashtable();
    private Integer id;
    private Double coeficiente;
    private String desccripcion;

    public ListasDePrecios() {
         this.numeroLista = 0;
        this.descripcionLista = "";
        this.porcentaje = 1.00;
    }

    public ListasDePrecios(Integer id) {
        this.id = id;
        ListasDePrecios listaD=(ListasDePrecios)listadoDeListas.get(id);
        this.coeficiente=listaD.getCoeficiente();
        this.desccripcion=listaD.getDesccripcion();
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getDesccripcion() {
        return desccripcion;
    }

    public void setDesccripcion(String desccripcion) {
        this.desccripcion = desccripcion;
    }
    public int getNumeroLista() {
        return numeroLista;
    }

    public String getDescripcionLista() {
        return descripcionLista;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }


    @Override
    public Boolean agregar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
       ArrayList listadoList=new ArrayList();
       ListasDePrecios lista=null;
       Transaccionable tra=new ConeccionLocal();
       String sql="select * from coeficienteslistas";
       ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                lista=new ListasDePrecios();
                lista.descripcionLista=rs.getString("descripcion");
                lista.porcentaje=rs.getDouble("coeficiente");
                lista.numeroLista=rs.getInt("id");
                listadoList.add(lista);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListasDePrecios.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return listadoList;
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public static void cargarMap(){
         Transaccionable tra;
         /*
         if(Inicio.coneccionRemota){
             tra=new Conecciones();
         }else{
         */ 
             tra=new ConeccionLocal();
         //}
        String sql="select * from coeficienteslistas";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoDeListas.clear();
            Integer numero=0;
            while(rs.next()){
                ListasDePrecios lista=new ListasDePrecios();
                lista.setId(rs.getInt("id"));
                lista.setDesccripcion(rs.getString("descripcion"));
                lista.setCoeficiente(rs.getDouble("coeficiente"));
                numero=lista.getId();
                listadoDeListas.put(numero,lista);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ListasDePrecios.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        //if(Inicio.coneccionRemota)BackapearListasDePrecios();
    }
    public static void BackapearListasDePrecios(){
        ListasDePrecios rs=new ListasDePrecios();
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from from coeficienteslistas";
        tra.guardarRegistro(sql);
        Enumeration<ListasDePrecios> elementos=listadoDeListas.elements();
            while(elementos.hasMoreElements()){
                rs=(ListasDePrecios)elementos.nextElement();
                sql="insert into coeficienteslistas (id,coeficiente,descripcion) values ("+rs.getId()+","+rs.getCoeficiente()+",'"+rs.getDesccripcion()+"')";
                System.out.println("LISTAS DE PRECIOS  BACKAPEARLISTAS --"+sql);
                tra.guardarRegistro(sql);
                
                }
            }
    public static ArrayList Listado(){
        ArrayList listado=new ArrayList();
        ListasDePrecios rs=new ListasDePrecios();
         Enumeration<ListasDePrecios> elementos=listadoDeListas.elements();
            while(elementos.hasMoreElements()){
                rs=(ListasDePrecios)elementos.nextElement();
                listado.add(rs);
                }
        return listado;
    }

     @Override
    public Boolean AltaObjeto(Object objeto) {
        ListasDePrecios listaDePrecios=(ListasDePrecios)objeto;
        Boolean verif=true;
        Transaccionable tra=new Conecciones();
        Double coe=listaDePrecios.getCoeficiente() / 100;
        coe=coe + 1;
        String sql="insert into coeficienteslistas (coeficiente,descripcion) values ("+coe+",'"+listaDePrecios.getDesccripcion()+"')";
        tra.guardarRegistro(sql);
        cargarMap();
        return verif;
    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        ListasDePrecios listaDePrecios=(ListasDePrecios)objeto;
        Boolean verif=true;
        Transaccionable tra=new Conecciones();
        Double coe=listaDePrecios.getCoeficiente() / 100;
        coe=coe + 1;
        String sql="update coeficienteslistas set coeficiente="+coe+",descripcion='"+listaDePrecios.getDesccripcion()+" where id="+listaDePrecios.getId();
        tra.guardarRegistro(sql);
        cargarMap();
        return verif;
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
