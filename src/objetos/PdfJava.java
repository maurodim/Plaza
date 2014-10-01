/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import interfaces.Generable;
import interfaces.Pdfable;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author mauro di
 */
public class PdfJava implements Pdfable{

    @Override
    public String GenerarPdfResumen(Object objeto) {
        //Transaccionable tra=new ConeccionLocal();
        ConeccionLocal con=new ConeccionLocal();
        Connection ch=con.getCon();
        
            
            String detalle = null;
            Resumenes resumen=new Resumenes();
            resumen=(Resumenes)objeto;
            String direccion="";
            
         Map listConsolidado=new HashMap();
        listConsolidado.put("idResumen1",resumen.getId());   
            String master="C://Gestion//Plantillas//resumenesPdf.jasper";
            direccion="C://Gestion//Resumenes//"+resumen.getId()+"_Resumen.pdf";
            String destino=direccion;
        String destino2=direccion;
        JasperReport reporte = null;
        try {
            reporte = (JasperReport)JRLoader.loadObject(master);
        } catch (JRException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(reporte, listConsolidado,ch);
        } catch (JRException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        JRExporter exporter=new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File(destino));
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File(destino2));
        try {
            exporter.exportReport();
                     //cnn.close();
        } catch (JRException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
            File f=new File(destino2);
        
            if(f.exists()){
            //JOptionPane.showMessageDialog(null,"pdf generado "+destino2);
            ServidorDeCorreos servidor=new ServidorDeCorreos();
            Boolean accionDeServ;
        Generable ge=new ServidorDeCorreos();
        servidor=(ServidorDeCorreos) ge.Cargar(1);
        if(servidor.getPuerto() == 0){
            accionDeServ=true;
        }else{
            accionDeServ=false;
           
        }
        Mail mail=new Mail(servidor);
                mail.setDireccionFile(destino2);
                mail.setDetalleListado(resumen.getId()+"_resumen.pdf");
                mail.setAsunto("Resumen de Liquidacion emitido N° :"+resumen.getId());
                mail.setDestinatario("contacto@bambusoft.com.ar");
                         try {
                             mail.enviarMailRepartoCargaCompleta();
                         } catch (MessagingException ex) {
                             Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
                         }
              /*  
                try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+destino2);
                Mail mail=new Mail();
                mail.setDireccionFile(destino2);
                mail.setDetalleListado(resumen.getId()+"hdr.pdf");
                mail.setAsunto("Resumen de Liquidacion emitido N° :"+resumen.getId());
                         try {
                             mail.enviarMailRepartoCargaCompleta();
                         } catch (MessagingException ex) {
                             Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
                         }
            } catch (IOException ex) {
                Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
            }
              */  
                
}
       return direccion;
    }
    
}
