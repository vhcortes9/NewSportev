package Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {

    //private static Connection cnn;
    public static Connection obtenerConexion() throws NamingException, SQLException {
        InitialContext contexto = new InitialContext();
        DataSource origenDatos = (DataSource) contexto.lookup("jdbc/dbsportev");
        
        Connection cnn = origenDatos.getConnection();
        cnn.setAutoCommit(false);
        System.out.println("Conexion OK!!");
        return cnn;
    }

    public static void desconectarBD(Connection cnn) throws SQLException {
        if (cnn != null) {
            cnn.commit();
            cnn.close();
            
        }
        cnn = null;
    }

    public static void reversarBD(Connection cnn) throws SQLException {
        if (cnn != null) {
            cnn.rollback();
            cnn.close();
        }
        cnn = null;
    }

}
