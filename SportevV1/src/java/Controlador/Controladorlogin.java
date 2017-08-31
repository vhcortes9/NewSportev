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

    public String validarUsuario() {
        try {
            Connection c = Conexion.obtenerConexion();
            DaoUsuarioLogin control = new DaoUsuarioLogin(c);
            BeanUsuariosLogin validarUsuario = control.consultar(this.usuario);
            BeanUsuariosLogin estadoUsuario = control.estadoUsuario(this.usuario);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("user", validarUsuario);
            if (estadoUsuario == null) {
                FacesMessage mensaje = new FacesMessage("usuario desabilitado ");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                
                return "";
            }
            if (validarUsuario == null) {
                FacesMessage mensaje = new FacesMessage("Contraseña o Nombre incorrectos ");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                Conexion.desconectarBD(c);
                return "";
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

        } catch (NamingException ex) {
            return "";
        } catch (SQLException ex) {
            return "";
        }
    }

    public String cerrarSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("user");
        return "index.xhtml";

    }

}
// <h:panelGroup layout="block" rendered="#{controladorForms.seleccion eq 'listaJugadores'}" >

