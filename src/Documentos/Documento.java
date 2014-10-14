/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

import interfaces.Pdfable;
import objetos.pdfsJavaGenerador;

/**
 *
 * @author mauro di
 */
public class Documento implements Pdfable{
    private static Integer tipo;
    private Integer numero;
    private static Encabezado encabezado;
    private static CuerpoDetalle cuerpo;
    private static PieDocumento pie;

    public static void setEncabezado(Encabezado encabezado) {
        Documento.encabezado = encabezado;
    }

    public static void setCuerpo(CuerpoDetalle cuerpo) {
        Documento.cuerpo = cuerpo;
    }

    public static void setPie(PieDocumento pie) {
        Documento.pie = pie;
    }

    public static Integer getTipo() {
        return tipo;
    }

    
    public Encabezado getEncabezado() {
        return encabezado;
    }

    public CuerpoDetalle getCuerpo() {
        return cuerpo;
    }

    public PieDocumento getPie() {
        return pie;
    }
    
    
    public static void setTipo(Integer tipo) {
        Documento.tipo = tipo;
    }

    @Override
    public String GenerarPdfResumen(Object objeto) {
        String direccion = null;
        pdfsJavaGenerador pdf=new pdfsJavaGenerador();
        Documento docu=new Documento();
        switch(Documento.tipo){
            case 1:
                ResumenDeSaldo resumen=(ResumenDeSaldo)objeto;
                docu.encabezado=(Encabezado)resumen.getEncabezado();
                docu.cuerpo=(CuerpoDetalle)resumen.getCuerpoDetalle();
                docu.pie=(PieDocumento)resumen.getPieDocumento();
                pdf.setDoc(docu);
                pdf.start();
                
                break;
        }
        
        return direccion;
    }
    
    
}
