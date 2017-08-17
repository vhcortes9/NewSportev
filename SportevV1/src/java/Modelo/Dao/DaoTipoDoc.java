/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanPreinscripcion;
import Modelo.Bean.BeanTipoDoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victorhugo
 */
public class DaoTipoDoc extends Conexion {
    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;
    public List<BeanTipoDoc> listarTipDoc() {
        List<BeanTipoDoc> listarTipodoc = new ArrayList();
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("select idTipoDocumento, NombreTipoDoc from tipodocumento");

            while (rs.next()) {
                listarTipodoc.add(new BeanTipoDoc(rs.getInt("idTipoDocumento"), rs.getString("NombreTipoDoc")));
            }
            rs.close();
            desconectarBD(conn);
        } catch (Exception e) {
            try {
                reversarBD(conn);
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(DaoTipoDoc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listarTipodoc;
    }
}
