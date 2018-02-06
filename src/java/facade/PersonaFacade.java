/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Persona_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usuario;

/**
 *
 * @author AARON
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public boolean isUsuarioEmpty(Persona entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Persona> persona = cq.from(Persona.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotNull(persona.get(Persona_.usuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuario(Persona entity) {
        return this.getMergedEntity(entity).getUsuario();
    }
    
}
