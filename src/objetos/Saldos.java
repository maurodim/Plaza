
package objetos;

import Conversores.Numeros;
import interfaces.Componable;
import interfaces.Generable;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import tablas.MiModeloTablaCargaHdr;
import tablas.MiModeloTablaListado;

/**
 *
 * @author mauro di
 */
public class Saldos implements Generable,Componable{
    private static Integer idBuscador;
    private Integer idTitular;
    private Double saldo;
    private String descripcion;
    private Date vencimiento;
    private Double recargo;
    private Double total;
    private Integer tipo;
    private Integer idResumen;
    private String vencimientoString;

    public String getVencimientoString() {
        return vencimientoString;
    }

    public void setVencimientoString(String vencimientoString) {
        this.vencimientoString = vencimientoString;
    }
    

    public static Integer getIdBuscador() {
        return idBuscador;
    }

    public static void setIdBuscador(Integer idBuscador) {
        Saldos.idBuscador = idBuscador;
    }
    

    public Integer getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(Integer idTitular) {
        this.idTitular = idTitular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
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
        
    }

    @Override
    public ArrayList Listar() {
        
        ArrayList resultado=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        Saldos saldo;
        Comisiones comision=new Comisiones();
        comision=(Comisiones)Inicio.comisiones.get(0);
        //String sql="select * from resumenes where fechavencimiento < '"+Inicio.fechaDia+"' and estado=1 and idpropiedad="+Saldos.idBuscador;
        String sql="select * from resumenes where estado=1 and idpropiedad="+Saldos.idBuscador;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               saldo=new Saldos();
               saldo.setIdTitular(rs.getInt("idpropiedad"));
               Double sald=rs.getDouble("saldo");
               Integer dias=0;
               if(Inicio.fechaVal.after(rs.getDate("fechavencimiento"))){
               dias=Numeros.CalcularDiasAFechaActual(rs.getDate("fechavencimiento"));
               }
               Double tot=0.00;
               if(dias > 0){
               Double total1=sald * dias;
               Double part1=total1 * comision.getPorcentaje();
               tot=sald + part1;
               }else{
                   tot=sald;
               }
               saldo.setSaldo(sald);
               saldo.setTotal(tot);
               saldo.setIdResumen(rs.getInt("id"));
               saldo.setVencimiento(rs.getDate("fechavencimiento"));
               saldo.setIdTitular(Saldos.idBuscador);
               resultado.add(saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Saldos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public Object Cargar(Integer id) {
        Transaccionable tra=new ConeccionLocal();
        Saldos saldo = null;
        Comisiones comision=new Comisiones();
        comision=(Comisiones)Inicio.comisiones.get(0);
        //String sql="select * from resumenes where fechavencimiento < '"+Inicio.fechaDia+"' and estado=1 and idpropiedad="+Saldos.idBuscador;
        String sql="select * from resumenes where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
               saldo=new Saldos();
               saldo.setIdTitular(rs.getInt("idpropiedad"));
               Double sald=rs.getDouble("saldo");
               Integer dias=0;
               if(Inicio.fechaVal.after(rs.getDate("fechavencimiento"))){
               dias=Numeros.CalcularDiasAFechaActual(rs.getDate("fechavencimiento"));
               }
               Double tot=0.00;
               if(dias > 0){
               Double total1=sald * dias;
               Double part1=total1 * comision.getPorcentaje();
               tot=sald + part1;
               }else{
                   tot=sald;
               }
               saldo.setSaldo(sald);
               saldo.setTotal(tot);
               saldo.setIdResumen(rs.getInt("id"));
               saldo.setVencimiento(rs.getDate("fechavencimiento"));
               saldo.setIdTitular(Saldos.idBuscador);
               //resultado.add(saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Saldos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo;
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComboBoxModel LlenarCombo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarListConArray(ArrayList listado) {
        DefaultListModel modelo=new DefaultListModel();
        Iterator il=listado.listIterator();
        Saldos saldo=new Saldos();
        while(il.hasNext()){
            saldo=(Saldos)il.next();
            modelo.addElement("Resumen :"+saldo.getIdResumen()+"Vencimiento :"+saldo.getVencimiento()+" Saldo actual :"+saldo.getSaldo());
        }
        return modelo;
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        MiModeloTablaCargaHdr modelo=new MiModeloTablaCargaHdr();
        Iterator il=listado.listIterator();
        Double recargo=0.00;
        Saldos saldo=new Saldos();
        modelo.addColumn("seleccion");
         modelo.addColumn("numero Resumen");
        modelo.addColumn("fecha vencimiento");
        modelo.addColumn("monto");
        modelo.addColumn("recargo");
        modelo.addColumn("total");
        Object[] fila=new Object[6];
        while(il.hasNext()){
            saldo=(Saldos)il.next();
            fila[0]=true;
            fila[1]=saldo.getIdResumen();
            fila[2]=saldo.getVencimiento();
            fila[3]=saldo.getSaldo();
            recargo=saldo.getTotal() - saldo.getSaldo();
            saldo.setRecargo(recargo);
            fila[4]=saldo.getRecargo();
            fila[5]=saldo.getTotal();
            modelo.addRow(fila);
//modelo.addElement("Resumen :"+saldo.getIdResumen()+"Vencimiento :"+saldo.getVencimiento()+" Saldo actual :"+saldo.getSaldo());
        }
        
        return modelo;
    }
    
    

    
}
