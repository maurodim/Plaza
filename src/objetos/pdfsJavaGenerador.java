/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Conversores.Numeros;
import Documentos.Documento;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import interfacesGraficas.Inicio;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        String arch=doc.getEncabezado().getNombre()+"_"+doc.getEncabezado().getIdComprobante()+".pdf";
        
        File fich=new File(arch);
        while(fich.exists()){
            i++;
            arch=doc.getEncabezado().getNombre()+"_"+doc.getEncabezado().getIdComprobante()+i+".pdf";
            fich=new File(arch);
        }
        FileOutputStream fichero;
        try {
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,14);
            cb.beginText();
            cb.setTextMatrix(120,750);
            cb.showText("RESUMEN DE SALDO - "+Inicio.configuracion.getNombre());
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(40,690);
            cb.showText("Apellido :"+doc.getEncabezado().getNombre());
            cb.setTextMatrix(410,690);
            cb.showText("Fecha "+Inicio.fechaDia);
            cb.setTextMatrix(40,670);
            cb.showText("Direccion :"+doc.getEncabezado().getDireccion());
            cb.setTextMatrix(40,650);
            cb.showText("Telefono :"+doc.getEncabezado().getTelefono());
            cb.setTextMatrix(380,650);
            cb.showText("Cuit "+doc.getEncabezado().getNumeroDeCuit());
            
            int renglon=610;
            String vencimiento;
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot=0.00;
            //aca empieza la iteracion
            Iterator itl=doc.getCuerpo().getDetalle().listIterator();
            Saldos saldo=new Saldos();
            while(itl.hasNext()){
                saldo=(Saldos)itl.next();
                vencimiento=saldo.getVencimientoString();
                descripcion="Numero Resumen de cta "+saldo.getIdResumen();
                monto=String.valueOf(saldo.getSaldo());
                recargo=String.valueOf(saldo.getRecargo());
                tot=tot + saldo.getTotal();
                total=String.valueOf(saldo.getTotal());
                cb.setTextMatrix(40,renglon);
                cb.showText(vencimiento);
                cb.setTextMatrix(110,renglon);
                cb.showText(descripcion);
                cb.setTextMatrix(280,renglon);
                cb.showText(monto);
                cb.setTextMatrix(340,renglon);
                cb.showText(recargo);
                cb.setTextMatrix(420,renglon);
                cb.showText(total);
                renglon=renglon - 20;
                
            }
            totalFinal=String.valueOf(tot);
            cb.setTextMatrix(380,renglon);
            cb.showText("TOTAL "+totalFinal);
            
            //pie de documento
            renglon=renglon - 60;
            cb.setTextMatrix(40,renglon);
            cb.showText(Inicio.configuracion.getNota1());
            cb.endText();
            documento.close();
            
            File f=new File(arch);
            if(f.exists()){
            
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+arch);
            }
            int confirmacion=JOptionPane.showConfirmDialog(null, "DESEA NOTIFICAR POR MAIL?");
            if(confirmacion==0){
                //JOptionPane.showMessageDialog(null,"acepto");
                
            }
            System.out.println("eligio "+confirmacion);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
