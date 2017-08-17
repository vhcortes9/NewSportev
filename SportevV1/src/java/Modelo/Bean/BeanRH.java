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
public class BeanRH {

   private int idRH;
   private String nombreRh;

    public BeanRH(int idRH, String nombreEps) {
        this.idRH = idRH;
        this.nombreRh = nombreEps;
    }

   
    public int getIdRH() {
        return idRH;
    }

    public void setIdRH(int idRH) {
        this.idRH = idRH;
    }

    public String getNombreRh() {
        return nombreRh;
    }

    public void setNombreRH(String nombreEps) {
        this.nombreRh = nombreEps;
    }

    
   
   
    public BeanRH() {
    }
    
}
