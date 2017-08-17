/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class BeanEps {

    private String nombreEps;
    private int ideps;

    public BeanEps(int ideps, String nombreEps) {
        this.nombreEps = nombreEps;
        this.ideps = ideps;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public int getIdeps() {
        return ideps;
    }

    public void setIdeps(int ideps) {
        this.ideps = ideps;
    }
    
    
    public BeanEps() {
    }
    
}
