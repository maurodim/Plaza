/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documentos;

import interfaces.Pdfable;



/**
 *
 * @author mauro di
 */
public class ResumenDeSaldo extends Thread{
    private Encabezado encabezado;
    private CuerpoDetalle cuerpoDetalle;
    private PieDocumento pieDocumento;

    public ResumenDeSaldo(Encabezado encabezado, CuerpoDetalle cuerpoDetalle, PieDocumento pieDocumento) {
        this.encabezado = encabezado;
        this.cuerpoDetalle = cuerpoDetalle;
        this.pieDocumento = pieDocumento;
    }

    public Encabezado getEncabezado() {
        return encabezado;
    }

    public CuerpoDetalle getCuerpoDetalle() {
        return cuerpoDetalle;
    }

    public PieDocumento getPieDocumento() {
        return pieDocumento;
    }
    
    @Override
    public void run(){
        
        Pdfable pdfs=new Documento();
        Documento.setEncabezado(encabezado);
        Documento.setPie(pieDocumento);
        Documento.setTipo(1);
        pdfs.GenerarPdfResumen(this);
    }
    
}
