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
public class BeanPartido {

    /**
     * Creates a new instance of BeanPartido
     */
    private int idPartido;
    private int NumeroFecha;
    private String idCampeonato;
    private String Equipo1;
    private String Equipo2;
    private String Lugar;
    private String Hora;
    private String Fecha;
    private String NombreCampeonato;
    private String Equipos;
    private int idEquipo;

    public String getEquipos() {
        return Equipos;
    }

    public void setEquipos(String Equipos) {
        this.Equipos = Equipos;
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

    public String getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(String idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getEquipo1() {
        return Equipo1;
    }

    public void setEquipo1(String Equipo1) {
        this.Equipo1 = Equipo1;
    }

    public String getEquipo2() {
        return Equipo2;
    }

    public void setEquipo2(String Equipo2) {
        this.Equipo2 = Equipo2;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public int getNumeroFecha() {
        return NumeroFecha;
    }

    public void setNumeroFecha(int NumeroFecha) {
        this.NumeroFecha = NumeroFecha;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    
    public BeanPartido() {
    }

    public String getNombreCampeonato() {
        return NombreCampeonato;
    }

    public void setNombreCampeonato(String NombreCampeonato) {
        this.NombreCampeonato = NombreCampeonato;
    }

 
}
