/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanEstadisticas;
import Modelo.Dao.DaoEstadisticas;
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
public class ControladorEstadisticas {
private BeanEstadisticas BEst = new BeanEstadisticas();
public HttpSession idPart = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    /**
     * Creates a new instance of ControladorEstadisticas
     * @param id
     */
    public void registrarAcciones(int id){
        int idPartido =  Integer.parseInt(idPart.getAttribute("idPartido").toString());
        int idEquipo =  Integer.parseInt(idPart.getAttribute("idEquipo").toString());
        BEst.setIdPartido(idPartido);
        BEst.setIdEquipo(idEquipo);
        DaoEstadisticas DEst = new DaoEstadisticas();
        DEst.regAcciones(this.BEst, id);
    
    
    }
    public ControladorEstadisticas() {
    }

    public BeanEstadisticas getBEst() {
        return BEst;
    }

    public void setBEst(BeanEstadisticas BEst) {
        this.BEst = BEst;
    }
    
}
