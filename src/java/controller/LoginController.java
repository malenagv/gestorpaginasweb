/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.rpc.encoding.Serializer;

@Named 
@ViewScoped
/**
 *
 * @author AARON
 */
public class LoginController implements Serializable{
    private Usuario usuario;
    @EJB
    private UsuarioFacade EJBUsuario;
    @PostConstruct
    
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        Usuario us;
        String redireccion = null;
        try {
             us =  EJBUsuario.iniciarSesion(usuario);
               if(us!= null){
                   //almacenar sesion JSF
                   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",us);
                    redireccion = "/index?faces-redirect=true";
               }else{
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectos"));
               }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR.toString()));
        }
        return redireccion;
    }
            
}
