package controller;

import entity.Suscripcion;
import facade.SuscripcionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "suscripcionController")
@ViewScoped
public class SuscripcionController extends AbstractController<Suscripcion> {

    @Inject
    private CommunitymanagerController idPersonaController;
    @Inject
    private PlanController idPlanController;

    public SuscripcionController() {
        // Inform the Abstract parent controller of the concrete Suscripcion Entity
        super(Suscripcion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPersonaController.setSelected(null);
        idPlanController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Communitymanager controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPersona(ActionEvent event) {
        Suscripcion selected = this.getSelected();
        if (selected != null && idPersonaController.getSelected() == null) {
            idPersonaController.setSelected(selected.getIdPersona());
        }
    }

    /**
     * Sets the "selected" attribute of the Plan controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPlan(ActionEvent event) {
        Suscripcion selected = this.getSelected();
        if (selected != null && idPlanController.getSelected() == null) {
            idPlanController.setSelected(selected.getIdPlan());
        }
    }

}
