package controller;

import entity.Communitymanager;
import entity.Usuario;
import entity.Persona;
import facade.CommunitymanagerFacade;
import facade.PersonaFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
//import facade.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
//import controller.util.MobilePageController;
import javax.inject.Named;

//import javax.faces.event.ActionEvent;
//import javax.inject.Inject;

@Named
@ViewScoped
public class UsuarioController1 implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioEJB;
    @EJB
    private PersonaFacade personaEJB;
    @EJB
    private CommunitymanagerFacade cmEJB;
    
    private Usuario usuario;
    private Persona persona;
    private Communitymanager cm;


  @PostConstruct
  public void init(){
      usuario = new Usuario();
      persona = new Persona();
      cm = new Communitymanager ();
      
  }

    public Communitymanager getCm() {
        return cm;
    }

    public void setCm(Communitymanager cm) {
        this.cm = cm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
  
    public void registrar (){
        try {
           this.usuario.setPersona(persona);
           this.cm.setUsuario(usuario);
//           this.usuario.setIdUsuario(persona.getIdPersona());
//            usuarioEJB.create(usuario);
              personaEJB.create(persona);
              this.usuario.setIdUsuario(persona.getIdPersona());
              usuarioEJB.create(usuario);
              this.cm.setIdCM(usuario.getIdUsuario());
              cmEJB.create(cm);
        } catch (Exception e) {
        }
            
            
    }
}
