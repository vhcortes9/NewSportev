/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanEquipo;
import Modelo.Dao.DaoEquipo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class ControladorEntrenador {
    private List<BeanEquipo> BEqu;
    /**
     * Creates a new instance of ControladorEntrenador
     */
    public ControladorEntrenador() {
        try {
        DaoEquipo DEquipo = new DaoEquipo();
        BEqu = DEquipo.listarSegunEntrenador();
            
        } catch (Exception e) {
        }
    }

    public List<BeanEquipo> getBEqu() {
        return BEqu;
    }

    public void setBEqu(List<BeanEquipo> BEqu) {
        this.BEqu = BEqu;
    }

    
    
}
