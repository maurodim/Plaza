/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objetos;

import interfaces.Generable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ServidorDeCorreos implements Generable{
    private String host;
    private String usuario;
    private String clave;
    private Integer puerto;
    private Boolean stats;
    private Boolean auth;
    private Integer id;

    public ServidorDeCorreos() {
        puerto=0;
    }

    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public Boolean isStats() {
        return stats;
    }

    public void setStats(Boolean stats) {
        this.stats = stats;
    }

    public Boolean isAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void Alta(Object objeto) {
        ServidorDeCorreos serv=new ServidorDeCorreos();
        serv=(ServidorDeCorreos)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="insert into servidor (host,usuario,clave,puerto,stats,auth) values ('"+serv.getHost()+"','"+serv.getUsuario()+"','"+serv.getClave()+"',"+serv.getPuerto()+",'"+serv.isStats()+"','"+serv.isAuth()+"')";
        tra.guardarRegistro(sql);
    }

    @Override
    public void Baja(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificacion(Object objeto) {
        ServidorDeCorreos serv=new ServidorDeCorreos();
        serv=(ServidorDeCorreos)objeto;
        Transaccionable tra=new ConeccionLocal();
        String sql="update servidor set host='"+serv.getHost()+"',usuario='"+serv.getUsuario()+"',clave='"+serv.getClave()+"',puerto="+serv.getPuerto()+",stats='"+serv.isStats()+"',auth='"+serv.isAuth()+"' where id="+serv.getId();
        tra.guardarRegistro(sql);
    }

    @Override
    public ArrayList Listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object Cargar(Integer id) {
        ServidorDeCorreos serv=new ServidorDeCorreos();
        String sql="select * from servidor where id="+id;
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                serv.setId(rs.getInt("id"));
                serv.setHost(rs.getString("host"));
                serv.setUsuario(rs.getString("usuario"));
                serv.setAuth(rs.getBoolean("auth"));
                serv.setPuerto(rs.getInt("puerto"));
                serv.setClave(rs.getString("clave"));
                serv.setStats(rs.getBoolean("stats"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorDeCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serv;
    }
    
    
}
