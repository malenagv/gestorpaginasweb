/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Administrador;
import entity.Communitymanager;
import entity.Persona;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author AARON
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Gestor_Community_Manager_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isAdministradorEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.administrador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Administrador findAdministrador(Usuario entity) {
        return this.getMergedEntity(entity).getAdministrador();
    }

    public boolean isCommunitymanagerEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.communitymanager)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Communitymanager findCommunitymanager(Usuario entity) {
        return this.getMergedEntity(entity).getCommunitymanager();
    }

    public boolean isPersonaEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.persona)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Persona findPersona(Usuario entity) {
        return this.getMergedEntity(entity).getPersona();
    }
    
         public Usuario iniciarSesion(Usuario us){
        Usuario usuario =null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.nombreUsuario = ?1 and u.contrasena = ?2";
            
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getNombreUsuario());
            query.setParameter(2, us.getContrasena());
            List<Usuario> lista = query.getResultList();
            if(!lista.isEmpty()){
                usuario = lista.get(0);
            }
                
        } catch (Exception e) {
            throw e;
        }finally{
            //em.close();
        }
            
        return usuario;
    }
    
}
