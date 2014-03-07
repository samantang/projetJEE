/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import metier.Personne;

/**
 *
 * @author saliou
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> implements PersonneFacadeLocal, PersonneFacadeRemote {

    @PersistenceContext(unitName = "ProjetJEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }

    @Override
    public Personne findPersonneById(int id) {
        try {
            return (Personne) em.createNamedQuery("Personne.findByIdpersonne").setParameter("idpersonne", id).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Personne findPersonneByEmail(String email) {
        try {
            return (Personne) em.createNamedQuery("Personne.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Personne findPersonneByLoginMdp(String login, String mdp) {
        try {
            Query q = em.createQuery("SELECT p FROM Personne p WHERE p.login = :login AND p.motDePasse = :motDePasse");
            q.setParameter("login", login);
            q.setParameter("motDePasse", mdp);
            return (Personne) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    } 
}
