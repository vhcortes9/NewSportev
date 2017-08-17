/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@SessionScoped
public class ControladorForms {
    private String Seleccion;

    public void cambiarForm(ActionEvent e){
        Seleccion = e.getComponent().getId();
    }
    public ControladorForms() {

    }
    public String getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(String Seleccion) {
        this.Seleccion = Seleccion;
    
}
    public void xxx(){
        this.Seleccion="formCampeonatos";
    }
}
