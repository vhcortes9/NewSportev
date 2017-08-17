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
public class BeanJugador {
    private String TipoDoc;
    private String RH;
    private String Eps;
    private String FechaNac;
    private String NombreJugador;
    private String ApellidosJugador;
    private String NumDoc;
    private String Genero;
    private String Direccion;
    private String Telefono;
    private String CorreoElec; 
    private String Equipo;
    
    private int idEquipo;
    private int idTipoDoc;
    private int idRh;
    private int idEps;
    private int idParticipante;
    private int idGenero;

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    
    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getEps() {
        return Eps;
    }

    public void setEps(String Eps) {
        this.Eps = Eps;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getNombreJugador() {
        return NombreJugador;
    }

    public void setNombreJugador(String NombreJugador) {
        this.NombreJugador = NombreJugador;
    }

    public String getApellidosJugador() {
        return ApellidosJugador;
    }

    public void setApellidosJugador(String ApellidosJugador) {
        this.ApellidosJugador = ApellidosJugador;
    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String NumDoc) {
        this.NumDoc = NumDoc;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreoElec() {
        return CorreoElec;
    }

    public void setCorreoElec(String CorreoElec) {
        this.CorreoElec = CorreoElec;
    }

    public String getEquipo() {
        return Equipo;
    }

    public void setEquipo(String Equipo) {
        this.Equipo = Equipo;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdRh() {
        return idRh;
    }

    public void setIdRh(int idRh) {
        this.idRh = idRh;
    }

    public int getIdEps() {
        return idEps;
    }

    public void setIdEps(int idEps) {
        this.idEps = idEps;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    
    
    /**
     * Creates a new instance of BeanJugador
     */
    public BeanJugador() {
    }
    
}
