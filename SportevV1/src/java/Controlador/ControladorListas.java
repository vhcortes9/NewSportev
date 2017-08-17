/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bean.BeanCategoria;
import Modelo.Bean.BeanEps;
import Modelo.Bean.BeanGenero;
import Modelo.Bean.BeanRH;
import Modelo.Bean.BeanTipoDoc;
import Modelo.Dao.DaoCategoria;
import Modelo.Dao.DaoEps;
import Modelo.Dao.DaoGenero;
import Modelo.Dao.DaoRh;
import Modelo.Dao.DaoTipoDoc;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author victorhugo
 */
@ManagedBean
@RequestScoped
public class ControladorListas {

     public List<SelectItem> consultarCat() {
        DaoCategoria Dcat = new DaoCategoria();
        List<BeanCategoria> listar;
        List<SelectItem> listarSI = new ArrayList();
        listar = Dcat.listarCat();
        listar.stream().forEach((s) -> {
        listarSI.add(new SelectItem(s.getIdCategoria(), s.getNombre()));
        });
        return listarSI;
    }
     public List<SelectItem> consultarGenero(){
         DaoGenero DGen = new DaoGenero();
         List<SelectItem> listarSI = new ArrayList();
         List<BeanGenero> listar;
         listar = DGen.listarGenero();
         listar.stream().forEach((ConGen) -> {
         listarSI.add(new SelectItem(ConGen.getIdGenero(), ConGen.getGenero()));
         });
         return listarSI;
     }
     
     public List<SelectItem> consultarTipoDoc(){
         DaoTipoDoc DTD = new DaoTipoDoc();
         List<BeanTipoDoc> listar;
         List<SelectItem> listarSI = new ArrayList();
         listar = DTD.listarTipDoc();
         listar.stream().forEach((ConsTipDoc) -> {
         listarSI.add(new SelectItem(ConsTipDoc.getIdTipoDoc(), ConsTipDoc.getTipoDoc()));
         });
         return listarSI;
     }
     
     public List<SelectItem> consRh(){
         DaoRh DRh = new DaoRh();
         List<SelectItem> listarSI = new ArrayList();
         List<BeanRH> listar;
         listar = DRh.listarRH();
         listar.stream().forEach((consRh) -> {
         listarSI.add(new SelectItem(consRh.getIdRH(), consRh.getNombreRh()));
         });
         return listarSI;
     }
      public List<SelectItem> consEps(){
         DaoEps DRh = new DaoEps();
         List<SelectItem> listarSI = new ArrayList();
         List<BeanEps> listar;
         listar = DRh.listarEps();
         listar.stream().forEach((consEps) -> {
         listarSI.add(new SelectItem(consEps.getIdeps(), consEps.getNombreEps()));
         });
         return listarSI;
     }
    public ControladorListas() {
    }
    
}
