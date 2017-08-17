/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.Daoperfil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author andre
 */
@ManagedBean
@RequestScoped
public class ControladorPerfil {

    /**
     * Creates a new instance of LoginAppBean
     */
    private BeanUsuariosLogin nombre;
    private BeanUsuariosLogin imagen;
    private BeanUsuariosLogin contraseña;
    private BeanUsuariosLogin usuario;
    private BeanDatosPersona usuar;
    private BeanDatosPersona mostrarperfil;
    private List<BeanDatosPersona> personas;
    private BeanDatosPersona personaselecionada;
    private BeanDatosPersona administradoreditar;
    private BeanDatosPersona usuarioPerfil;

    private Part fotoguardar;

    /**
     * Creates a new instance of LoginAppBean
     */
    private Part file;
    private String nomb;
    private String pathReal;

    private int id;
    private String opcion;

    @EJB

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getPath() {
        return pathReal;
    }

    public void setPath(String path) {
        this.pathReal = path;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Integer getDocumento() {
        return id;
    }

    public void setDocumento(Integer id) {
        this.id = id;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public void upload(int i) throws IOException {
        this.id = i;

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Imagenes");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Imagenes\\";

        String retorno = "";
        try {
            this.nomb = file.getSubmittedFileName();
            pathReal = "/UploadFile/imagenes/" + nomb;

            if (this.nomb.substring(this.nomb.length() - 3).equals("jpg")) {
                //path = path + this.nombre;
                path = path + file.getSubmittedFileName();
                InputStream in = file.getInputStream();

                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path));
                out.write(data);
                in.close();
                out.close();
                String a = file.getSubmittedFileName();
                Daoperfil Dperfil = new Daoperfil();
                if (Dperfil.registrarimagen(i, a)) {
                    Daoperfil Dper = new Daoperfil();
                    FacesMessage mensaje = new FacesMessage("Modificacion Exitosa");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    imagen = Dper.verimagen();
                } else {
                    Daoperfil Dper = new Daoperfil();
                    FacesMessage mensaje = new FacesMessage("Modificacion Erronea");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    imagen = Dper.verimagen();
                }

            } else if (this.nomb.substring(this.nomb.length() - 3).equals("png")) {
                //path = path + this.nombre;
                path = path + file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                byte[] data = new byte[in.available()];
                in.read(data);
                FileOutputStream out = new FileOutputStream(new File(path));
                out.write(data);
                in.close();
                out.close();
                String a = file.getSubmittedFileName();
                Daoperfil Dperfil = new Daoperfil();
                if (Dperfil.registrarimagen(i, a)) {
                    Daoperfil Dper = new Daoperfil();
                    FacesMessage mensaje = new FacesMessage("Modificacion Exitosa");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    imagen = Dper.verimagen();
                } else {
                    Daoperfil Dper = new Daoperfil();
                    FacesMessage mensaje = new FacesMessage("Modificacion Erronea");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    imagen = Dper.verimagen();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ControladorPerfil() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        usuarioPerfil = new BeanDatosPersona();
        contraseña = new BeanUsuariosLogin();
        nombre = new BeanUsuariosLogin();
        personaselecionada = new BeanDatosPersona();
        imagen = new BeanUsuariosLogin();

        try {

            Daoperfil dp = new Daoperfil();
            personas = dp.listaPersonas();
            imagen = dp.verimagen();
            usuar = dp.listaPersona();

        } catch (Exception e) {
        }

    }

    public void asignareditar(BeanDatosPersona persona) {
        this.personaselecionada = persona;
    }

    public void asignareditarAdmin(BeanDatosPersona administrador) {
        this.administradoreditar = administrador;
    }

    public void desabilitarUsuario(int id) {
        Daoperfil daperfil = new Daoperfil();
        if (daperfil.desabilitarUsuario(id)) {
            FacesMessage mensaje = new FacesMessage("Usuario Desabilitado");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage("Error");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
    public void habilitarUsuario(int id) {
        Daoperfil daperfil = new Daoperfil();
        if (daperfil.habilitarUsuario(id)) {
            FacesMessage mensaje = new FacesMessage("Usuario Habilitado");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage("Usuario Error");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    public String modificarcontraseña() {
        String retorno = "";
        if (usuarioPerfil.getContraseña().equals(usuario.getContrasenia())) {

            if (contraseña.getContrasenia().equals(imagen.getContrasenia())) {
                Daoperfil per = new Daoperfil();
                if (per.modificarcontraseña(this.contraseña)) {
                    FacesMessage mensaje = new FacesMessage("Datos validos");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);

                    retorno = "ModificarPerfil";
                } else {
                    FacesMessage mensaje = new FacesMessage("Datos Invalidos");
                    mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    retorno = "ModificarPerfil";
                }
            } else {
                retorno = "ModificarPerfil";
            }
        } else {
            FacesMessage mensaje = new FacesMessage("Contraseña Incorrecta");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            retorno = "ModificarPerfil";

        }

        return retorno;
    }

    public String modificarnombre() {
        String retorno = "";
        Daoperfil per = new Daoperfil();
        if (per.modificarnombre(this.nombre)) {
            FacesMessage mensaje = new FacesMessage("Datos validos");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            retorno = "ModificarPerfil";

        } else {
            FacesMessage mensaje = new FacesMessage("Datos Invalidos");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            retorno = "ModificarPerfil";
        }
        return retorno;
    }

    public void modificarpersona() {
        Daoperfil per = new Daoperfil();
        if (per.modificarpersona(this.personaselecionada)) {
            FacesMessage mensaje = new FacesMessage("Modificacion Exitosa");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            Daoperfil pers = new Daoperfil();
            personas = pers.listaPersonas();

        } else {
            FacesMessage mensaje = new FacesMessage("Error De Moficacion");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }

    }

    public void modificarAdministrador() {
        Daoperfil per = new Daoperfil();
        if (per.modificarpersona(this.administradoreditar)) {
            FacesMessage mensaje = new FacesMessage("Modificacion Exitosa");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            Daoperfil pers = new Daoperfil();
            usuar = pers.listaPersona();

        } else {
            FacesMessage mensaje = new FacesMessage("Error De Moficacion");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }

    }

    public String validarPerfil() {
        String retorno = "";
        if (usuarioPerfil.getContraseña().equals(usuario.getContrasenia())) {
            retorno = "ModificarPerfil";
        } else {
            retorno = "Perfil";
        }
        return retorno;
    }

    public BeanDatosPersona getPersonaselecionada() {
        return personaselecionada;
    }

    public void setPersonaselecionada(BeanDatosPersona personaselecionada) {
        this.personaselecionada = personaselecionada;
    }

    public List<BeanDatosPersona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<BeanDatosPersona> personas) {
        this.personas = personas;
    }

    public BeanUsuariosLogin getNombre() {
        return nombre;
    }

    public void setNombre(BeanUsuariosLogin nombre) {
        this.nombre = nombre;
    }

    public BeanUsuariosLogin getContraseña() {
        return contraseña;
    }

    public void setContraseña(BeanUsuariosLogin contraseña) {
        this.contraseña = contraseña;
    }

    public BeanDatosPersona getUsuar() {
        return usuar;
    }

    public void setUsuar(BeanDatosPersona usuar) {
        this.usuar = usuar;
    }

    public BeanDatosPersona getMostrarperfil() {
        return mostrarperfil;
    }

    public void setMostrarperfil(BeanDatosPersona mostrarperfil) {
        this.mostrarperfil = mostrarperfil;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public BeanDatosPersona getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(BeanDatosPersona usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public BeanUsuariosLogin getImagen() {
        return imagen;
    }

    public void setImagen(BeanUsuariosLogin imagen) {
        this.imagen = imagen;
    }

    public BeanDatosPersona getAdministradoreditar() {
        return administradoreditar;
    }

    public void setAdministradoreditar(BeanDatosPersona administradoreditar) {
        this.administradoreditar = administradoreditar;
    }

}
