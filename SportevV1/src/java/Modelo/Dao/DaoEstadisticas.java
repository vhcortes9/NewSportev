/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import Modelo.Bean.BeanEstadisticas;
import java.sql.*;

/**
 *
 * @author victorhugo
 */
public class DaoEstadisticas extends Conexion{
private Statement puente; 
private ResultSet rs;
private CallableStatement cs;
private Connection conn;

public boolean listo = false;

//public boolean regAcciones(){
//    try {
//        BeanEstadisticas BEstadisticas = new BeanEstadisticas();
//        conn = obtenerConexion();
//        cs = conn.prepareCall("call Sp_RegAcciones('B')");
//    } catch (Exception e) {
//    }
//}
}
