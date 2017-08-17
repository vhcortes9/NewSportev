/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.Conexion;
import Modelo.Bean.BeanMensaje;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoMensaje;
import Modelo.Dao.DaoUsuarioLogin;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class ControladorMensaje {

    /**
     * Creates a new instance of ControladorMensaje
     */
    private List<BeanMensaje> Menn;
    private BeanMensaje mesanje = new BeanMensaje();

    private BeanUsuariosLogin usuario;
    private List<BeanUsuariosLogin> us;
    private List<BeanUsuariosLogin> user;
    private List<BeanUsuariosLogin> entrnador;
    private boolean activo = false;

    public String activar() {
        activo = true;
        return "rolAdministradorIni.xhtml";
    }

    public ControladorMensaje() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");

        try {
            DaoMensaje menss = new DaoMensaje();
            Menn = menss.verMensje();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DaoMensaje dme = new DaoMensaje();
            us = dme.listaJugador();
            DaoMensaje dm = new DaoMensaje();
            user = dm.administradores();
            DaoMensaje d = new DaoMensaje();
            entrnador = d.entrenadores();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void eliminar(int id){
        String respuesta = "";
        DaoMensaje de = new DaoMensaje();
        if (de.eliminar(id)) {
          Menn = de.verMensje();
        }     
    }
  
    
    public String enviarMensaje() {
        String respuesta = "";

        DaoMensaje Dms = new DaoMensaje();

        if (Dms.enviarMen(this.mesanje)) {
            respuesta = "/EnviarMensaje.xhtml";

        } else {
            respuesta = "/EnviarMensaje.xhtml";
        }

        return respuesta;
    }
   
    public List<BeanMensaje> getMenn() {
        return Menn;
    }

    public void setMenn(List<BeanMensaje> Menn) {
        this.Menn = Menn;
    }

    public BeanMensaje getMesanje() {
        return mesanje;
    }

    public void setMesanje(BeanMensaje mesanje) {
        this.mesanje = mesanje;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public List<BeanUsuariosLogin> getUs() {
        return us;
    }

    public void setUs(List<BeanUsuariosLogin> us) {
        this.us = us;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<BeanUsuariosLogin> getUser() {
        return user;
    }

    public void setUser(List<BeanUsuariosLogin> user) {
        this.user = user;
    }

    public List<BeanUsuariosLogin> getEntrnador() {
        return entrnador;
    }

    public void setEntrnador(List<BeanUsuariosLogin> entrnador) {
        this.entrnador = entrnador;
    }
    
}
