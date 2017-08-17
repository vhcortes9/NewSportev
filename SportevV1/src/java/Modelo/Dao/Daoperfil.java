/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Dao;

import Conexion.Conexion;
import static Conexion.Conexion.desconectarBD;
import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanUsuariosLogin;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class Daoperfil extends Conexion {

    private Statement puente = null;
    private ResultSet rs = null;
    private Connection conn = null;

    public boolean listo = false;
    private BeanUsuariosLogin BLog = new BeanUsuariosLogin();

    public Daoperfil() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        BLog = (BeanUsuariosLogin) session.getAttribute("user");
    }

    public boolean modificarpersona(Object obj) {
        BeanDatosPersona Bpersona = (BeanDatosPersona) obj;
        try {
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("UPDATE `persona` SET `IdRH` = '" + Bpersona.getIdRH() + "',"
                    + "`FechaNacimiento`='" + Bpersona.getFechaNacimiento() + "', "
                    + "`Nombre` = '" + Bpersona.getNombre() + "', "
                    + "`Apellido` = '" + Bpersona.getApellido() + "', "
                    + "`Identificacion` ='" + Bpersona.getIdentificacion() + "', "
                    + "`Genero` = '" + Bpersona.getGenero() + "', "
                    + "`Direccion` ='" + Bpersona.getDireccion() + "', "
                    + "`Telefono`= '" + Bpersona.getTelefono() + "',"
                    + "`Email` = '" + Bpersona.getEmail() + "',"
                    + "`IdEps` = '" + Bpersona.getIdEps() + "',"
                    + "`Genero`= '" + Bpersona.getGenero() + "'"
                    + "WHERE `persona`.`Id` = '" + Bpersona.getId() + "';");
            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }

    public boolean modificarcontraseña(Object obj) {
        try {
            BeanUsuariosLogin Beusu = (BeanUsuariosLogin) obj;
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("UPDATE `usuario` SET `Contrasenia` = '" + Beusu.getContrasenia() + "' WHERE `idUsuario` = '" + BLog.getIdUsuario() + "'");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (Exception ex) {
                Logger.getLogger(DaoUsuarioLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }

    public boolean modificarnombre(Object obj) {
        try {
            BeanUsuariosLogin Beusu = (BeanUsuariosLogin) obj;
            conn = obtenerConexion();
            puente = conn.createStatement();
            puente.executeUpdate("UPDATE `usuario` SET `Usuario` = '" + Beusu.getUsuarioNombre() + "' WHERE `idUsuario` = '" + BLog.getIdUsuario() + "'");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            try {
                reversarBD(conn);
            } catch (Exception ex) {
                Logger.getLogger(DaoUsuarioLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return listo;
    }

    public BeanUsuariosLogin verimagen() {
        BeanUsuariosLogin imag = new BeanUsuariosLogin();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("Select imagen from usuario where Idpersona = '" + BLog.getIdparticipante() + "' ");
            while (rs.next()) {
                imag.setImagen(rs.getString("imagen"));
            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imag;
    }

    public BeanDatosPersona listaPersona() {
        BeanDatosPersona Bper = new BeanDatosPersona();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT p.Id, p.Nombre, p.Apellido, p.Identificacion, p.FechaNacimiento, p.Direccion, "
                    + "p.Telefono, p.Email,h.Tipo, e.Nombre, g.Nombre from persona p INNER JOIN usuario u "
                    + "on u.Idpersona = p.Id INNER JOIN rolusuario ru on ru.IdUsuario = u.idUsuario INNER JOIN rol r "
                    + "on r.idRol = ru.IdRol INNER JOIN eps e on e.idEps = p.IdEps INNER JOIN rh h on h.idRh = p.IdRH "
                    + "INNER JOIN genero g on g.IdGenero = p.Genero WHERE p.Id = '" + BLog.getIdparticipante() + "'");
            while (rs.next()) {

                Bper.setId(rs.getString("Id"));
                Bper.setNombre(rs.getString(2));
                Bper.setApellido(rs.getString("Apellido"));
                Bper.setIdentificacion(rs.getString("Identificacion"));
                Bper.setFechaNacimiento(rs.getString("FechaNacimiento"));
                Bper.setDireccion(rs.getString("Direccion"));
                Bper.setTelefono(rs.getString("Telefono"));
                Bper.setEmail(rs.getString("Email"));
                Bper.setIdRH(rs.getString("Tipo"));
                Bper.setIdEps(rs.getString("Nombre"));
                Bper.setGenero(rs.getString(11));

            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Bper;
    }

    public List<BeanDatosPersona> listaPersonas() {
        List<BeanDatosPersona> listarPersona = new ArrayList();
        try {
            conn = Conexion.obtenerConexion();
            puente = conn.createStatement();
            rs = puente.executeQuery("SELECT distinct p.Id, p.Nombre, p.Apellido,p.Telefono,p.Direccion,p.Email,"
                    + "p.FechaNacimiento, cc.NombreTipoDoc, p.Identificacion,h.Tipo, es.Nombre, gn.Nombre "
                    + "FROM rh h INNER JOIN persona p on p.IdRH = h.idRh INNER JOIN eps es on es.idEps = p.IdEps"
                    + " INNER JOIN Genero gn on gn.IdGenero = p.Genero INNER JOIN tipodocumento cc on "
                    + "cc.idTipoDocumento = p.IdTipodocumento INNER JOIN participantes_has_equipo pe on"
                    + " pe.idJParticipante = p.Id INNER JOIN usuario u on u.Idpersona = p.Id INNER JOIN "
                    + "equipo_has_campeonato ec on ec.idEquipo = pe.idEquipo INNER JOIN campeonato c on "
                    + "c.idCampeonato = ec.idCampeonato where c.idCampeonato in (SELECT c.idCampeonato "
                    + "FROM persona p INNER JOIN campeonato c on c.idpersona = p.Id WHERE p.Id = '" + BLog.getIdparticipante() + "' ) ");
            while (rs.next()) {
                BeanDatosPersona Bper = new BeanDatosPersona();

                Bper.setId(rs.getString("Id"));
                Bper.setNombre(rs.getString(2));
                Bper.setApellido(rs.getString("Apellido"));
                Bper.setTipoIdentificacion(rs.getString("NombreTipoDoc"));
                Bper.setIdentificacion(rs.getString("Identificacion"));
                Bper.setFechaNacimiento(rs.getString("FechaNacimiento"));
                Bper.setDireccion(rs.getString("Direccion"));
                Bper.setTelefono(rs.getString("Telefono"));
                Bper.setEmail(rs.getString("Email"));
                Bper.setIdRH(rs.getString("Tipo"));
                Bper.setIdEps(rs.getString("Nombre"));
                Bper.setGenero(rs.getString(11));

                listarPersona.add(Bper);

            }
            desconectarBD(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarPersona;
    }

    public boolean registrarimagen(int id, String i) {
        try {
            puente = obtenerConexion().createStatement();
            puente.executeUpdate("Update usuario SET imagen = '" + i + "' WHERE `idUsuario` = '" + id + "'");
            desconectarBD(conn);
            listo = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listo;
    }
    int desabilitado = 1;

    public boolean desabilitarUsuario(int id) {
        try {
            puente = obtenerConexion().createStatement();
            puente.executeUpdate("Update usuario Set habilitado = '" + desabilitado + "' where Idpersona = '" + id + "'");
            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  listo;
    }
    int habilitado = 0;
    public boolean habilitarUsuario(int id) {
        try {
            puente = obtenerConexion().createStatement();
            puente.executeUpdate("Update usuario Set habilitado = '" + habilitado + "' where Idpersona = '" + id + "'");
            desconectarBD(conn);
            listo = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  listo;
    }

}
