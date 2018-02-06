package controller;

import entity.Communitymanager;
import entity.Usuario;
import entity.Persona;
import entity.Suscripcion;
import facade.CommunitymanagerFacade;
import facade.PersonaFacade;
import facade.SuscripcionFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
//import facade.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
//import controller.util.MobilePageController;
import javax.inject.Named;

//import javax.faces.event.ActionEvent;
//import javax.inject.Inject;

@Named
@ViewScoped
public class SuscripcionController1 implements Serializable {
    
    @EJB
    private SuscripcionFacade suscripcionEJB;
    
    @EJB
    private CommunitymanagerFacade cmEJB;
   
    
    private Suscripcion suscripcion;
    private Communitymanager cm;

    public Communitymanager getCm() {
        return cm;
    }

    public void setCm(Communitymanager cm) {
        this.cm = cm;
    }


  @PostConstruct
  public void init(){
      suscripcion = new Suscripcion();
      
  }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

 
  
    public void registrar (){
        try {
//           this.suscr.setPersona(persona);
//           this.cm.setUsuario(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            Date d = new Date();
            d= new Date();
            d.setMonth(d.getMonth()+Integer.parseInt(suscripcion.getIdPlan().getDuracion()));
              this.suscripcion.setFecha(d);
              this.suscripcion.setIdPersona(us.getCommunitymanager());
              this.suscripcion.setMonto(this.suscripcion.getIdPlan().getPrecio());
             
              this.setCm(us.getCommunitymanager());
              SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
              this.cm.setMembresia(formatter.format(suscripcion.getFecha()));
//              cmEJB.edit(cm);
              suscripcionEJB.create(suscripcion);
              cmEJB.edit(cm);
              
//              this.usuario.setIdUsuario(persona.getIdPersona());
//              usuarioEJB.create(usuario);
//              this.cm.setIdCM(usuario.getIdUsuario());
//              cmEJB.create(cm);
        } catch (Exception e) {
        }
            
            
    }
}
