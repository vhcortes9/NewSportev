/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanRH;
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
public class DaoRh extends Conexion{
    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    
    public List<BeanRH> listarRH() {
        List<BeanRH> listrh = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select * from rh");

            while (rs.next()) {
                listrh.add(new BeanRH(rs.getInt("idRh"), rs.getString("Tipo")));
            }

            rs.close();
            Conexion.desconectarBD(conn);

        } catch (Exception e) {
            try {
                Conexion.reversarBD(conn);
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(DaoRh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listrh;
    }
}
