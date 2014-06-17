/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import interfacesGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 * AQUI SE VAN A GESTIONAR TODAS LAS CONECCIONES DEL SISTEMA, LOS DATOS COMO OBJETOS
 * Y LAS INTERFACES VAN A GUARDAR, ELIMINAR O ACTUALIZAR LAS TABLAS, SE VA A PASAR EL STRING POR LA INTERFAZ 
 * PARA QUE AQUI SE REALICE LA TRANSACCION
 * 
 */
public class Conecciones implements Transaccionable{
    private Connection con;
    private Statement st;

    public Conecciones() {
                MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser("maurodim");//maurodim
                    dataSource.setDatabaseName("maurodim_aseavyt");//maurodim_lseriea
                    dataSource.setPassword("mau*2012");//mau*2012
                    dataSource.setServerName("201.235.253.65");//201.235.253.65
                    con=dataSource.getConnection();
                    st=con.createStatement();
                 }catch(Exception ex){
                    
                String cod1=String.valueOf(ex);
                        
			System.out.println("NO SE PUDO CONECTAR A LA BASE "+ex);
		FileWriter fichero=null;
            PrintWriter pw=null;
            String fechaDia;
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
        fechaDia=ano+"-"+mes+"-"+dia;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+fechaDia+" - erroresDeLectura.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(ex);
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
                 }

        
    }

    @Override
    public Boolean guardarRegistro(String sql) {
        Boolean coneccion=true;
        try {
            System.out.println("SENTENCIA "+sql);
            if(st==null){
                Transaccionable tt=new ConeccionLocal();
            String ss="insert into fallas (st,estado) values ('"+sql+"',0)";
            tt.guardarRegistro(ss);
            Inicio.coneccionRemota=false;
            }else{
            st.executeUpdate(sql);
            Inicio.coneccionRemota=true;
            }
            //this.st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            coneccion=false;
            FileWriter fichero=null;
            PrintWriter pw=null;
            Inicio.coneccionRemota=false;
            Transaccionable tt=new ConeccionLocal();
            String ss="insert into fallas (st) values ('"+sql+"')";
            tt.guardarRegistro(sql);
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - erroresDeConeccion.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sql);
                
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
            
        }
        return coneccion;
    }

    @Override
    public Boolean guardarRegistros(ArrayList listadoDeSentencias) {
        String sql="";
        Boolean coneccionCorrecta=true;
        Iterator il=listadoDeSentencias.listIterator();
        while(il.hasNext()){
            sql=(String)il.next();
            try {
                st.executeUpdate(sql);
                Inicio.coneccionRemota=true;
            } catch (SQLException ex) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
                coneccionCorrecta=false;
                Inicio.coneccionRemota=false;
            }
        }
        
        return coneccionCorrecta;
    }

    @Override
    public ResultSet leerConjuntoDeRegistros(String sql) {
        ResultSet rs=null;
        try {
            /*
            if(st==null){
            Transaccionable tt=new ConeccionLocal();
            System.out.println("ACA DESDE CONECCIONES "+sql);
            rs=tt.leerConjuntoDeRegistros(sql);
            }else{
             */
            //System.out.println("ERROR EN SENTENCIA "+sql);
            st.execute(sql);
            rs=st.getResultSet();
            //}
        } catch (SQLException ex) {
            Inicio.coneccionRemota=false;
            System.out.println("NO SE CONECTA, ACA CARGA LOS OBJETOS");
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO ENTRO LA CONECCION");
        }
        catch (NullPointerException ee){
            
            if(st!=null)Inicio.coneccionRemota=false;
            
            System.out.println("ERROR "+sql);
        }
        return rs;
    }
    private Boolean ValidarConeccion(){
        Boolean verificar=true;
        if(st==null)verificar=false;
        if(con==null)verificar=false;
        return verificar;
    }
   

    }
    

