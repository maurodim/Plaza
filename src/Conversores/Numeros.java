/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversores;

import interfacesGraficas.Inicio;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Numeros {
    private static String doble;
    private static String flotante;
    private static String fecha;
    
    public static String ConvertirNumero(Double num){
        DecimalFormat formato=new DecimalFormat("####.#");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvertirFecha(Date ff){
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=ano+"-"+mes+"-"+dia;
        
        return fecha;
    }
    public static Date ConvertirStringEnDate(String ff){
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-mm-dd");
        Date fechaVal = null;    
        try {
            fechaVal = fh.parse(ff);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaVal;
    }
    public static Double ConvertirStringADouble(String num){
        num=num.replace(",",".");
        System.out.println(" rsultado "+num);
        Double dd=Double.parseDouble(num);
        return dd;
    }
    public static String LeerChooser(Calendar dias){
        DecimalFormat fr=new DecimalFormat("00");
        
        
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dias;
        //mes=dia.format(fechaNueva,null,null);
        Double pesoDia=0.00;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        
        //String fechaNueva=dateChooserCombo1.getText();
        //String fechaNueva=dateChooserCombo1.getSelectedDate();
        //String seleccion1=fechaNueva.toString();
        //String seleccion=fr.format(dd)+"/"+fr.format(mes)+"/"+ano;
        String seleccion=ano+"-"+fr.format(mes)+"-"+fr.format(dd);
        //seleccion=new SimpleDateFormat(seleccion1).format(mes);
        return seleccion;
    }
    public static Calendar ConvertirStringEnCalendar(String fecha){
        Calendar calendar=Calendar.getInstance();
        
        
        
        return calendar;
    }
    public static Integer CalcularDiasAFechaActual(Date ff){
        Integer diferencia=0;
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=new GregorianCalendar();
	Calendar c2=new GregorianCalendar();
        c2.setTime(ff);
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	String dia1;
        String dia2;
        int da=1;
        int me=Integer.parseInt(mes);
        int ann=Integer.parseInt(ano);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        dia1=dia+"-"+me+"-"+ann;
        c2.set(ann,me,da);
        long miliss1=c2.getTimeInMillis();
        Date f2=Inicio.fechaVal;
        c1.setTime(f2);
        dia=Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
	mes=Integer.toString(c1.get(Calendar.MONTH));
	ano=Integer.toString(c1.get(Calendar.YEAR));
	
        da=Integer.parseInt(dia);
        me=Integer.parseInt(mes);
        ann=Integer.parseInt(ano);
        me++;
        dia2=dia+"-"+me+"-"+ann;
        c1.set(ann,me,da);
        long miliss2=c1.getTimeInMillis();
        long diffDias=miliss2 - miliss1;
        
        String difer=String.valueOf(diffDias / (24 * 60 * 60 * 1000));
        diferencia=Integer.parseInt(difer);
        System.out.println("DIAS DE ATRASO "+diferencia+" dia 1:"+dia1+" dia2:"+dia2);
        return diferencia;
    }
    }