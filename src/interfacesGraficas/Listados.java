/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas;

import Excel.ListadosXls;
import interfaces.Componable;
import interfaces.Emitible;
import interfaces.Generable;
import interfaces.Listables;
import interfacesGraficasInmob.EmisionDeResumen;
import interfacesGraficasInmob.PropietariosMod;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Contratos;
import objetos.Inquilinos;
import objetos.Propiedades;
import objetos.Propietarios;
import objetos.Resumenes;
import objetos.Saldos;
import objetos.pdfsJavaGenerador;
import tablas.MiModeloTablaCargaHdr;
import tablas.MiModeloTablaListado;

/**
 *
 * @author mauro di
 */
public class Listados extends javax.swing.JInternalFrame {
    private Listables lsProp;
    private Componable genP;
    private int eleccion;
    /**
     * Creates new form Listados
     */
    public Listados(int objeto) {
        initComponents();
        CargarTabla(objeto);
        eleccion=objeto;
        this.jButton4.setVisible(false);
    }
    private void CargarTabla(int objeto){
        switch(objeto){
            case 1:
                this.setTitle("LISTADO DE PROPIETARIOS ");
                genP=new Propietarios();
                this.jTable1.setModel(genP.LlenarTabla(0));
                this.jButton1.setText("Modificar Propietario");
                this.jButton2.setText("Eliminar Propietario");
                this.jButton3.setVisible(false);
                break;
            case 2:
                this.setTitle("LISTADO DE PROPIEDADES ");
                genP=new Propiedades();
                this.jTable1.setModel(genP.LlenarTabla(0));
                this.jButton1.setText("Modificar Propiedad");
                this.jButton2.setText("Eliminar Propiedad");
                this.jButton3.setVisible(true);
                this.jButton4.setVisible(true);
                this.jButton4.setText("Ver Saldo");
                break;
            case 3:
                this.setTitle("LISTADO DE INQUILINOS");
                genP=new Inquilinos();
                this.jTable1.setModel(genP.LlenarTabla(0));
                this.jButton1.setText("Modificar Inquilino");
                this.jButton2.setText("Eliminar Inquilino");
                this.jButton3.setVisible(false);
                break;
            case 4:
                this.setTitle("LISTADO DE CONTRATOS");
                genP=new Contratos();
                this.jTable1.setModel(genP.LlenarTabla(0));
                this.jButton1.setText("Modificar Contrato");
                this.jButton2.setText("Eliminar Contrato");
                this.jButton3.setVisible(true);
                this.jButton3.setText("Emitir Excell");
                break;
            case 5:
                this.setTitle("EMISOR DE RESUMENES ");
                Emitible emi=new Contratos();
                //MiModeloTablaCargaHdr mmod=new MiModeloTablaCargaHdr();
                //mmod=(MiModeloTablaCargaHdr) emi.LlenarTablaParaSeleccionar();
                this.jTable1.setModel(emi.LlenarTablaParaSeleccionar());
                this.jButton1.setText("Emitir");
                this.jButton2.setVisible(false);
                this.jButton3.setVisible(false);
                break;
            case 6:
                this.setTitle("LISTADO DE RESUMENES GENERADOS");
                genP=new Resumenes();
                this.jTable1.setModel(genP.LlenarTabla(0));
                break;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        MiModeloTablaListado modelo=new MiModeloTablaListado();
        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Generable gen;
        int renglon=0;
        int numero=0;
        switch (eleccion){
            case 1:
                Propietarios propietario=new Propietarios();
                gen=new Propietarios();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                propietario=(Propietarios)gen.Cargar(numero);
                PropietariosMod mod=new PropietariosMod(propietario.getId());
                Inicio.jDesktopPane1.add(mod);
                mod.setVisible(true);
                mod.toFront();
                break;
            case 2:
                Propiedades propiedad=new Propiedades();
                gen=new Propiedades();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                propiedad=(Propiedades)gen.Cargar(numero);
                PropiedadesMod pM=new PropiedadesMod(propiedad);
                Inicio.jDesktopPane1.add(pM);
                pM.setVisible(true);
                pM.toFront();
                break;
            case 3:
                Inquilinos inquilino=new Inquilinos();
                gen=new Inquilinos();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                inquilino=(Inquilinos)gen.Cargar(numero);
                ProveedoresAbm prov=new ProveedoresAbm(inquilino);
                Inicio.jDesktopPane1.add(prov);
                prov.setVisible(true);
                prov.toFront();
                break;
            case 4:
                Contratos contrato=new Contratos();
                gen=new Contratos();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                contrato=(Contratos) gen.Cargar(numero);
        try {
            ArticulosMod articu=new ArticulosMod(contrato);
            Inicio.jDesktopPane1.add(articu);
            articu.setVisible(true);
            articu.toFront();
        } catch (ParseException ex) {
            Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
            case 5:
                int cantidad=this.jTable1.getRowCount();
                int numeroR=0;
                ArrayList listadoResumenes=new ArrayList();
                
                Resumenes resum;
                Generable ge=new Resumenes();
                Boolean selec=false;
                for(int i=0; i < cantidad;i++){
                    selec=(Boolean) this.jTable1.getValueAt(i,0);
                    if(selec){
                    numeroR=(int) this.jTable1.getValueAt(i, 3);
                    resum=new Resumenes();
                    resum=(Resumenes) ge.Cargar(numeroR);
                    System.out.println("NUMERO RESUMEN "+numeroR);
                    listadoResumenes.add(resum);
                    }
                }
                EmisionDeResumen emi=new EmisionDeResumen(listadoResumenes);
                
                emi.setVisible(true);
                emi.toFront();
                break;
            default:
                
                break;
              
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Generable gen;
        int renglon=0;
        int numero=0;
        switch (eleccion){
            case 1:
                //Propietarios propietario=new Propietarios();
                gen=new Propietarios();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                gen.Baja(numero);
                break;
            case 2:
                gen=new Propiedades();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                gen.Baja(numero);
                break;
            case 3:
                //Inquilinos inquilino=new Inquilinos();
                gen=new Inquilinos();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                gen.Baja(numero);
                break;
            case 4:
                gen=new Contratos();
                renglon=this.jTable1.getSelectedRow();
                numero=(int) this.jTable1.getValueAt(renglon,0);
                gen.Baja(numero);
                break;
            default:
                
                break;
              
        }
        CargarTabla(eleccion);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        System.out.println("ENTRO CON SHOWN");
        this.jTable1.removeAll();
        CargarTabla(eleccion);
    }//GEN-LAST:event_formComponentShown

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        System.out.println("ENTRO CON el foco");
    }//GEN-LAST:event_formFocusGained

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        System.out.println("ENTRO CON el resized");
    }//GEN-LAST:event_formComponentResized

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        System.out.println("ENTRO CON el activado");
        
        this.jTable1.removeAll();
        CargarTabla(eleccion);
    }//GEN-LAST:event_formInternalFrameActivated

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ListadosXls listados=new ListadosXls(1);
        try {
            listados.ListadoDeContratos();
        } catch (SQLException ex) {
            Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
        }
        pdfsJavaGenerador pdf=new pdfsJavaGenerador();
        pdf.start();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ListadoDeSaldos listadoS=new ListadoDeSaldos(null,true);
        Generable gen=new Saldos();
        Componable comp=new Saldos();
        ArrayList lstSaldos=new ArrayList();
        int renglon=this.jTable1.getSelectedRow();
        int numero=(int) this.jTable1.getValueAt(renglon,0);
        int numeroC=Integer.parseInt(String.valueOf(this.jTable1.getValueAt(renglon,4)));
        Saldos.setIdBuscador(numero);
        lstSaldos=gen.Listar();
        listadoS.idPropiedad=numero;
        listadoS.idContrato=numeroC;
        MiModeloTablaCargaHdr mod=new MiModeloTablaCargaHdr();
        mod=(MiModeloTablaCargaHdr) comp.LlenarTablaConArray(lstSaldos);
        listadoS.jTable1.setModel(mod);
//listadoS.jList1.setModel(comp.LlenarListConArray(lstSaldos));
        listadoS.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
