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
public class BeanPreinscripcion {

    private String NombreEntre;
    private String ApellidosEntre;
    private String TipoDoc;
    private String NumDoc;
    private String Genero;
    private String RH;
    private String Telefono;
    private String Direccion;
    private String CorreoElec;
    private String ConfirCorreoElec;
    private String NombreEquipo;
    private String ColorUni;
    private String FechaNac;
    private String Eps;
    private String Categoria;

   
    private String idCat;
    private String idRh;
    private String idGenero;
    private String idEps;
    private int idTipoDoc;
    private int idCampeonato;
    private int idPersona;
    private int idEqu;

    public int getIdEqu() {
        return idEqu;
    }

    public void setIdEqu(int idEqu) {
        this.idEqu = idEqu;
    }
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

   

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    
    public BeanPreinscripcion(String NombreEntre, String ApellidosEntre, String TipoDoc, String NumDoc, String Genero, String RH, String Telefono, String Direccion, String CorreoElec, String ConfirCorreoElec, String NombreEquipo, String ColorUni, String FechaNac, String Eps, String Categoria) {
        this.NombreEntre = NombreEntre;
        this.ApellidosEntre = ApellidosEntre;
        this.TipoDoc = TipoDoc;
        this.NumDoc = NumDoc;
        this.Genero = Genero;
        this.RH = RH;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.CorreoElec = CorreoElec;
        this.ConfirCorreoElec = ConfirCorreoElec;
        this.NombreEquipo = NombreEquipo;
        this.ColorUni = ColorUni;
        this.FechaNac = FechaNac;
        this.Eps = Eps;
        this.Categoria = Categoria;
    }

    public BeanPreinscripcion(String TipoDoc, int idTipoDoc) {
        this.TipoDoc = TipoDoc;
        this.idTipoDoc = idTipoDoc;
    }

    

    public String getNombreEntre() {
        return NombreEntre;
    }

    public void setNombreEntre(String NombreEntre) {
        this.NombreEntre = NombreEntre;
    }

    public String getApellidosEntre() {
        return ApellidosEntre;
    }

    public void setApellidosEntre(String ApellidosEntre) {
        this.ApellidosEntre = ApellidosEntre;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
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

    public String getRH() {
        return RH;
    }

    public void setRH(String RH) {
        this.RH = RH;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreoElec() {
        return CorreoElec;
    }

    public void setCorreoElec(String CorreoElec) {
        this.CorreoElec = CorreoElec;
    }

    public String getConfirCorreoElec() {
        return ConfirCorreoElec;
    }

    public void setConfirCorreoElec(String ConfirCorreoElec) {
        this.ConfirCorreoElec = ConfirCorreoElec;
    }

    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String NombreEquipo) {
        this.NombreEquipo = NombreEquipo;
    }

    public String getColorUni() {
        return ColorUni;
    }

    public void setColorUni(String ColorUni) {
        this.ColorUni = ColorUni;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getEps() {
        return Eps;
    }

    public void setEps(String Eps) {
        this.Eps = Eps;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getIdCat() {
        return idCat;
    }

    public void setIdCat(String idCat) {
        this.idCat = idCat;
    }

    public String getIdRh() {
        return idRh;
    }

    public void setIdRh(String idRh) {
        this.idRh = idRh;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getIdEps() {
        return idEps;
    }

    public void setIdEps(String idEps) {
        this.idEps = idEps;
    }

   
    
    public BeanPreinscripcion() {
        
    }
    
}
