package controller;

import entity.Usuario;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private AdministradorController administradorController;
    @Inject
    private CommunitymanagerController communitymanagerController;
    @Inject
    private PersonaController personaController;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        administradorController.setSelected(null);
        communitymanagerController.setSelected(null);
        personaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Administrador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAdministrador(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && administradorController.getSelected() == null) {
            administradorController.setSelected(selected.getAdministrador());
        }
    }

    /**
     * Sets the "selected" attribute of the Communitymanager controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCommunitymanager(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && communitymanagerController.getSelected() == null) {
            communitymanagerController.setSelected(selected.getCommunitymanager());
        }
    }

    /**
     * Sets the "selected" attribute of the Persona controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePersona(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && personaController.getSelected() == null) {
            personaController.setSelected(selected.getPersona());
        }
    }

}
