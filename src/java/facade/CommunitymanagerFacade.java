/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Communitymanager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Communitymanager_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuario;
import entity.Suscripcion;
import java.util.List;

/**
 *
 * @author AARON
 */
@Stateless
public class CommunitymanagerFacade extends AbstractFacade<Communitymanager> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommunitymanagerFacade() {
        super(Communitymanager.class);
    }

    public boolean isUsuarioEmpty(Communitymanager entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Communitymanager> communitymanager = cq.from(Communitymanager.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(communitymanager, entity), cb.isNotNull(communitymanager.get(Communitymanager_.usuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuario(Communitymanager entity) {
        return this.getMergedEntity(entity).getUsuario();
    }

    public boolean isSuscripcionListEmpty(Communitymanager entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Communitymanager> communitymanager = cq.from(Communitymanager.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(communitymanager, entity), cb.isNotEmpty(communitymanager.get(Communitymanager_.suscripcionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Suscripcion> findSuscripcionList(Communitymanager entity) {
        Communitymanager mergedEntity = this.getMergedEntity(entity);
        List<Suscripcion> suscripcionList = mergedEntity.getSuscripcionList();
        suscripcionList.size();
        return suscripcionList;
    }
    
}
