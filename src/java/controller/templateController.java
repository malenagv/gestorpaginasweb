/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Communitymanager;
import entity.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * 
 */
@Named
@ViewScoped
public class templateController implements Serializable {
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(us == null){
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No tienes permisos para acceder"));
           // context.getExternalContext().redirect("./../permisos.xhtml");
           context.getExternalContext().redirect("http://localhost:8080/Gestor_Community_Manager/faces/permisos.xhtml");
        }
        
        } catch (Exception e) {
            //log para guardar registro error
        }
    }
    
    public boolean esAdmin(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(us.getCommunitymanager() == null){
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No tienes permisos para acceder"));
           // context.getExternalContext().redirect("./../permisos.xhtml");
            return true;
        }
        
        } catch (Exception e) {
            //log para guardar registro error
        }return false;
    }
    
     public boolean estaSuscrito(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            Date d = new Date();
            Date hoy = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            d= formatter.parse(us.getCommunitymanager().getMembresia());
            
        if(us.getCommunitymanager().getMembresia()!=null && !hoy.after(d)){
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No tienes permisos para acceder"));
           // context.getExternalContext().redirect("./../permisos.xhtml");
            return true;
        }
        
        } catch (Exception e) {
            //log para guardar registro error
        }return false;
    }
     
      public String fechaFin(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(us.getCommunitymanager().getMembresia()!=null){
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No tienes permisos para acceder"));
           // context.getExternalContext().redirect("./../permisos.xhtml");
            return "Estás suscrito hasta "+ us.getCommunitymanager().getMembresia();
        }
        
        } catch (Exception e) {
            //log para guardar registro error
        }return "No estás suscrito";
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public Communitymanager getCM(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(us.getCommunitymanager().getMembresia()!=null){
           //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No tienes permisos para acceder"));
           // context.getExternalContext().redirect("./../permisos.xhtml");
            return us.getCommunitymanager();
        }
        
        } catch (Exception e) {
            //log para guardar registro error
        }return null;
    }
}
