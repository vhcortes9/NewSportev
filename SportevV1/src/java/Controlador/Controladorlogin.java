/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoUsuarioLogin;
import java.sql.Connection;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@SessionScoped
public class Controladorlogin {

    /**
     * Creates a new instance of LoginAppBean
     */
    private BeanUsuariosLogin usuario;

    public Controladorlogin() {
        usuario = new BeanUsuariosLogin();

    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    private Connection conn = null;

    Conexion cnn = new Conexion();
    String mensajes = "";

    public String validarUsuario() {
        try {
            Connection c = Conexion.obtenerConexion();
            DaoUsuarioLogin control = new DaoUsuarioLogin(c);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            BeanUsuariosLogin validarUsuario = control.consultar(this.usuario);
            session.setAttribute("user", validarUsuario);

            if ( validarUsuario== null) {
                mensajes = "Usuario o Contraseña Incorrectas";
                return mensajes;
            } else {
            boolean estadoUsuario = control.estadoUsuario(this.usuario);
                if ( estadoUsuario == false) {
                    mensajes = " Usuario desabilitado ";
                    Conexion.desconectarBD(c);

                    return mensajes;
                } else {

                    String nombreRol = control.consultarRol(this.usuario);
                    String retorno = "";
                    if (nombreRol != null && nombreRol.equals("Administrador")) {
                        retorno = "rolAdministradorIni";
                    }

                    if (nombreRol != null && nombreRol.equals("Entrenador")) {
                        retorno = "rolEntrenadorIni";
                    }

                    if (nombreRol != null && nombreRol.equals("Jugador")) {
                        retorno = "rolJugadorIni";
                    }

                    Conexion.desconectarBD(c);
                    return retorno;
                }
            }

        } catch (NamingException ex) {
            return mensajes;
        } catch (SQLException ex) {
            return mensajes;
        }
    }
  

    public String cerrarSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("user");
        return "index.xhtml";

    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    
}
// <h:panelGroup layout="block" rendered="#{controladorForms.seleccion eq 'listaJugadores'}" >

