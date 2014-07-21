/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas;

import Actualizaciones.Actualiza;

import Excel.InformeMensual;
import interfaces.Cajeables;
import interfacesGraficasInmob.PropietariosMod;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import objetos.Articulos;
import objetos.Cajas;
import objetos.ClientesTango;
import objetos.GastosF;
import objetos.ListasDePrecios;
import objetos.Proveedores;
import objetos.Usuarios;

/**
 *
 * @author mauro
 */
public class Inicio extends javax.swing.JFrame {
    public static Integer niv;
    public static Usuarios usuario;
    public static Integer sucursal=1;
    public static Integer deposito=1;
    public static Cajas caja;
    public static String fechaDia;
    public static Date fechaVal;
    public static Integer numeroCajaAdministradora=0;
    private BufferedImage img;
    public static Boolean coneccionRemota=true;
    private static Actualiza actu=new Actualiza();

    public void setNiv(Integer nive) {
        niv = nive;
        permisos(niv);
    }
    
    
    /**
     * Creates new form Inicio
     */
    public Inicio(Integer nivel) {
        //Articulos.CargarMap();
        //if(coneccionRemota){
        //Articulos.CargarMap();
        if(Inicio.coneccionRemota){
            Proveedores.cargarListadoProv1();
        }else{
            Proveedores.cargarListadoProv();
        }
        ClientesTango.cargarMap();
        ListasDePrecios.cargarMap();
//        Cajas.BackapearCajas();
        Cajas.LeerCajaAdministradora();
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
	System.err.println(fechaDia);
        //fecha="23/12/2011";
        String fh=ano+"-"+mes+"-"+dia;
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-mm-dd");
        fechaVal = null;    
        try {
            fechaVal = ff.parse(fh);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //}else{
        
        
        /*
        Actualiza actu=new Actualiza();
        actu.start();
        */
        
        
        //}
        initComponents();
        
        
 
        //permisos(nivel);
    }

    public Inicio() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            if(coneccionRemota){
                //img = ImageIO.read(new URL("http://www.maurodi.net/imagenes/saynomore.jpg"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800,600);
            }

        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISITEMA DE GESTION BAMBU SOFTWARE");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jDesktopPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDesktopPane1ComponentShown(evt);
            }
        });

        jMenu4.setText("Usuarios");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cambiar de Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Parametros");

        jMenuItem15.setText("Tipos de Accesos");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Usuarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem16.setText("Recargos");
        jMenu5.add(jMenuItem16);

        jMenuItem18.setText("Comisiones");
        jMenu5.add(jMenuItem18);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Propietarios");

        jMenuItem20.setText("Modificacion de propietarios");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem20);

        jMenuItem23.setText("Nuevos Propietarios");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem23);

        jMenuBar1.add(jMenu6);

        jMenu1.setText("Administrador");

        jMenuItem6.setText("Iniciar Actualizador");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem8.setText("Detener Actualizador");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setText("Informes");
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Inquilinos");

        jMenuItem10.setText("Administracion de Inquilinos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem13.setText("Seguimiento de Ctas Ctes");
        jMenu2.add(jMenuItem13);

        jMenuItem14.setText("Generar Resumenes");
        jMenu2.add(jMenuItem14);

        jMenuBar1.add(jMenu2);

        jMenu8.setText("Contratos");
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });

        jMenuItem11.setText("Administracion de Contratos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);

        jMenuBar1.add(jMenu8);

        jMenu9.setText("Cuentas");

        jMenuItem17.setText("Ingreso de Nueva Cuenta");
        jMenu9.add(jMenuItem17);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDesktopPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentShown
        Loguin log=new Loguin();
        //this.jDesktopPane1.add(log);
        log.setVisible(true);
        //log.toFront();
        log.pack();
        
    }//GEN-LAST:event_jDesktopPane1ComponentShown

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Loguin ini=new Loguin();
        jDesktopPane1.add(ini);
        ini.setVisible(true);
        ini.toFront();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       
       //jMenu1.setEnabled(Inicio.usuario.getMenu().getMenu1());
       //jMenu2.setEnabled(Inicio.usuario.getMenu().getMenu2());
       //jMenu3.setEnabled(Inicio.usuario.getMenu().getMenu3());
       jMenu4.setEnabled(Inicio.usuario.getMenu().getMenu4());
       jMenu5.setEnabled(Inicio.usuario.getMenu().getMenu5());
       jMenu6.setEnabled(Inicio.usuario.getMenu().getMenu6());
       //jMenu7.setEnabled(Inicio.usuario.getMenu().getMenu7());
       //       this.jMenuItem2.setEnabled(true);
       Cajeables caj=new Cajas();
       if(caj.VerificarCaja(usuario.getNumero(),sucursal,fechaDia)){
           this.caja=(Cajas)caj.CargarCaja(usuario.getNumero(),sucursal,fechaDia);
       }else{
       this.caja=new Cajas(1);
       Double saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese Saldo Inicial","0.00"));
       System.out.println("SALDO INGRESADO "+saldo);
       this.caja.setSaldoInicial(saldo);
       
       this.caja=(Cajas) caj.AbrirCaja(caja);
       }
       //this.sucursal.setCaja(caja);
       //this.jMenuItem5.setEnabled(false);

    }//GEN-LAST:event_formComponentShown

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        /*
        UsuariosAbm abmU=new UsuariosAbm();
        jDesktopPane1.add(abmU);
        abmU.setVisible(true);
        abmU.toFront();
        */
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        AbmClientes clie=new AbmClientes();
        jDesktopPane1.add(clie);
        clie.setVisible(true);
        clie.toFront();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        /*
        TipoAccesoAbm tipo=new TipoAccesoAbm();
        jDesktopPane1.add(tipo);
        tipo.setVisible(true);
        tipo.toFront();
        */ 
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        PropietariosMod nwCliente=new PropietariosMod();
        jDesktopPane1.add(nwCliente);
        nwCliente.setVisible(true);
        nwCliente.toFront();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
       // Actualiza actu=new Actualiza();
        actu.start();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        actu.interrupt();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ProveedoresAbm prov=new ProveedoresAbm();
        jDesktopPane1.add(prov);
        prov.setVisible(true);
        prov.toFront();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        ArticulosMod art=new ArticulosMod();
        jDesktopPane1.add(art);
        art.setVisible(true);
        art.toFront();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
    public void permisos(Integer nivel){
        jMenu1.setEnabled(Inicio.usuario.getMenu().getMenu1());
       //jMenu2.setEnabled(Inicio.usuario.getMenu().getMenu2());
       //jMenu3.setEnabled(Inicio.usuario.getMenu().getMenu3());
       jMenu4.setEnabled(Inicio.usuario.getMenu().getMenu4()); 
       jMenu5.setEnabled(Inicio.usuario.getMenu().getMenu5());
       //this.jMenuItem2.setEnabled(true);
       Cajeables caj=new Cajas();
       if(caj.VerificarCaja(usuario.getNumero(),sucursal,fechaDia)){
           this.caja=(Cajas)caj.CargarCaja(usuario.getNumero(),sucursal,fechaDia);
       }else{
       this.caja=new Cajas(1);
       Double saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingrese Saldo Inicial","0.00"));
       System.out.println("SALDO INGRESADO "+saldo);
       this.caja.setSaldoInicial(saldo);
       
       this.caja=(Cajas) caj.AbrirCaja(caja);
       }
       //this.sucursal.setCaja(caja);
       //this.jMenuItem5.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    public static javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
