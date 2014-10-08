/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Documentos.Documento;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador extends Thread{
    private Documento doc=new Documento();

    public void setDoc(Documento doc) {
        this.doc = doc;
    }
    
    
    @Override
    public void run(){
        Document documento=new Document();
        int i=1;
        String arch=doc.getEncabezado().getIdComprobante()+i+".pdf";
        
        File fich=new File(arch);
        while(fich.exists()){
            i++;
            arch=doc.getEncabezado().getIdComprobante()+i+".pdf";
            fich=new File(arch);
        }
        FileOutputStream fichero;
        try {
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.beginText();
            cb.setTextMatrix(50,690);
            cb.showText("Apellido :"+doc.getEncabezado().getNombre());
            cb.setTextMatrix(50,670);
            cb.showText("Direccion :"+doc.getEncabezado().getDireccion());
            
            cb.endText();
            documento.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
