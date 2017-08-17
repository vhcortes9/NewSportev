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
public class BeanTipoDoc {
    private int IdTipoDoc;
    private String tipoDoc;

    public BeanTipoDoc(int IdTipoDoc, String tipoDoc) {
        this.IdTipoDoc = IdTipoDoc;
        this.tipoDoc = tipoDoc;
    }

    public int getIdTipoDoc() {
        return IdTipoDoc;
    }

    public void setIdTipoDoc(int IdTipoDoc) {
        this.IdTipoDoc = IdTipoDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    
    
    /**
     * Creates a new instance of BeanTipoDoc
     */
    public BeanTipoDoc() {
    }
    
}
