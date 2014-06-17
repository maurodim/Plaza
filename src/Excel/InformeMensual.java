/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Transaccionable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;


/**
 *
 * @author mauro
 */
public class InformeMensual {
  /*
   * generar aca la vista para acceder
   * select *,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=1)as ventas,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=12)as gastosCaja,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=11)as pagoAProveedores from movimientoscaja group by DAY(fecha)
   * 
   * informemenusladecaja
   * SELECT *,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=1 and movimientoscaja.idCaja=caja.numero)as ventas,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=4 and movimientoscaja.idCaja=caja.numero)as retiroEfectivo,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=11 and movimientoscaja.idCaja=caja.numero)as pagoProveedores,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=12 and movimientoscaja.idCaja=caja.numero)as gastosDeCaja,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=13 and movimientoscaja.idCaja=caja.numero)as cobroCtaCte,(select usuarios.nombre from usuarios where usuarios.numero=caja.numeroUsuario)as nombreU FROM `caja`
   * 
   * 
   * informeMnesual2:
   * SELECT *,sum(monto)as tot FROM movimientoscaja group by DATE(fecha),tipoMovimiento
   * 
   * informemensualventa:
   * 
   * SELECT sum(tot)as totalVentas,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=1 group by DAY(fecha)
   * 
   * informemensualgastoscaja
   * 
   * select sum(tot)as totalGastos,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=12 group by DAY(fecha)
   * 
   * informemensualpagoproveedores
   * 
   * select sum(tot)as totalProv,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=11 group by DAY(fecha)
   * 
   * informemensualcobranzaclientes
   * 
   * select sum(tot)as totalCob,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=13 group by DAY(fecha)
   */
   public void GenerarInformeMensual(String desde,String hasta) throws SQLException{
       HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Resumen");
        /*
         * GENERAR LAS SIGUIENTES HOJAS
         * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y 
         * NUMERO DE CAJA
         * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
         * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
         * 
         */
        HSSFSheet hoja1=libro.createSheet("Movimientos");
        HSSFSheet hoja2=libro.createSheet("Articulos");
        HSSFSheet hoja3=libro.createSheet("Gastos");
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda1;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select *,(select movimientoscaja.monto from movimientoscaja where movimientoscaja.tipoMovimiento=10 and movimientoscaja.idCaja=informemensualdecaja.numero and movimientoscaja.monto < 0)as cierre from informemensualdecaja where fecha between '"+desde+"' and '"+hasta+"'";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        int col=0;
        int a=0;
            if(a==0){
                fila=hoja.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Inicial");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Ventas");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Gasto de caja");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Cobranza clientes");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("diferencia");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Entrega de Efectivo");
            celda8=fila.createCell(8);
            celda8.setCellStyle(titulo);
            celda8.setCellValue("Dejo en Caja");
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getString("nombreU"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda1.setCellValue(rs.getDouble("saldoInicial"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("ventas"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("gastosDeCaja"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("cobroCtaCte"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(rs.getDouble("diferencia"));
            Double retiro=rs.getDouble("retiroEfectivo");
            Double retFinal=rs.getDouble("cierre");
            Double tota=retiro + retFinal;
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda7.setCellValue(tota);
            if(a > 1){
            form="B"+a+"+C"+a+"+D"+a+"+E"+a+"+G"+a+"+H"+a;
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda8.setCellValue(rs.getDouble("saldoFinal"));
            }
        }
            
            /*
             * HOJA 1
             */
 
        form=null;
        sql="SELECT *,(select usuarios.nombre from usuarios where usuarios.numero=movimientoscaja.numeroUsuario) as nombreU,(select tipomovimientos.descripcion from tipomovimientos where tipomovimientos.id=movimientoscaja.tipoMovimiento)as descripcionMovimiento,(select listcli.RAZON_SOCI from listcli where listcli.codMMd=movimientoscaja.idCliente)as nombreC FROM movimientoscaja where fecha between '"+desde+"' and '"+hasta+"'";
        System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
        //HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        col=0;
        a=0;
            if(a==0){
                fila=hoja1.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion Movimiento");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Monto");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Numero Caja");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Cliente");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Observaciones");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Numero de Sucursal");

            //celda8=fila.createCell(8);
            //celda8.setCellStyle(titulo);
            //celda8.setCellValue("Dejo en Caja");
            
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja1.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rs.getString("nombreU"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("descripcionMovimiento"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("monto"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getInt("idCaja"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda4.setCellValue(rs.getString("nombreC"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda6.setCellValue(rs.getString("observaciones"));
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda7.setCellValue(rs.getInt("numeroSucursal"));
            /*
            if(a > 1){
            form="B"+a+"+C"+a+"+D"+a+"+E"+a+"+G"+a+"+H"+a;
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            celda8.setCellFormula(form);
            }
            */
            
        }
            /*
             * HOJA 2
             */
        form=null;
        sql="SELECT *,(select listcli.RAZON_SOCI from listcli where listcli.codMMd=movimientosarticulos.numeroCliente)as nombreC,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo)as descA,(select usuarios.nombre from usuarios where usuarios.numero=movimientosarticulos.numeroUsuario) as nombreU FROM movimientosarticulos where tipoMovimiento =1 and fecha between '"+desde+"' and '"+hasta+"'";
        System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
        //HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        col=0;
        a=0;
            if(a==0){
                fila=hoja2.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion Articulo");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Cantidad");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Precio de Costo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Precio de Venta");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Precio de Servicio");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("comprobante");

            celda8=fila.createCell(8);
            celda8.setCellStyle(titulo);
            celda8.setCellValue("Cliente");
            
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja2.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rs.getString("nombreU"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("descA"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("cantidad"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("precioDeCosto"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("precioDeVenta"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(rs.getDouble("precioServicio"));
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda7.setCellValue(rs.getInt("numeroComprobante"));
            
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda8.setCellValue(rs.getString("nombreC"));
            
        }    
            /*
             * HOJA 3
             */
        form=null;
        sql="SELECT *,(select usuarios.nombre from usuarios where usuarios.numero=movimientoscaja.numeroUsuario) as nombreU,(select tipomovimientos.descripcion from tipomovimientos where tipomovimientos.id=movimientoscaja.tipoMovimiento)as descripcionMovimiento,(select listcli.RAZON_SOCI from listcli where listcli.codMMd=movimientoscaja.idCliente)as nombreC FROM movimientoscaja where tipoMovimiento=12 and fecha between '"+desde+"' and '"+hasta+"'";
        System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
        //HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        col=0;
        a=0;
            if(a==0){
                fila=hoja3.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion Movimiento");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Monto");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Numero Caja");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Observaciones");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Numero de Sucursal");

            //celda8=fila.createCell(8);
            //celda8.setCellStyle(titulo);
            //celda8.setCellValue("Dejo en Caja");
            
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja3.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rs.getString("nombreU"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("descripcionMovimiento"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("monto"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getInt("idCaja"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda4.setCellValue("");
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda6.setCellValue(rs.getString("observaciones"));
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda7.setCellValue(rs.getInt("numeroSucursal"));
            /*
            if(a > 1){
            form="B"+a+"+C"+a+"+D"+a+"+E"+a+"+G"+a+"+H"+a;
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_FORMULA);
            celda8.setCellFormula(form);
            }
            */
            
        }
            
            
        rs.close();
        //texto+="\r\n";
        String ruta="C://Informes//informemensual.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
