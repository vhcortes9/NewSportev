/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanJugador;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoJugador;
import Modelo.Dao.DaoPreinscripcion;
import Modelo.Dao.DaoUsuarioLogin;
import Modelo.Dao.Daoperfil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@SessionScoped
public class controladorJugador {

    private BeanJugador BJug = new BeanJugador();
    private List<BeanJugador> consJug;
    private List<BeanJugador> listarjugadores;
    public String respuesta = "";
    private BeanUsuariosLogin usuario;
    public HttpSession idEq = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public String eliminar(int id) {
        DaoJugador daoJugador = new DaoJugador();
        if (daoJugador.Eliminar(id)) {
            respuesta = "Usuario Eliminado";

        } else {
            respuesta = "Error";

        }
        return respuesta;
    }

    public void consultar(int id) {
        DaoJugador daoJugador = new DaoJugador();
        List<BeanJugador> listar;
        listar = daoJugador.consJugadorXId(id);
        for (BeanJugador Bjug : listar) {
            BJug = Bjug;
        }
    }

    public String modificar() {
        DaoJugador DJug = new DaoJugador();
        if (DJug.modificar(this.BJug)) {
            respuesta = "rolEntrenadorIni.xhtml";
        } else {
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }
    
    public String inscribirJug(String usu, String Correo, int id){
    DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
    DaoUsuarioLogin DLog = new DaoUsuarioLogin();
    String usuEnd = "Jugador"+usu;
    String Pass = "jug"+usu+"5port3v";
        if (DPreinscripcion.inscribir(usuEnd, Pass, Correo, id, Integer.parseInt(idEq.getAttribute("idEquipo").toString()))) {
          respuesta = "index.xhtml";
        }else{
            respuesta = "Error.xhtml";
        }
        return respuesta;

    }
    public String regJugador() {
        
        int idEquipo = Integer.parseInt(idEq.getAttribute("idEquipo").toString());
        DaoJugador DJug = new DaoJugador();
        BJug.setIdEquipo(idEquipo);
        if (DJug.registrarJugador(this.BJug)) {
            respuesta = "EquiposEntrenador.xhtml";
        } else {
            respuesta = "Error.xhtml";
        }
        return respuesta;
    }

    /**
     * Creates a new instance of controladorJugador
     */
    public controladorJugador() {
        //instancia de bean login para usar atributos de la session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        //listar jugadores
        DaoJugador dju = new DaoJugador();
        listarjugadores = dju.controlListarJugadores();
        if (listarjugadores.isEmpty()) {
            System.out.println("Lista vacia");
        } else {
            System.out.println("Lista llena");
        }

    }

    public String desabilitarUsuario(int id) {
        String retorno = " ";
        Daoperfil daperfil = new Daoperfil();
        if (daperfil.desabilitarUsuario(id)) {
            FacesMessage mensaje = new FacesMessage("Usuario Desabilitado");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            DaoJugador dju = new DaoJugador();
            listarjugadores = dju.controlListarJugadores();
            retorno = "Jugadores.xhtml";

        } else {
            FacesMessage mensaje = new FacesMessage("Error");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        }
        return retorno;
    }

    public String habilitarUsuario(int id) {
        String retorno = "";
        Daoperfil daperfil = new Daoperfil();
        if (daperfil.habilitarUsuario(id)) {
            FacesMessage mensaje = new FacesMessage("Usuario Habilitado");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            DaoJugador dju = new DaoJugador();
            listarjugadores = dju.controlListarJugadores();
            retorno = "Jugadores.xhtml";// tengo otra idea 

        } else {
            FacesMessage mensaje = new FacesMessage("Usuario Error");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        }
        return retorno;

    }

    public void consultarjug(int id) {
        HttpSession idequipo = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        idequipo.setAttribute("idEquipo", id);
        DaoJugador DJug = new DaoJugador();
        consJug = DJug.listarJugadores(id);
    }

    public void cerrarEqui() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("idEquipo");
    }

    public List<BeanJugador> getConsJug() {
        return consJug;
    }

    public void setConsJug(List<BeanJugador> consJug) {
        this.consJug = consJug;
    }

    public BeanJugador getBJug() {
        return BJug;
    }

    public void setBJug(BeanJugador BJug) {
        this.BJug = BJug;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public List<BeanJugador> getListarjugadores() {
        return listarjugadores;
    }

    public void setListarjugadores(List<BeanJugador> listarjugadores) {
        this.listarjugadores = listarjugadores;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
