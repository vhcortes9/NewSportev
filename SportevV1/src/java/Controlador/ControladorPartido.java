/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanEquipo;
import Modelo.Bean.BeanPartido;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoCampeonato;
import Modelo.Dao.DaoEquipo;
import Modelo.Dao.DaoPartido;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@SessionScoped
public class ControladorPartido {

    /**
     * Creates a new instance of ControladorPartido
     */
    private BeanPartido BPar = new BeanPartido();
    private List<BeanPartido> consPar;
    private List<BeanPartido> consEqui;
    private List<BeanDatosPersona> consultarCorreos;
    private BeanPartido partidoselecionado;
    BeanUsuariosLogin usuario;
    private boolean deshabilitarvistapartido;

    public void listarPorPart(String Equi1, String Equi2) {
        DaoPartido DPart = new DaoPartido();
        consEqui = DPart.consultarEquiPorPartido(Equi1, Equi2);
    }

    public void eliminar(int id) {
        DaoPartido dapartido = new DaoPartido();
        if (dapartido.Eliminar(id)) {
        } else {
            DaoPartido Dpa = new DaoPartido();
            consPar = Dpa.listarpartido();
        }

    }

    public String registrarPart(int id , int id1) {
        String respuesta = "";
        DaoPartido DPar = new DaoPartido();
        if (BPar.getEquipo1().equals(BPar.getEquipo2())) {
            FacesMessage mensaje = new FacesMessage("Los Equipos deben ser distintos");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        } else {
            if (DPar.registrarPartido(this.BPar)) {
                DaoPartido Dpa = new DaoPartido();
                consPar = Dpa.listarpartido();
                consultarCorreos(id, id1);

            } else {
                FacesMessage mensaje = new FacesMessage("No Se pudo Registrar");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
        }

        return respuesta;
    }

    public void consultarCorreos(int id, int id1) {
        
        DaoPartido dpart = new DaoPartido();
        consultarCorreos = dpart.consultarCorreos(id, id1);
        if (consultarCorreos.isEmpty()) {
            System.out.println("Error de lista");
        }else{
            System.out.println("lista");
        }
    }

    public String modificar() {
        String respuesta = "";
        DaoPartido DPar = new DaoPartido();
        if (DPar.modificar(this.BPar)) {
            DaoPartido Dpa = new DaoPartido();
            consPar = Dpa.listarpartido();
        } else {
            FacesMessage mensaje = new FacesMessage("Accion no realizada");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            respuesta = "/RegistrarPartido.xhtml";
        }
        return respuesta;
    }

    public List<SelectItem> consultarPartido() {
        DaoEquipo Dpar = new DaoEquipo();
        List<BeanEquipo> listar;
        List<SelectItem> listarpa = new ArrayList();
        listar = Dpar.listarEquipo();
        listar.stream().forEach((s) -> {
            listarpa.add(new SelectItem(s.getIdEquipo(), s.getNombreEquipo()));
        });
        return listarpa;
    }

    public List<SelectItem> consultarCamponatos() {
        DaoCampeonato dec = new DaoCampeonato();
        List<BeanCampeonato> listar;
        List<SelectItem> listarcap = new ArrayList();
        listar = dec.listacampe();
        listar.stream().forEach((s) -> {
            listarcap.add(new SelectItem(s.getIdCampeonato(), s.getNombre()));
        });
        return listarcap;
    }

    public void asignarEditar(BeanPartido usu) {
        deshabilitarvistapartido = true;
        System.out.println("Asignar Editar");
        this.BPar = usu;
    }

    public void consPart(int id) {
        DaoPartido DPar = new DaoPartido();
        List<BeanPartido> listar;
        listar = DPar.consultar(id);
        for (BeanPartido Bpar : listar) {
            BPar = Bpar;
        }
    }

    public void consPartidosSegCam(int id) {
        deshabilitarvistapartido = false;
        partidoselecionado = new BeanPartido();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        try {
            deshabilitarvistapartido = false;
            DaoPartido Dpar = new DaoPartido();
            consPar = Dpar.listarpartidoSegCamp(id);

        } catch (Exception e) {
        }
    }

    public ControladorPartido() {
//        deshabilitarvistapartido = false;
//        partidoselecionado = new BeanPartido();
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        usuario = (BeanUsuariosLogin) session.getAttribute("user");
//        try {
//             deshabilitarvistapartido = false;
//            DaoPartido Dpar = new DaoPartido();
//            consPar = Dpar.listarpartido();
//
//        } catch (Exception e) {
//        }

    }

    public List<BeanPartido> getConsEqui() {
        return consEqui;
    }

    public void setConsEqui(List<BeanPartido> consEqui) {
        this.consEqui = consEqui;
    }

    public BeanPartido getPartidoselecionado() {
        return partidoselecionado;
    }

    public void setPartidoselecionado(BeanPartido partidoselecionado) {
        this.partidoselecionado = partidoselecionado;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public BeanPartido getBpar() {
        return BPar;
    }

    public void setBpar(BeanPartido Bpar) {
        this.BPar = Bpar;
    }

    public List<BeanPartido> getConsPar() {
        return consPar;
    }

    public void setConsPar(List<BeanPartido> consPar) {
        this.consPar = consPar;
    }

    public boolean isDeshabilitarvistapartido() {
        return deshabilitarvistapartido;
    }

    public void setDeshabilitarvistapartido(boolean deshabilitarvistapartido) {
        this.deshabilitarvistapartido = deshabilitarvistapartido;
    }

}
