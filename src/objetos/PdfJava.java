/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import interfaces.Pdfable;
import interfaces.Transaccionable;
import interfacesGraficas.Inicio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro di
 */
public class PdfJava implements Pdfable{

    @Override
    public String GenerarPdfResumen(Object objeto) {
        Transaccionable tra=new ConeccionLocal();
        
        
            
            String detalle = null;
            Resumenes resumen=new Resumenes();
            resumen=(Resumenes)objeto;
            String direccion="";
            String sql="select * from cuentas where idresumen="+resumen.getId();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                detalle+=rs.getString("descripcion")+" monto: "+rs.getDouble("monto")+"";
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            try{
            direccion="C://Gestion//Resumenes//"+resumen.getId()+"_Resumen.pdf";
            try (OutputStream texto_salida = new FileOutputStream(direccion)) {
                Document doc=new Document();
                PdfWriter writer=PdfWriter.getInstance(doc, texto_salida);
                doc.open();
                PdfContentByte pcb=writer.getDirectContent();
                BaseFont bf=BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1250,BaseFont.NOT_EMBEDDED);
                pcb.setFontAndSize(bf,12);
                pcb.beginText();
                
                pcb.setTextMatrix(20,320);
                pcb.showText("Fecha :"+Inicio.fechaDia);
                pcb.setTextMatrix(30,50);
                pcb.showText("Inquilino : mauro Di");
                pcb.setTextMatrix(50,320);
                pcb.showText("Resumen N° :"+resumen.getId());
                pcb.endText();
                //doc.add(new Paragraph(detalle));
                doc.close();
                texto_salida.close();
            }
        } catch (DocumentException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfJava.class.getName()).log(Level.SEVERE, null, ex);
        }
            return direccion;
    }
    
}
