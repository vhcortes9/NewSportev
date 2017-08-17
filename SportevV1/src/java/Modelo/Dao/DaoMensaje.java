/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import static Conexion.Conexion.desconectarBD;
import static Conexion.Conexion.obtenerConexion;
import static Conexion.Conexion.reversarBD;
import Modelo.Bean.BeanMensaje;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
public class DaoMensaje extends Conexion {

   
    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement psp = null;

    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();
    public boolean listo = false;

    public DaoMensaje() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");
    }

    public boolean enviarMen(Object obj) {
        BeanMensaje BeMssje = (BeanMensaje) obj;
        try {
            puente = obtenerConexion().createStatement();
            puente.executeUpdate("INSERT INTO `mensaje` ( `emisor`,`reseptor`, `asunto`, `texto`) "
                    + "VALUES ('" + BLog.getUsuarioNombre() + "',"
                    + "'" + BeMssje.getIdReceptor() + "',"
                    + "'" + BeMssje.getAsunto() + "',"
                    + "'" + BeMssje.getTexto() + "')");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }
public boolean elimanar(Object obj) throws SQLException{
    BeanMensaje Bmss = (BeanMensaje) obj;
    try {
        puente = obtenerConexion().createStatement();
        puente.executeLargeUpdate("Delete From mensaje where id='"+Bmss.getIdMensaje()+"'");
    } catch (Exception e) {
    }
    return listo;
}
    public List<BeanMensaje> verMensje() {
        List<BeanMensaje> vermensaje = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT r.NombreRol, m.reseptor, m.emisor, m.asunto, m.texto, m.FechaHora, "
                    + "m.id FROM usuario u INNER JOIN rolusuario rl  on rl.IdUsuario = u.idUsuario INNER JOIN rol r "
                    + "on r.idRol = rl.IdRol INNER JOIN mensaje m on m.reseptor = u.Usuario WHERE u.Usuario = '"+BLog.getUsuarioNombre()+"'"
                    + "and m.desatendido = 0;");
            while (rs.next()) {
                BeanMensaje BeaMess = new BeanMensaje();

                BeaMess.setNombrerol(rs.getString("NombreRol"));
                BeaMess.setIdEmisor(rs.getString("emisor"));
                BeaMess.setAsunto(rs.getString("asunto"));
                BeaMess.setTexto(rs.getString("texto"));
                BeaMess.setFechaHora(rs.getString("FechaHora"));
                BeaMess.setIdMensaje(rs.getString("id"));

                vermensaje.add(BeaMess);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vermensaje;
    }
    public List<BeanMensaje> verMensjeAtendidos() {
        List<BeanMensaje> vermensaje = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT r.NombreRol, m.reseptor, m.emisor, m.asunto, m.texto, m.FechaHora, m.id\n"
                    + "FROM usuario u INNER JOIN rolusuario rl  on rl.IdUsuario = u.idUsuario INNER JOIN rol r on"
                    + " r.idRol = rl.IdRol INNER JOIN mensaje m on m.reseptor = u.Usuario "
                    + "WHERE u.Usuario ='" + BLog.getUsuarioNombre() + "' and m.desantendido = 1;");
            while (rs.next()) {
                BeanMensaje BeaMess = new BeanMensaje();

                BeaMess.setNombrerol(rs.getString("NombreRol"));
                BeaMess.setIdEmisor(rs.getString("emisor"));
                BeaMess.setAsunto(rs.getString("asunto"));
                BeaMess.setTexto(rs.getString("texto"));
                BeaMess.setFechaHora(rs.getString("FechaHora"));
                BeaMess.setIdMensaje(rs.getString("id"));

                vermensaje.add(BeaMess);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vermensaje;
    }
    int a = 1;
     public boolean atender(int id) {
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate(" UPDATE `mensaje` set `desantendido` = '"+a+"' where id = '"+id+"' ;");

            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoMensaje.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }
    public List<BeanUsuariosLogin> listaJugador() {
        List<BeanUsuariosLogin> verJugador = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT u.Usuario as Usuario, r.NombreRol as NombreRol FROM participantes_has_equipo pe INNER JOIN "
                    + "usuario u on pe.idJParticipante = u.Idpersona INNER JOIN rolusuario ru ON "
                    + "ru.IdUsuario = u.idUsuario INNER JOIN rol r on r.idRol = ru.IdRol WHERE pe.idEquipo in "
                    + "(SELECT pe.idEquipo FROM participantes_has_equipo pe INNER JOIN "
                    + "usuario u on pe.idJParticipante = u.Idpersona WHERE u.Idpersona = " + BLog.getIdparticipante()+ ");");
            while (rs.next()) {
                BeanUsuariosLogin Bequipo = new BeanUsuariosLogin();

                Bequipo.setUsuarioNombre(rs.getString("Usuario"));
                Bequipo.setNombreRol(rs.getString("NombreRol"));
                

                verJugador.add(Bequipo);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verJugador;
    }
    public List<BeanUsuariosLogin>administradores(){
        List<BeanUsuariosLogin> veradmin = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT u.Usuario, r.NombreRol, c.Nombre FROM  usuario u  INNER JOIN rolusuario ru "
                    + "ON ru.IdUsuario = u.idUsuario INNER JOIN rol r on r.idRol = ru.IdRol INNER JOIN"
                    + " campeonato c on c.idpersona = u.Idpersona WHERE r.NombreRol = \"Administrador\"");
            while (rs.next()) {                
                BeanUsuariosLogin  Buser = new BeanUsuariosLogin();
                 Buser.setUsuarioNombre(rs.getString("Usuario"));
                 Buser.setNombreRol(rs.getString("NombreRol"));
                 Buser.setNombrecampeonato(rs.getString("Nombre"));
                 veradmin.add(Buser);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veradmin;
    }
    public List<BeanUsuariosLogin>entrenadores(){
        List<BeanUsuariosLogin> veradmin = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT u.Usuario, r.NombreRol, c.Nombre, e.NombreEquipo FROM  usuario u "
                    + " INNER JOIN rolusuario ru ON ru.IdUsuario = u.idUsuario INNER JOIN rol r on r.idRol = ru.IdRol "
                    + "INNER JOIN participantes_has_equipo pe on pe.idJParticipante = u.Idpersona INNER JOIN equipo e "
                    + "on e.idEquipo = pe.idEquipo INNER JOIN equipo_has_campeonato ec on ec.idEquipo =e.idEquipo "
                    + "INNER JOIN campeonato c on c.idCampeonato = ec.idCampeonato   WHERE r.NombreRol = \"Entrenador\"");
            while (rs.next()) {                
                BeanUsuariosLogin  Buser = new BeanUsuariosLogin();
                 Buser.setUsuarioNombre(rs.getString("Usuario"));
                 Buser.setNombreRol(rs.getString("NombreRol"));
                 Buser.setNombrecampeonato(rs.getString("Nombre"));
                 Buser.setNombreequipo(rs.getString("NombreEquipo"));
                 veradmin.add(Buser);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veradmin;
    }
    public boolean eliminar(int id){
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("DELETE FROM mensaje WHERE id = '"+id+"'");
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return listo;
    }

    public List<BeanMensaje> verMensje(BeanMensaje mesanje) {
        List<BeanMensaje> vermensaje = new ArrayList();
        try {
            puente = obtenerConexion().createStatement();
            rs = puente.executeQuery("SELECT r.NombreRol, m.reseptor, m.emisor, m.asunto, m.texto, m.FechaHora, m.id\n"
                    + "FROM usuario u INNER JOIN rolusuario rl  on rl.IdUsuario = u.idUsuario INNER JOIN rol r on"
                    + " r.idRol = rl.IdRol INNER JOIN mensaje m on m.reseptor = u.Usuario "
                    + "WHERE u.Usuario ='" + BLog.getUsuarioNombre() + "';");
            while (rs.next()) {
                BeanMensaje BeaMess = new BeanMensaje();

                BeaMess.setNombrerol(rs.getString("NombreRol"));
                BeaMess.setIdEmisor(rs.getString("emisor"));
                BeaMess.setAsunto(rs.getString("asunto"));
                BeaMess.setTexto(rs.getString("texto"));
                BeaMess.setFechaHora(rs.getString("FechaHora"));
                BeaMess.setIdMensaje(rs.getString("id"));

                vermensaje.add(BeaMess);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vermensaje; }
}
