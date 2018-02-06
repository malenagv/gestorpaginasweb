package controller;

import entity.Communitymanager;
import entity.Suscripcion;
import java.util.List;
import facade.CommunitymanagerFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "communitymanagerController")
@ViewScoped
public class CommunitymanagerController extends AbstractController<Communitymanager> {

    @Inject
    private UsuarioController usuarioController;

    // Flags to indicate if child collections are empty
    private boolean isSuscripcionListEmpty;

    public CommunitymanagerController() {
        // Inform the Abstract parent controller of the concrete Communitymanager Entity
        super(Communitymanager.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSuscripcionListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuario(ActionEvent event) {
        Communitymanager selected = this.getSelected();
        if (selected != null && usuarioController.getSelected() == null) {
            usuarioController.setSelected(selected.getUsuario());
        }
    }

    public boolean getIsSuscripcionListEmpty() {
        return this.isSuscripcionListEmpty;
    }

    private void setIsSuscripcionListEmpty() {
        Communitymanager selected = this.getSelected();
        if (selected != null) {
            CommunitymanagerFacade ejbFacade = (CommunitymanagerFacade) this.getFacade();
            this.isSuscripcionListEmpty = ejbFacade.isSuscripcionListEmpty(selected);
        } else {
            this.isSuscripcionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Suscripcion entities that
     * are retrieved from Communitymanager and returns the navigation outcome.
     *
     * @return navigation outcome for Suscripcion page
     */
    public String navigateSuscripcionList() {
        Communitymanager selected = this.getSelected();
        if (selected != null) {
            CommunitymanagerFacade ejbFacade = (CommunitymanagerFacade) this.getFacade();
            List<Suscripcion> selectedSuscripcionList = ejbFacade.findSuscripcionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Suscripcion_items", selectedSuscripcionList);
        }
        return "/app/suscripcion/index";
    }

}
