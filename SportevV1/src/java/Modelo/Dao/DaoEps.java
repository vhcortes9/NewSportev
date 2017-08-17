/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanEps;
import Modelo.Bean.BeanTipoDoc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victorhugo
 */
public class DaoEps extends Conexion {
     private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    public List<BeanEps> listarEps() {
        List<BeanEps> listarEps = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select IdEps, Nombre from eps");

            while (rs.next()) {
                listarEps.add(new BeanEps(rs.getInt("IdEps"), rs.getString("Nombre")));
            }
            rs.close();
            desconectarBD(conn);
        } catch (Exception e) {
            try {
                reversarBD(conn);
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(DaoEps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listarEps;
    }
}
