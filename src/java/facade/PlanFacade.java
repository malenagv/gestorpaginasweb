/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Plan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Plan_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Suscripcion;
import java.util.List;

/**
 *
 * @author AARON
 */
@Stateless
public class PlanFacade extends AbstractFacade<Plan> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanFacade() {
        super(Plan.class);
    }

    public boolean isSuscripcionListEmpty(Plan entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Plan> plan = cq.from(Plan.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(plan, entity), cb.isNotEmpty(plan.get(Plan_.suscripcionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Suscripcion> findSuscripcionList(Plan entity) {
        Plan mergedEntity = this.getMergedEntity(entity);
        List<Suscripcion> suscripcionList = mergedEntity.getSuscripcionList();
        suscripcionList.size();
        return suscripcionList;
    }
    
}
