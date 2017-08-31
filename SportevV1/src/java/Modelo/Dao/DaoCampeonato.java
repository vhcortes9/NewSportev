/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Controlador.Controladorlogin;
import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victorhugo
 */
public class DaoCampeonato extends Conexion {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    private PreparedStatement pst = null;
    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();
    //public Controladorlogin contLog = new Controladorlogin();

    public boolean listo = false;

    public DaoCampeonato() throws NamingException, SQLException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");
        conn=obtenerConexion();
    }

    public boolean registrarCampeonato(Object obj) {
        BeanCampeonato BCamp = (BeanCampeonato) obj;
        try {
            puente = conn.createStatement();
            puente.executeUpdate("insert into campeonato (`idpersona`, `idCategoria`, "
                    + "`Nombre`, `FechaInicio`, `FechaFin`) "
                    + "values('" + BLog.getIdparticipante() + "', "
                    + "'" + BCamp.getIdCategoria() + "', "
                    + "'" + BCamp.getNombre() + "', "
                    + "'" + BCamp.getFechaInicio() + "', "
                    + "'" + BCamp.getFechaFin() + "')");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }
    //lista para listar en el partido 
    public List<BeanCampeonato> listacampe() {
        List<BeanCampeonato> listc = new ArrayList();
        try {
            puente = conn.createStatement();
            rs = puente.executeQuery("Select idCampeonato, Nombre From campeonato WHERE Idpersona = '"+BLog.getIdparticipante()+"' ");
            while (rs.next()) {                
                listc.add(new BeanCampeonato(rs.getInt("idCampeonato"),rs.getString("Nombre")));
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  listc;
    }

    public List<BeanCampeonato> listarCampeonato() {
        //falta crear el procedimiento almacenado para esta lista
        List<BeanCampeonato> listarCampeonatos = new ArrayList();
        try {
            puente = conn.createStatement();
            rs = puente.executeQuery("select cam.`idCampeonato`,  per.`Nombre`, cat.`Nombre`, cam.`Nombre`, cam.`FechaInicio`, cam.`FechaFin`, cam.`idpersona` from campeonato as cam inner join categoria as cat on cat.`idCategoria` = cam.`idCategoria` inner join persona as per on cam.`idpersona` = per.`Id` where per.`Id` = '" + BLog.getIdparticipante() + "'");
            while (rs.next()) {
                BeanCampeonato BCampeonato = new BeanCampeonato();
                BCampeonato.setIdCampeonato(rs.getInt(1));
                BCampeonato.setOrganizador(rs.getString(2));
                BCampeonato.setCategoria(rs.getString(3));
                BCampeonato.setNombre(rs.getString(4));
                BCampeonato.setFechaInicio(rs.getString(5));
                BCampeonato.setFechaFin(rs.getString(6));
                BCampeonato.setIdOrgannizador(rs.getInt(7));

                listarCampeonatos.add(BCampeonato);

            }
            desconectarBD(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listarCampeonatos;
    }

    public boolean eliminar(Object obj) throws SQLException {
        BeanCampeonato BCampeonato = (BeanCampeonato) obj;
        try {
            puente = conn.createStatement();
            puente.executeUpdate("DELETE FROM campeonato WHERE idCampeonato = '" + BCampeonato.getIdCampeonato() + "'");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }

    public List<BeanCampeonato> consultar(int id) {
        List<BeanCampeonato> lisXIdCamp = new ArrayList();
        BeanCampeonato BCamp = new BeanCampeonato();
        try {
            puente =conn.createStatement();
            rs = puente.executeQuery("select cam.`idCampeonato`,  per.`Nombre`, cat.`Nombre`, cam.`Nombre`, cam.`FechaInicio`, cam.`FechaFin` from campeonato as cam i"
                    + "nner join categoria as cat on cat.`idCategoria` = cam.`idCategoria` "
                    + "inner join persona as per on cam.`idpersona` = per.`Id` where cam.`idCampeonato` = '" + id + "'");
            while (rs.next()) {
                BCamp.setIdCampeonato(rs.getInt(1));
                BCamp.setOrganizador(rs.getString(2));
                BCamp.setCategoria(rs.getString(3));
                BCamp.setNombre(rs.getString(4));
                BCamp.setFechaInicio(rs.getString(5));
                BCamp.setFechaFin(rs.getString(6));

                lisXIdCamp.add(BCamp);
            }
            desconectarBD(conn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lisXIdCamp;
    }

    public boolean modificar(Object obj) {
        BeanCampeonato BCamp = (BeanCampeonato) obj;
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("update campeonato as cam inner join persona as org on cam.`idpersona` = org.`Id` "
                    + "set org.`Nombre`= '" + BCamp.getOrganizador() + "', "
                    + "cam.`idCategoria`= '" + BCamp.getIdCategoria() + "',"
                    + "cam.`Nombre`= '" + BCamp.getNombre() + "',"
                    + "cam.`FechaInicio`= '" + BCamp.getFechaInicio() + "',"
                    + "cam.`FechaFin`= '" + BCamp.getFechaFin() + "' "
                    + "WHERE cam.`idCampeonato`= '" + BCamp.getIdCampeonato() + "'; ");
            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoCampeonato.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }
}
