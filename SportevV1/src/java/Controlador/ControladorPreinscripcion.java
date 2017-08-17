/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCategoria;
import Modelo.Bean.BeanGenero;
import Modelo.Bean.BeanPreinscripcion;
import Modelo.Bean.BeanRH;
import Modelo.Dao.DaoCategoria;
import Modelo.Dao.DaoGenero;
import Modelo.Dao.DaoPreinscripcion;
import Modelo.Dao.DaoRh;
import Modelo.Dao.DaoUsuarioLogin;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@SessionScoped
public class ControladorPreinscripcion {

    //hola mundo
    private BeanPreinscripcion preinscripcion = new BeanPreinscripcion();
    private BeanGenero genero = new BeanGenero();
    private List<BeanPreinscripcion> consPreins;
    public String Respuesta;
   
    
   
    
    public List<SelectItem> consultarGenero(){
    DaoGenero DGen = new DaoGenero();
    List<BeanGenero> listar;
    List<SelectItem> listarSI = new ArrayList();
    listar = DGen.listarGenero();
     listar.stream().forEach((s) -> {
      listarSI.add(new SelectItem(s.getIdGenero(),s.getGenero()));
    });
    return listarSI;
    }
    
    public List<SelectItem> consultarCat(){
    DaoCategoria DCat = new DaoCategoria();
    List<BeanCategoria> listar;
    List<SelectItem> listarSI = new ArrayList();
    listar = DCat.listarCat();
     listar.stream().forEach((s) -> {
      listarSI.add(new SelectItem(s.getIdCategoria(),s.getNombre()));
    });
    return listarSI;
    }
    public List<SelectItem> consultarRh(){
        DaoRh DRh = new DaoRh();
    List<BeanRH> listar;
    List<SelectItem> listarSI = new ArrayList();
    listar = DRh.listarRH();
     listar.stream().forEach((s) -> {
      listarSI.add(new SelectItem(s.getIdRH(),s.getNombreRh()));
    });
    return listarSI;
    }
    
    /*public List<SelectItem> consultarEps(){
    DaoEps DEps = new DaoEps();
    List<BeanEps> listar;
    List<SelectItem> listarSI = new ArrayList();
    listar = DPreinscripcion.listarEps();
     listar.stream().forEach((s) -> {
      listarSI.add(new SelectItem(s.getIdeps(),s.getNombreEps()));
    });
    return listarSI;
    }
    */
    public String preinscribir(){
        DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
        if (DPreinscripcion.preinscripcion(this.preinscripcion)) {
            Respuesta = "index.xhtml";
        }else{
            Respuesta = "Error.xhtml";
        }
        return Respuesta;
    }
    

    public BeanGenero getGenero() {
        return genero;
    }

    public void setGenero(BeanGenero genero) {
        this.genero = genero;
    }

    public List<BeanPreinscripcion> getConsPreins() {
        return consPreins;
    }

    public void setConsPreins(List<BeanPreinscripcion> consPreins) {
        this.consPreins = consPreins;
    }
    
    public BeanPreinscripcion getPreinscripcion() {
        return preinscripcion;
    }

    public void setPreinscripcion(BeanPreinscripcion preinscripcion) {
        this.preinscripcion = preinscripcion;
    }
    
    public String inscribir(String usu, String Correo, int id, int idEqui){
    DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
    DaoUsuarioLogin DLog = new DaoUsuarioLogin();
    String usuEnd = "Entrenador"+usu;
    String Pass = "3ntr3"+usu+"5port3v";
        if (DPreinscripcion.inscribir(usuEnd, Pass, Correo, id, idEqui)) {
          Respuesta = "index.xhtml";
        }else{
            Respuesta = "Error.xhtml";
        }
        return Respuesta;

    }
    public void consultarSegCamp(int id){
    DaoPreinscripcion DPreinscripcion = new DaoPreinscripcion();
        consPreins = DPreinscripcion.listar(id);
    }
    public ControladorPreinscripcion() {
        
    }

   
    
}
