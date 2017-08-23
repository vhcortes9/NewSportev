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
public class BeanEstadisticas {
    
    private int idAccion;
    private int idJug;
    private int idEquipo;
    private int idPartido;
    private int TarjAmarilla;
    private int TarjRoja;
    private int TarjAzul;
    private int Goles;
    private int Autogoles;

    public int getTarjAmarilla() {
        return TarjAmarilla;
    }

    public void setTarjAmarilla(int TarjAmarilla) {
        this.TarjAmarilla = TarjAmarilla;
    }

    public int getTarjRoja() {
        return TarjRoja;
    }

    public void setTarjRoja(int TarjRoja) {
        this.TarjRoja = TarjRoja;
    }

    public int getTarjAzul() {
        return TarjAzul;
    }

    public void setTarjAzul(int TarjAzul) {
        this.TarjAzul = TarjAzul;
    }

    public int getGoles() {
        return Goles;
    }

    public void setGoles(int Goles) {
        this.Goles = Goles;
    }

    public int getAutogoles() {
        return Autogoles;
    }

    public void setAutogoles(int Autogoles) {
        this.Autogoles = Autogoles;
    }

    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public int getIdJug() {
        return idJug;
    }

    public void setIdJug(int idJug) {
        this.idJug = idJug;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    

    /**
     * Creates a new instance of BeanEstadisticas
     */
    public BeanEstadisticas() {
    }
    
}
