/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanCategoria;
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
public class DaoCategoria extends Conexion {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public boolean listo = false;

    
    public List<BeanCategoria> listarCat() {
        List<BeanCategoria> listCat = new ArrayList();

        try {
            puente = Conexion.obtenerConexion().createStatement();
            rs = puente.executeQuery("select  idCategoria, Nombre from categoria");

            while (rs.next()) {
                listCat.add(new BeanCategoria(rs.getInt("idCategoria"), rs.getString("Nombre")));
            }
            Conexion.desconectarBD(conn);
        } catch (Exception e) {
            try {
                Conexion.reversarBD(conn);
            } catch (SQLException ex) {
                Logger.getLogger(DaoCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCat;
    }
}
