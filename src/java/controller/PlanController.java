package controller;

import entity.Plan;
import entity.Suscripcion;
import java.util.List;
import facade.PlanFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "planController")
@ViewScoped
public class PlanController extends AbstractController<Plan> {

    // Flags to indicate if child collections are empty
    private boolean isSuscripcionListEmpty;

    public PlanController() {
        // Inform the Abstract parent controller of the concrete Plan Entity
        super(Plan.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSuscripcionListEmpty();
    }

    public boolean getIsSuscripcionListEmpty() {
        return this.isSuscripcionListEmpty;
    }

    private void setIsSuscripcionListEmpty() {
        Plan selected = this.getSelected();
        if (selected != null) {
            PlanFacade ejbFacade = (PlanFacade) this.getFacade();
            this.isSuscripcionListEmpty = ejbFacade.isSuscripcionListEmpty(selected);
        } else {
            this.isSuscripcionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Suscripcion entities that
     * are retrieved from Plan and returns the navigation outcome.
     *
     * @return navigation outcome for Suscripcion page
     */
    public String navigateSuscripcionList() {
        Plan selected = this.getSelected();
        if (selected != null) {
            PlanFacade ejbFacade = (PlanFacade) this.getFacade();
            List<Suscripcion> selectedSuscripcionList = ejbFacade.findSuscripcionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Suscripcion_items", selectedSuscripcionList);
        }
        return "/app/suscripcion/index";
    }

}
