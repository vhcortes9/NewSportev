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
import Modelo.Bean.BeanJugador;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victorhugo
 */
public class DaoJugador extends Conexion {

    private Statement puente = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement pst = null;
    private int id;
    public BeanJugador Bjug = new BeanJugador();
    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();

    public DaoJugador() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");
    //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //Bjug = (BeanJugador) session.getAttribute("user");
    }
    public boolean listo = false;

    public boolean registrarJugador(Object obj) {

        try {
            BeanJugador BJ = (BeanJugador) obj;
            conn = obtenerConexion();
            cs = conn.prepareCall("call sp_regJugador("
                    + "'" + BJ.getIdTipoDoc() + "', "
                    + "'" + BJ.getIdRh() + "', "
                    + "'" + BJ.getIdEps() + "', "
                    + "'" + BJ.getFechaNac() + "', "
                    + "'" + BJ.getNombreJugador() + "', "
                    + "'" + BJ.getApellidosJugador() + "', "
                    + "'" + BJ.getNumDoc() + "', "
                    + "'" + BJ.getIdGenero() + "', "
                    + "'" + BJ.getDireccion() + "', "
                    + "'" + BJ.getTelefono() + "', "
                    + "'" + BJ.getCorreoElec() + "', "
                    + "'" + BJ.getIdEquipo() + "');");

            cs.executeUpdate();
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            try {
                reversarBD(conn);
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(DaoJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listo;
    }

    public List<BeanJugador> listarJugadores(int id) {
        List<BeanJugador> listarJug = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select td.`NombreTipoDoc`, r.`Tipo`, e.`Nombre`, TIMESTAMPDIFF(YEAR, `FechaNacimiento`, CURDATE()) as Edad,"
                    + " par.`Nombre`, par.`Apellido`, par.`Identificacion`, g.`Nombre`, par.`Telefono`, par.`Email`, par.`Id`, u.`habilitado` "
                    + "from persona as par inner join tipodocumento as td on par.`idTipoDocumento` = td.`idTipoDocumento` "
                    + "inner join rh as r on par.`idRh` = r.`idRh` inner join eps as e on e.`idEps` = par.`idEps` inner join "
                    + "genero as g on g.`IdGenero` = par.`Genero` inner join participantes_has_equipo as pe on "
                    + "pe.`idJParticipante` = par.`Id` inner join usuario u on par.`Id` = u.`Idpersona` where pe.`idEquipo` = '" + id + "' and pe.`Jugador` = 1");
            while (rs.next()) {
                BeanJugador BJug = new BeanJugador();

                BJug.setNombreJugador(rs.getString(5));
                BJug.setApellidosJugador(rs.getString("Apellido"));
                BJug.setGenero(rs.getString(8));
                BJug.setTipoDoc(rs.getString("NombreTipoDoc"));
                BJug.setNumDoc(rs.getString("Identificacion"));
                BJug.setRH(rs.getString("Tipo"));
                BJug.setCorreoElec(rs.getString("Email"));
                BJug.setTelefono(rs.getString("Telefono"));
                BJug.setEps(rs.getString("Nombre"));
                BJug.setFechaNac(rs.getString(4));
                BJug.setIdParticipante(rs.getInt("Id"));
                BJug.setEstado(rs.getString("habilitado")); // algo esta fallando toca mirar ya reviso

                listarJug.add(BJug);
            }
            /*reversarBD(conn);
             desconectarBD(conn);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarJug;
    }
//conreol de jugadores  consultando los jugadores
    public List<BeanJugador> controlListarJugadores() {
        List<BeanJugador> listarJug = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT distinct per.Id, per.Nombre, per.Apellido, doc.NombreTipoDoc, per.Identificacion, per.Telefono, per.Email,"
                    + " e.Nombre,  h.Tipo, gn.Nombre, TIMESTAMPDIFF(YEAR, `FechaNacimiento`, CURDATE()) as Edad, u.habilitado FROM participantes_has_equipo pe "
                    + "INNER JOIN persona per INNER JOIN usuario u on pe.idJParticipante = per.id INNER JOIN rolusuario ru ON ru.IdUsuario = u.idUsuario "
                    + "INNER JOIN rol r on r.idRol = ru.IdRol INNER JOIN eps e on e.idEps = per.IdEps INNER JOIN rh h on h.idRh = per.IdRH INNER JOIN"
                    + " tipodocumento doc on doc.idTipoDocumento = per.IdTipodocumento INNER JOIN genero gn on gn.IdGenero = per.Genero WHERE pe.idEquipo "
                    + "in (SELECT pe.idEquipo FROM participantes_has_equipo pe INNER JOIN usuario u on pe.idJParticipante = u.Idpersona WHERE u.Idpersona = '"+BLog.getIdparticipante()+"' "
                    + "and r.NombreRol = \"Jugador\")");
            while (rs.next()) {
                BeanJugador BJug = new BeanJugador();

                BJug.setIdParticipante(rs.getInt("Id"));
                BJug.setNombreJugador(rs.getString(2));
                BJug.setApellidosJugador(rs.getString("Apellido"));
                BJug.setTipoDoc(rs.getString("NombreTipoDoc"));
                BJug.setNumDoc(rs.getString("Identificacion"));
                BJug.setRH(rs.getString("Tipo"));
                BJug.setCorreoElec(rs.getString("Email"));
                BJug.setTelefono(rs.getString("Telefono"));
                BJug.setEps(rs.getString("Nombre"));
                BJug.setFechaNac(rs.getString(4));
                BJug.setGenero(rs.getString(11));
                BJug.setEstado(rs.getString("habilitado")); // algo esta fallando toca mirar ya reviso

                listarJug.add(BJug);
            }
            /*reversarBD(conn);
             desconectarBD(conn);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarJug;
    }

    public boolean Eliminar(int id) {
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("DELETE FROM `persona` WHERE Id = '" + id + "'");
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }

    public List<BeanJugador> consJugadorXId(int id) {
        List<BeanJugador> listarJug = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select td.`NombreTipoDoc`, r.`Tipo`, e.`Nombre`, par.`FechaNacimiento`, "
                    + "par.`Nombre`, par.`Apellido`, par.`Identificacion`, g.`Nombre`, par.`Telefono`, "
                    + "par.`Email`, par.`Id`, par.`Direccion`, pe.`idEquipo` from persona as par "
                    + "inner join tipodocumento as td on par.`idTipoDocumento` = td.`idTipoDocumento` "
                    + "inner join rh as r on par.`idRh` = r.`idRh` inner join eps as e on e.`idEps` = par.`idEps` inner join "
                    + "genero as g on g.`IdGenero` = par.`Genero` inner join participantes_has_equipo as pe on "
                    + "pe.`idJParticipante` = par.`Id` where par.`Id` = '" + id + "' ");

            while (rs.next()) {
                BeanJugador BJug = new BeanJugador();

                BJug.setNombreJugador(rs.getString(5));
                BJug.setApellidosJugador(rs.getString("Apellido"));
                BJug.setGenero(rs.getString(8));
                BJug.setTipoDoc(rs.getString("NombreTipoDoc"));
                BJug.setNumDoc(rs.getString("Identificacion"));
                BJug.setRH(rs.getString("Tipo"));
                BJug.setCorreoElec(rs.getString("Email"));
                BJug.setTelefono(rs.getString("Telefono"));
                BJug.setEps(rs.getString("Nombre"));
                BJug.setFechaNac(rs.getString(4));
                BJug.setIdParticipante(rs.getInt("Id"));
                BJug.setDireccion(rs.getString("Direccion"));
                BJug.setIdEquipo(rs.getInt("idEquipo"));

                listarJug.add(BJug);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarJug;
    }

    public boolean modificar(Object obj) {
        BeanJugador BJug = (BeanJugador) obj;
        try {
            conn = obtenerConexion();
            cs = conn.prepareCall("call dbsportev.sp_updateJugador('" + BJug.getIdTipoDoc() + "', "
                    + "'" + BJug.getIdRh() + "', "
                    + "'" + BJug.getIdEps() + "', "
                    + "'" + BJug.getFechaNac() + "', "
                    + "'" + BJug.getNombreJugador() + "', "
                    + "'" + BJug.getApellidosJugador() + "', "
                    + "'" + BJug.getNumDoc() + "', "
                    + "'" + BJug.getIdGenero() + "', "
                    + "'" + BJug.getDireccion() + "', "
                    + "'" + BJug.getTelefono() + "', "
                    + "'" + BJug.getCorreoElec() + "',"
                    + "'" + BJug.getIdEquipo() + "', "
                    + "'" + BLog.getIdparticipante() + "');");
            cs.executeUpdate();
            listo = true;
            desconectarBD(conn);
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }
}
