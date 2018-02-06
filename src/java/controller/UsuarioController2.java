package controller;

import entity.Administrador;
import entity.Communitymanager;
import entity.Usuario;
import entity.Persona;
import facade.AdministradorFacade;
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
public class UsuarioController2 implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioEJB;
    @EJB
    private PersonaFacade personaEJB;
    @EJB
    private AdministradorFacade adminEJB;
    
    private Usuario usuario;
    private Persona persona;
    private Administrador admin;


  @PostConstruct
  public void init(){
      usuario = new Usuario();
      persona = new Persona();
      admin = new Administrador ();
      
  }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
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
           this.admin.setUsuario(usuario);
//           this.usuario.setIdUsuario(persona.getIdPersona());
//            usuarioEJB.create(usuario);
              personaEJB.create(persona);
              this.usuario.setIdUsuario(persona.getIdPersona());
              usuarioEJB.create(usuario);
              this.admin.setIdAS(usuario.getIdUsuario());
              adminEJB.create(admin);
        } catch (Exception e) {
        }
            
            
    }
}