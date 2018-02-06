/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Administrador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Administrador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuario;

/**
 *
 * @author AARON
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }

    public boolean isUsuarioEmpty(Administrador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Administrador> administrador = cq.from(Administrador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administrador, entity), cb.isNotNull(administrador.get(Administrador_.usuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuario(Administrador entity) {
        return this.getMergedEntity(entity).getUsuario();
    }
    
}
