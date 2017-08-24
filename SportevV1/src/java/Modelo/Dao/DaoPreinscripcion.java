/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanPreinscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author victorhugo
 */
public class DaoPreinscripcion extends Conexion {

    private Connection conn = null;
    private Statement puente = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private CallableStatement cs = null;

    public boolean encontrado = false;
    public boolean listo = false;

    public String de = "Sportev2017@gmail.com";
    public String pass = "vjay2017sportev";
    public String asunto = "Preinscripcion";
    public String mensaje = "Utd se encuentra preinscrito en el torneo. "
            + "Cuando page la inscripcion al organizador se le estara enviando una cuenta de nuestro sitio web";
   

    public DaoPreinscripcion() {
    }

    public boolean enviarCorreo(String de, String clave, String para, String mensaje, String asunto) {
        boolean enviado = false;
        try{

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(de, clave);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(asunto);
            message.setText(mensaje);

            System.out.println("Mail Check 2");



            System.out.println("Mail Check 3");

            Transport.send(message, de, pass);
            System.out.println("Mail Sent");
            enviado = true;
        }catch(MessagingException ex){
            System.out.println("Mail fail"+ex.getMessage());
            ex.printStackTrace();
        }
//        try {
//
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "465");
//            props.put("mail.smtp.starttls.enable", "true"); 
//
//            Session sesion = Session.getDefaultInstance(props, null);
//            MimeMessage message = new MimeMessage(sesion);
//            message.setFrom(new InternetAddress(de));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
//            message.setSubject(asunto);
//            message.setText(mensaje);
//
//            Transport transport = sesion.getTransport("smtp");
//            transport.connect("smtp.gmail.com", de, clave);
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//            enviado = true;
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("enviado:" + enviado);

        return enviado;
    }

    public boolean inscribir(String usuario, String pass, String Correo, int idper, int idEquipo ){
        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("call dbsportev.Sp_Inscribir('"+idEquipo+"', '"+usuario+"', '"+pass+"', '"+idper+"');");
            cs.executeUpdate();
            desconectarBD(conn);
            mensaje = "Felicitaciones has sido registrado en el campeonato!!!\n"
                    + "A continuacion te vamos a dejar el usuario y la contraseña con las que podras ingresar:\n"
                    + "User: '"+usuario+"'\n"
                    + "Password: '"+pass+"'\n"
                    + "Gracias por usar nuestro sistema.\n";
            asunto = "Inscripcion";
            if (enviarCorreo(de, pass, Correo, mensaje, asunto)) {
                System.out.println("Se envio");
                listo = true;
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoPreinscripcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listo;
    }
   public boolean preinscripcion(Object obj) {
        try {
            BeanPreinscripcion bp = (BeanPreinscripcion) obj;
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("call sp_Preinscripcion("
                    + "'" + bp.getIdTipoDoc() + "',"
                    + " '" + Integer.parseInt(bp.getIdRh()) + "',"
                    + " '" + Integer.parseInt(bp.getIdEps()) + "',"
                    + " '" + bp.getFechaNac() + "',"
                    + " '" + bp.getNombreEntre() + "',"
                    + " '" + bp.getApellidosEntre() + "',"
                    + " '" + bp.getNumDoc() + "',"
                    + " '" + Integer.parseInt(bp.getIdGenero()) + "',"
                    + " '" + bp.getDireccion() + "',"
                    + " '" + bp.getTelefono() + "',"
                    + " '" + bp.getCorreoElec() + "',"
                    + " '" + bp.getNombreEquipo() + "',"
                    + " '" + Integer.parseInt(bp.getIdCat()) + "',"
                    + " '" + bp.getColorUni() + "', "
                    + " '" + bp.getIdCampeonato() + "');");
            cs.executeUpdate();
            desconectarBD(conn);
            if (enviarCorreo(de, pass, bp.getCorreoElec(), mensaje, asunto)) {
                System.out.println("Se envio");
                listo = true;
            } else {
                System.out.println("Error");
            }

        } catch (Exception ex) {
            try {
                reversarBD(conn);
            } catch (SQLException ex1) {
                Logger.getLogger(DaoPreinscripcion.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
        }
        BeanPreinscripcion bp = new BeanPreinscripcion();
        System.out.println(bp.getCorreoElec());
        System.out.println(bp.getNombreEntre());
        System.out.println("listo:" + listo);
        System.out.println("encontrado:" + encontrado);
        return listo;

    }

    public List<BeanPreinscripcion> listar(int id) {

        List<BeanPreinscripcion> listarPreinscritos = new ArrayList();
        try {
            puente = Conexion.obtenerConexion().createStatement();
            rs = puente.executeQuery("select distinct persona.`Nombre`, persona.`Apellido`, equipo.`NombreEquipo`, \n"
                    + "tipodocumento.`NombreTipoDoc`, persona.`Identificacion`, persona.`Telefono`, persona.`Email`, persona.`Id`, participantes_has_equipo.`idEquipo` \n"
                    + "from dbsportev.participantes_has_equipo inner join dbsportev.persona on `persona`.`Id` = \n"
                    + "participantes_has_equipo.`idJParticipante` inner join dbsportev.equipo on equipo.`idEquipo` = \n"
                    + "participantes_has_equipo.`idEquipo` inner join tipodocumento on tipodocumento.`idTipoDocumento` = \n"
                    + "persona.`idTipoDocumento` inner join equipo_has_campeonato as ec on ec.`idEquipo` = participantes_has_equipo.`idEquipo`\n"
                    + "where ec.`idCampeonato` = '" + id + "' and participantes_has_equipo.`Jugador` = 0");
            while (rs.next()) {

                BeanPreinscripcion BPreinscrip = new BeanPreinscripcion();
                BPreinscrip.setNombreEntre(rs.getString("Nombre"));
                BPreinscrip.setApellidosEntre(rs.getString("Apellido"));
                BPreinscrip.setNombreEquipo(rs.getString("NombreEquipo"));
                BPreinscrip.setTipoDoc(rs.getString("NombreTipoDoc"));
                BPreinscrip.setNumDoc(rs.getString("Identificacion"));
                BPreinscrip.setTelefono(rs.getString("Telefono"));
                BPreinscrip.setCorreoElec(rs.getString("Email"));
                BPreinscrip.setIdPersona(rs.getInt("Id"));
                BPreinscrip.setIdEqu(rs.getInt("idEquipo"));

                listarPreinscritos.add(BPreinscrip);
                Conexion.desconectarBD(conn);
            }
        } catch (Exception ex) {
            try {
                Conexion.reversarBD(conn);
            } catch (SQLException ex1) {
                Logger.getLogger(DaoPreinscripcion.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ex.printStackTrace();
        }
        return listarPreinscritos;
    }

}
