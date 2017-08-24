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
import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanPartido;
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
public class DaoPartido {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement pst = null;

    public boolean listo = false;

    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();

    public DaoPartido() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");

    }

    public List<BeanDatosPersona> consultarCorreos(int id, int id1) {
        List<BeanDatosPersona> listarCorreos = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT DISTINCT per.Email FROM persona"
                    + " per INNER JOIN participantes_has_equipo pq on pq.idJParticipante = per.Id"
                    + " INNER JOIN partido par on par.Equipo1 = pq.idEquipo or par.Equipo2 = pq.idEquipo "
                    + "WHERE par.Equipo1 = '"+id+"' and par.Equipo2 = '"+id1+"'");
            while (rs.next()) {
                BeanDatosPersona corr = new BeanDatosPersona();
                corr.setEmail(rs.getString("Email"));
                listarCorreos.add(corr);

            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarCorreos;
    }

    public List<BeanPartido> consultarEquiPorPartido(String Equi1, String Equi2) {
        List<BeanPartido> listar = new ArrayList();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select `NombreEquipo`, `idEquipo` from equipo where `NombreEquipo` = '" + Equi1 + "' or `NombreEquipo` = '" + Equi2 + "'");
            while (rs.next()) {
                BeanPartido BPart = new BeanPartido();
                BPart.setEquipos(rs.getString(1));
                BPart.setIdEquipo(rs.getInt(2));
                listar.add(BPart);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoPartido.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listar;
    }

    public boolean registrarPartido(Object obj) {
        BeanPartido Bepar = (BeanPartido) obj;
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("INSERT INTO `partido` ( `idCampeonato`, `Equipo1`, `Equipo2`, `Lugar`, `Hora`, `N_Fecha`, `Fecha`)"
                    + "values('" + Bepar.getIdCampeonato() + "',"
                    + "'" + Bepar.getEquipo1() + "',"
                    + "'" + Bepar.getEquipo2() + "',"
                    + "'" + Bepar.getLugar() + "',"
                    + "'" + Bepar.getHora() + "',"
                    + "'" + Bepar.getNumeroFecha() + "',"
                    + "'" + Bepar.getFecha() + "')");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
        }
        return listo;
    }

    public List<BeanPartido> consultar(int id) {
        List<BeanPartido> listarPart = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT pt.`idPartido`, cam.`Nombre`,(SELECT NombreEquipo from equipo where idEquipo =pt.`Equipo1`) "
                    + "as equipo1,(SELECT NombreEquipo from equipo where idEquipo =pt.`Equipo2`) as equipo2, pt.`Lugar`, pt.`Hora`, "
                    + "pt.`N_Fecha`, pt.`Fecha` FROM campeonato as cam INNER JOIN partido as pt on pt.`idCampeonato` = cam.`idCampeonato where pt.`idPartido` = '" + id + "'");

            while (rs.next()) {
                BeanPartido BPart = new BeanPartido();

                BPart.setIdPartido(rs.getInt(1));
                BPart.setIdCampeonato(rs.getString(2));
                BPart.setEquipo1(rs.getString(3));
                BPart.setEquipo2(rs.getString(4));
                BPart.setLugar(rs.getString(5));
                BPart.setHora(rs.getString(6));
                BPart.setNumeroFecha(rs.getInt(7));
                BPart.setFecha(rs.getString(8));

                listarPart.add(BPart);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoPartido.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listarPart;
    }

    public boolean modificar(Object obj) {
        BeanPartido Bepar = (BeanPartido) obj;
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate(" update campeonato as cam INNER JOIN partido as pt on pt.`idCampeonato` = cam.`idCampeonato`"
                    + " set pt.`Equipo1`='" + Bepar.getEquipo1() + "',"
                    + "pt.`Equipo2`='" + Bepar.getEquipo2() + "',"
                    + "pt.`Lugar`='" + Bepar.getLugar() + "',"
                    + "pt.`N_Fecha`='" + Bepar.getNumeroFecha() + "',"
                    + "pt.`Fecha`='" + Bepar.getFecha() + "'"
                    + "where pt.idPartido = '" + Bepar.getIdPartido() + "';");

            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoPartido.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }

    public List<BeanPartido> listarpartido() {

        List<BeanPartido> listarPartidos = new ArrayList();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT pt.`idPartido`, cam.`Nombre`, (SELECT NombreEquipo from equipo where idEquipo =pt.Equipo1) "
                    + "as equipo1,(SELECT NombreEquipo from equipo where idEquipo = pt.Equipo2) "
                    + "as equipo2, pt.`Lugar`, pt.`Hora`, pt.`N_Fecha`, pt.`Fecha`,cam.`idCampeonato` "
                    + "FROM campeonato as cam INNER JOIN partido as pt on pt.`idCampeonato` = cam.`idCampeonato` "
                    + "INNER JOIN persona AS per ON per.`Id` = cam.`idpersona`where per.`Id` = '" + BLog.getIdparticipante() + "'");
            while (rs.next()) {
                BeanPartido Bepartido = new BeanPartido();

                Bepartido.setIdPartido(rs.getInt(1));
                Bepartido.setIdCampeonato(rs.getString("Nombre"));
                Bepartido.setEquipo1(rs.getString(3));
                Bepartido.setEquipo2(rs.getString(4));
                Bepartido.setLugar(rs.getString(5));
                Bepartido.setHora(rs.getString(6));
                Bepartido.setNumeroFecha(rs.getInt(7));
                Bepartido.setFecha(rs.getString(8));

                listarPartidos.add(Bepartido);
                desconectarBD(conn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listarPartidos;
    }

    public List<BeanPartido> listarpartidoSegCamp(int id) {

        List<BeanPartido> listarPartidos = new ArrayList();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT pt.`idPartido`, cam.`Nombre`, (SELECT NombreEquipo from equipo where idEquipo =pt.Equipo1) "
                    + "as equipo1,(SELECT NombreEquipo from equipo where idEquipo = pt.Equipo2) "
                    + "as equipo2, pt.`Lugar`, pt.`Hora`, pt.`N_Fecha`, pt.`Fecha`,cam.`idCampeonato` "
                    + "FROM campeonato as cam INNER JOIN partido as pt on pt.`idCampeonato` = cam.`idCampeonato` "
                    + "INNER JOIN persona AS per ON per.`Id` = cam.`idpersona`where per.`Id` = '" + BLog.getIdparticipante() + "' and cam.`idCampeonato` = '" + id + "'");
            while (rs.next()) {
                BeanPartido Bepartido = new BeanPartido();

                Bepartido.setIdPartido(rs.getInt(1));
                Bepartido.setIdCampeonato(rs.getString("Nombre"));
                Bepartido.setEquipo1(rs.getString(3));
                Bepartido.setEquipo2(rs.getString(4));
                Bepartido.setLugar(rs.getString(5));
                Bepartido.setHora(rs.getString(6));
                Bepartido.setNumeroFecha(rs.getInt(7));
                Bepartido.setFecha(rs.getString(8));

                listarPartidos.add(Bepartido);
                /* Conexion.reversarBD(conn);
                 Conexion.desconectarBD(conn);*/
            }
            desconectarBD(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listarPartidos;
    }

    public boolean Eliminar(int id) {
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("DELETE FROM `partido` WHERE idPartido = '" + id + "'");
            desconectarBD(conn);
        } catch (Exception e) {
        }
        return listo;
    }
}
