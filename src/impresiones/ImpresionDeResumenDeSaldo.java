/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impresiones;

import Documentos.Documento;

/**
 *
 * @author mauro di
 */
public class ImpresionDeResumenDeSaldo extends Thread{
    private int tipoImpresion;
    private Documento documento;

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    public int getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(int tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }
    @Override
    public void run(){
        switch (this.tipoImpresion){
            case 1:
                
                break;
        }
    }
    
}
