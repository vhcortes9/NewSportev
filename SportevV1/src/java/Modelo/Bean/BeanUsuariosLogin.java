/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Bean;

import com.sun.mail.handlers.image_jpeg;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import org.primefaces.model.UploadedFile;

/**
 *
 * @author Capera
 */
@ManagedBean
@RequestScoped
public class BeanUsuariosLogin {

    /**
     * Creates a new instance of BeanUsuariosLogin
     */
    private int idUsuario;
    private int idparticipante;
    private int rol;
    private String nombreRol;
    private String usuarioNombre;
    private String contrasenia;
    private String nombrecampeonato;
    private String nombreequipo;
    private String imagen;
    private String Estado;
   

    public BeanUsuariosLogin(String nombreRol, String usuarioNombre) {
        this.nombreRol = nombreRol;
        this.usuarioNombre = usuarioNombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(int idparticipante) {
        this.idparticipante = idparticipante;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    
    public BeanUsuariosLogin() {
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombrecampeonato() {
        return nombrecampeonato;
    }

    public void setNombrecampeonato(String nombrecampeonato) {
        this.nombrecampeonato = nombrecampeonato;
    }

    public String getNombreequipo() {
        return nombreequipo;
    }

    public void setNombreequipo(String nombreequipo) {
        this.nombreequipo = nombreequipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    

    
}
