/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Suscripcion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Suscripcion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Communitymanager;
import entity.Plan;

/**
 *
 * @author AARON
 */
@Stateless
public class SuscripcionFacade extends AbstractFacade<Suscripcion> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuscripcionFacade() {
        super(Suscripcion.class);
    }

    public boolean isIdPersonaEmpty(Suscripcion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Suscripcion> suscripcion = cq.from(Suscripcion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(suscripcion, entity), cb.isNotNull(suscripcion.get(Suscripcion_.idPersona)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Communitymanager findIdPersona(Suscripcion entity) {
        return this.getMergedEntity(entity).getIdPersona();
    }

    public boolean isIdPlanEmpty(Suscripcion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Suscripcion> suscripcion = cq.from(Suscripcion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(suscripcion, entity), cb.isNotNull(suscripcion.get(Suscripcion_.idPlan)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Plan findIdPlan(Suscripcion entity) {
        return this.getMergedEntity(entity).getIdPlan();
    }
    
}
