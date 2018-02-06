package controller;

import entity.Administrador;
import facade.AdministradorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "administradorController")
@ViewScoped
public class AdministradorController extends AbstractController<Administrador> {

    @Inject
    private UsuarioController usuarioController;

    public AdministradorController() {
        // Inform the Abstract parent controller of the concrete Administrador Entity
        super(Administrador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuario(ActionEvent event) {
        Administrador selected = this.getSelected();
        if (selected != null && usuarioController.getSelected() == null) {
            usuarioController.setSelected(selected.getUsuario());
        }
    }

}
