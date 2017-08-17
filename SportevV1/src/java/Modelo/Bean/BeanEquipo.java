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
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class BeanEquipo {

    /**
     * Creates a new instance of BeanEquipo
     */
    private int idEquipo;
    private String NombreEquipo;
    private String Categoria;
    private String Uniforme;
    

    public BeanEquipo(int idEquipo, String NombreEquipo) {
        this.idEquipo = idEquipo;
        this.NombreEquipo = NombreEquipo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getUniforme() {
        return Uniforme;
    }

    public void setUniforme(String Uniforme) {
        this.Uniforme = Uniforme;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String NombreEquipo) {
        this.NombreEquipo = NombreEquipo;
    }
    

    public BeanEquipo() {
    }

}
