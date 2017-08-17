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
public class BeanGenero {
    private int idGenero;
    private String genero;

    public BeanGenero(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    /**
     * Creates a new instance of BeanGenero
     */
    public BeanGenero() {
    }
    
}
