/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas;

import Conversores.Numeros;
import interfaces.Editables;
import interfaces.Generable;
import interfaces.Listables;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.swing.JTextField;
import objetos.Articulos;
import objetos.Contratos;
import objetos.Inquilinos;
import objetos.Propiedades;
import objetos.Propietarios;

/**
 *
 * @author mauro
 */
public class ArticulosMod extends javax.swing.JInternalFrame {
    private Contratos arti=new Contratos();
    private Integer accion=0;
    private Double ajuste=0.00;
    private ArrayList listadoInq;
    private ArrayList listadoProp;
    private Propiedades propiedad=new Propiedades();
    private Inquilinos inquilino=new Inquilinos();
    

    public ArticulosMod(Contratos art) throws ParseException {
        arti=art;
        inquilino=new Inquilinos();
        Propiedades propiedade=new Propiedades();
        Calendar calendar=new GregorianCalendar();
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(arti.getVencimiento2());
        Date fecha1=ff.parse(arti.getVencimiento2());
        //calendar.setTime(arti.getVencimiento2());
        initComponents();
        this.setTitle("MODIFICACION DE CONTRATO");
        calendar.setTime(fecha1);
        System.out.println(fecha1);
        this.dateChooserCombo1.setSelectedDate(calendar);
        fecha1=ff.parse(arti.getVencimiento1());
        calendar.setTime(fecha1);
        this.dateChooserCombo2.setSelectedDate(calendar);
        try{
        this.jTextField7.setText(String.valueOf(arti.getMonto1()));
        this.jTextField1.setText(String.valueOf(arti.getMonto2()));
        Listables inq=new Inquilinos();
        Listables prop=new Propiedades();
        listadoInq=inq.listarPorEstado(0);
        listadoProp=prop.listarPorEstado(0);
        Iterator iInq=listadoInq.listIterator();
        Iterator iProp=listadoProp.listIterator();
        while(iInq.hasNext()){
            inquilino=(Inquilinos)iInq.next();
            this.jComboBox1.addItem(inquilino.getNombre());
        }
        while(iProp.hasNext()){
            propiedade=(Propiedades)iProp.next();
            this.jComboBox2.addItem(propiedade.getDireccion());
        }
        //this.jComboBox1.setSelectedItem(arti.getInquilino().getNombre());
        //this.jComboBox2.setSelectedItem(arti.getPropiedad().getDireccion());
        Propietarios propietario=new Propietarios();
        Generable genP=new Propietarios();
        propietario=(Propietarios) genP.Cargar(arti.getPropietario());
        this.jTextField2.setText(propietario.getNombre());
        }catch(java.lang.NullPointerException ec){
            System.err.println(ec);
        }
        this.jTextField7.selectAll();
        this.jTextField7.requestFocus();
        
        /*
        this.jTextField1.setText(arti.getDescripcionArticulo());
        this.jTextField2.setText(String.valueOf(arti.getStockActual()));
        this.jTextField3.setText(String.valueOf(arti.getStockMinimo()));
        this.jTextField4.setText(String.valueOf(arti.getPrecioDeCosto()));
        this.jTextField5.setText(String.valueOf(arti.getPrecioUnitarioNeto()));
        this.jTextField7.setText(String.valueOf(arti.getCodigoDeBarra()));
        this.jTextField6.setText(String.valueOf(arti.getPrecioServicio()));
        this.jCheckBox1.setSelected(arti.getModificaPrecio());
        this.jCheckBox2.setSelected(arti.getModificaServicio());
        this.jPanel2.setVisible(false);
        this.jTextField7.selectAll();
        this.jTextField7.requestFocus();
        accion=2;
        */ 
    }
    
    
    /**
     * Creates new form ArticulosMod
     */
    public ArticulosMod() {
        initComponents();
        this.jLabel6.setVisible(false);
        this.jTextField2.setVisible(false);
        this.setTitle("CARGA DE NUEVO CONTRATO");
        inquilino=new Inquilinos();
        Propiedades propiedade=new Propiedades();
        Listables inq=new Inquilinos();
        Listables prop=new Propiedades();
        listadoInq=inq.listarPorEstado(0);
        listadoProp=prop.listarPorEstado(0);
        Iterator iInq=listadoInq.listIterator();
        Iterator iProp=listadoProp.listIterator();
        while(iInq.hasNext()){
            inquilino=(Inquilinos)iInq.next();
            this.jComboBox1.addItem(inquilino.getNombre());
        }
        while(iProp.hasNext()){
            propiedade=(Propiedades)iProp.next();
            this.jComboBox2.addItem(propiedade.getDireccion());
        }
        this.jTextField7.requestFocus();
        accion=1;
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MODIFICACION DE ARTICULOS");

        jLabel1.setText("Fecha Modificacion Valores");

        jTextField1.setText("0");

        jLabel5.setText("Monto 1 er año");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha Vencimiento :");

        jTextField7.setText("0");

        dateChooserCombo1.setFormat(2);

        jLabel2.setText("Monto 2 do año");

        jLabel3.setText("Inquilino :");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Propiedad :");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Propietario :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateChooserCombo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2)))
                .addContainerGap(358, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String venc1=Numeros.LeerChooser(this.dateChooserCombo1.getSelectedDate());
        
        
        String venc2=Numeros.LeerChooser(this.dateChooserCombo2.getSelectedDate());
        System.out.println(" fecha 1 "+venc1+" fecha 2 "+venc2);
        arti.setVencimiento1(venc1);
        arti.setMonto1(Numeros.ConvertirStringADouble(this.jTextField7.getText()));
        arti.setVencimiento2(venc2);
        arti.setMonto2(Numeros.ConvertirStringADouble(this.jTextField1.getText()));
        arti.setInquilino(inquilino.getId());
        arti.setPropiedad(propiedad.getId());
        arti.setPropietario(propiedad.getPropietario());
        arti.setUsuario(Inicio.usuario.getNumeroId());
        
        Generable edit=new Contratos();
        if(accion==2){
            edit.Modificacion(arti);
        }else{
            edit.Alta(arti);
            inquilino.setPropiedad(propiedad.getId());
            inquilino.setObservaciones(" ");
            propiedad.setContrato(arti.getId());
            Generable in=new Inquilinos();
            Generable pp=new Propiedades();
            in.Modificacion(inquilino);
            pp.Modificacion(propiedad);
            
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        //ACA DEBO CARGAR DESDE EL ARRAY
        
        Generable gen=new Propiedades();
        Generable genP=new Propietarios();
        Propietarios propietario=new Propietarios();
        int posicion=this.jComboBox2.getSelectedIndex();
        //posicion++;
        propiedad=(Propiedades)listadoProp.get(posicion);
        
        try{
            this.jLabel6.setVisible(true);
            this.jTextField2.setVisible(true);
            System.out.println("propietario id "+propiedad.getPropietario());
            if(propiedad.getPropietario() > 0){
            propietario=(Propietarios)genP.Cargar(propiedad.getPropietario());
            }
            this.jTextField2.setText(propietario.getNombre());
            this.jTextField2.setEnabled(false);
        }catch(java.lang.NullPointerException e){
            System.err.println("DIO ERROR COMO SUPONIA");
            this.jLabel6.setVisible(false);
            this.jTextField2.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
       
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Generable gen=new Inquilinos();
        int pos=this.jComboBox1.getSelectedIndex();
        //pos++;
        inquilino=(Inquilinos)listadoInq.get(pos);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
