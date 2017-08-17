/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Controlador.Controladorlogin;
import Modelo.Bean.BeanEquipo;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
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
public class DaoEquipo extends Conexion {
        public BeanUsuariosLogin BUsLog= new BeanUsuariosLogin();  
    public DaoEquipo(){ 
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BUsLog = (BeanUsuariosLogin) session.getAttribute("user");
    }

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public boolean listo = false;

    public List<BeanEquipo> listarEquipo() {
        List<BeanEquipo> listaEqui = new ArrayList();
        try {
            puente = Conexion.obtenerConexion().createStatement();
            rs = puente.executeQuery("select idEquipo,NombreEquipo from equipo ");
            while (rs.next()) {
                listaEqui.add(new BeanEquipo(rs.getInt("idEquipo"), rs.getString("NombreEquipo")));
            }
            Conexion.reversarBD(conn);
            Conexion.desconectarBD(conn);
        } catch (Exception e) {
        }

        return listaEqui;
    }

    public List<BeanEquipo> listarSegunEntrenador() {
        List<BeanEquipo> listar = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select eq.`NombreEquipo`, cat.`Nombre`, "
                    + "eq.`Uniforme`, pe.`idEquipo` from participantes_has_equipo as pe inner "
                    + "join equipo as eq on eq.`idEquipo` = pe.`idEquipo` inner "
                    + "join categoria as cat on cat.`idCategoria` = eq.`idCategoria` "
                    + "where pe.`idJParticipante` = '"+BUsLog.getIdparticipante()+"' and pe.`Jugador` = 0 and pe.`Activo` = 1");
            while (rs.next()) {                
            BeanEquipo beanEquipo = new BeanEquipo();
                beanEquipo.setNombreEquipo(rs.getString(1));
                beanEquipo.setCategoria(rs.getString(2));
                beanEquipo.setUniforme(rs.getString(3));
                beanEquipo.setIdEquipo(rs.getInt(4));
                
                listar.add(beanEquipo);
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoEquipo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listar;
    }
}
