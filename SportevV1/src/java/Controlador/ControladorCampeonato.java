
package Controlador;

import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanCategoria;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoCampeonato;
import Modelo.Dao.DaoCategoria;
import Modelo.Dao.DaoPreinscripcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyleConstants;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class ControladorCampeonato {

    /**
     * Creates a new instance of ControladorCampeonato
     */
    private String nombre;
    private BeanCampeonato BCamp = new BeanCampeonato();
    private List<BeanCampeonato> consCamp;
    private BeanUsuariosLogin usuario;
    private boolean activo = false;
    private String Seleccion;

    public String respuesta = "";
    public String emviarPerfil()  {
        if (true) {
            respuesta = "/perfil.xhtml";
        }
        return respuesta;
    }


    public void attrListener(ActionEvent event){
	}


    public void registrarCampeonato() throws NamingException, SQLException {
        DaoCampeonato DCamp = new DaoCampeonato();
        DCamp.registrarCampeonato(this.BCamp);
            llenarDatos();
    }

    public void eliminar() throws NamingException, SQLException {
        DaoCampeonato DCamp = new DaoCampeonato();
        DCamp.eliminar(this.BCamp);
        llenarDatos();
    }

    public void editar(int id) throws NamingException, SQLException {
        DaoCampeonato DCampeonato = new DaoCampeonato();
        List<BeanCampeonato> listar;
        listar = DCampeonato.consultar(id);
        for (BeanCampeonato BCampeonato : listar) {
            BCamp = BCampeonato;
        }

    }
     public void prueba() throws NamingException, SQLException{
     registrarCampeonato();
     llenarDatos();
     }
    public void modificar() throws NamingException, SQLException{
        DaoCampeonato DCamp = new DaoCampeonato();
        System.out.println(BCamp.getIdCampeonato());
        DCamp.modificar(this.BCamp);
        llenarDatos();
          
    }

    public void llenarDatos(){
    try {
            DaoCampeonato DCampeonato = new DaoCampeonato();
            consCamp = DCampeonato.listarCampeonato();

        } catch (Exception e) {
        }
    }
    public ControladorCampeonato() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        try {
            DaoCampeonato DCampeonato = new DaoCampeonato();
            consCamp = DCampeonato.listarCampeonato();

        } catch (Exception e) {
        }
    }

    public String getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(String Seleccion) {
        this.Seleccion = Seleccion;
    }

    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public BeanCampeonato getBCamp() {
        return BCamp;
    }

    public List<BeanCampeonato> getConsCamp() {
        return consCamp;
    }

    public void setConsCamp(List<BeanCampeonato> consCamp) {
        this.consCamp = consCamp;
    }

    public void setBCamp(BeanCampeonato BCamp) {
        this.BCamp = BCamp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

}
