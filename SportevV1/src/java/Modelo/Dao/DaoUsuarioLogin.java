/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import static Conexion.Conexion.desconectarBD;
import Modelo.Bean.BeanMensaje;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
public class DaoUsuarioLogin extends Conexion {

    Connection cnn;
    public BeanUsuariosLogin BLog = new BeanUsuariosLogin();

    public DaoUsuarioLogin(Connection cnn) {
        this.cnn = cnn;
    }

    public DaoUsuarioLogin() {
    }
  
        
    public BeanUsuariosLogin consultar(BeanUsuariosLogin vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT * FROM usuario WHERE Usuario = ? "
                + "and Contrasenia = ? ");
        sentencia.setString(1, vo.getUsuarioNombre());
        sentencia.setString(2, vo.getContrasenia());
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            vo.setIdUsuario(resultado.getInt("idUsuario"));
            vo.setIdparticipante(resultado.getInt("Idpersona"));
            vo.setUsuarioNombre(resultado.getString("Usuario"));
            vo.setContrasenia(resultado.getString("Contrasenia"));
            vo.setImagen(resultado.getString("imagen"));
            vo.setEstado(resultado.getString("habilitado"));

            return vo;
        }
        desconectarBD(cnn);
        return null;
    }
    public boolean estadoUsuario(BeanUsuariosLogin vo) throws SQLException {
        boolean rta=false;
        PreparedStatement sentencia = cnn.prepareStatement("SELECT * FROM usuario WHERE Usuario = ? and habilitado = 0");
        sentencia.setString(1, vo.getUsuarioNombre());
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            rta=true;
        }
        desconectarBD(cnn);
        return rta;
    }
    


    public String consultarRol(BeanUsuariosLogin vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT r.NombreRol "
                + "from rol r, usuario u, rolusuario ru "
                + "where r.idRol = ru.IdRol "
                + "and u.idUsuario = ru.IdUsuario "
                + "and u.Usuario = ? "
                + "and u.Contrasenia = ?");
        sentencia.setString(1, vo.getUsuarioNombre());
        sentencia.setString(2, vo.getContrasenia());
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return resultado.getString("r.NombreRol");
        }
        desconectarBD(cnn);
        return null;
        

    }

}
