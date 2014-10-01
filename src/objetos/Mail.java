/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author Administrador
 */
public class Mail {
    private final Properties propiedades=new Properties();
    private String password;
    private Session sesion;
    private String direccionFile;
    private String detalleListado;
    private String asunto;
    private ServidorDeCorreos serv;
    private String destinatario;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    

    public Mail(ServidorDeCorreos se) {
        serv=new ServidorDeCorreos();
        serv=se;
        password=serv.getClave();
        System.out.println("clave "+password);
    }

    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    
    public void setDetalleListado(String detalleListado) {
        this.detalleListado = detalleListado;
    }
    
    
    public void setDireccionFile(String direccionFile) {
        this.direccionFile = direccionFile;
    }
    
    private void init(){
        propiedades.put("mail.smtp.host",serv.getHost());
        propiedades.put("mail.smtp.starttls.enable","false");//serv.isStats());
        propiedades.put("mail.transport.protocol","smtp");
        propiedades.put("mail.smtp.port",serv.getPuerto());
        propiedades.put("mail.smtp.mail.sender",serv.getUsuario());
        propiedades.put("mail.smtp.user",serv.getUsuario());
        propiedades.put("mail.smtp.auth",serv.isAuth());
        sesion=Session.getDefaultInstance(propiedades);
        
    }
    public void enviarMailRepartoCargaCompleta() throws MessagingException{
        init();
        try{
            System.out.println(direccionFile);
            MimeMessage mensaje=new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress((String)propiedades.get("mail.smtp.mail.sender")));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(this.destinatario));
            mensaje.setSubject(asunto);
            BodyPart texto=new MimeBodyPart();
            texto.setText("ES UN ENVIO DE PRUEBA   \n Saludos");
            BodyPart adjunto=new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(direccionFile)));
            adjunto.setFileName(detalleListado);
            MimeMultipart multiParte=new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            //mensaje.setText("El reparto del vehiculo esta cerrado para el reparto. Motivo :CAPACIDAD DE CARGA COMPLETADA");
            mensaje.setContent(multiParte);
            Transport t=sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), password);
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            System.err.println("EL MENSAJE NO SE PUDO ENVIAR "+me);
        }
    }
   public void enviarMailRepartoCerrado(String descripcionVehiculo,String fecha) throws MessagingException{
        init();
        try{
            MimeMessage mensaje=new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress((String)propiedades.get("mail.smtp.mail.sender")));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress("hernangonzalez@sidercon.com"));
            mensaje.addRecipient(Message.RecipientType.CC,new InternetAddress("rgonzalez@sidercon.com"));
            mensaje.setSubject("REPARTO CERRADO");
            mensaje.setText("El reparto del vehiculo "+descripcionVehiculo+" esta cerrado para el reparto del dia "+fecha+". Motivo :CAPACIDAD DE REPARTO COMPLETA");
            Transport t=sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), password);
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            System.err.println("EL MENSAJE NO SE PUDO ENVIAR "+me);
        }
    }
}
