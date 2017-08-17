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
public class BeanCampeonato {
        private int idCampeonato;
        private int idOrgannizador;
        private int idCategoria;
        private String Nombre;
        private String FechaInicio;
        private String FechaFin;
        
        private String organizador;
        private String categoria;

    public BeanCampeonato(int idCampeonato, String Nombre) {
        this.idCampeonato = idCampeonato;
        this.Nombre = Nombre;
    }
        
        
        

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
        
        
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public int getIdOrgannizador() {
        return idOrgannizador;
    }

    public void setIdOrgannizador(int idOrgannizador) {
        this.idOrgannizador = idOrgannizador;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }
        
        
        
    public BeanCampeonato() {
    }
    
}
