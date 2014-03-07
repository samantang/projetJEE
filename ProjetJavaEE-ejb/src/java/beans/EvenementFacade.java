/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import metier.Evenement;

/**
 *
 * @author saliou
 */
@Stateless
public class EvenementFacade extends AbstractFacade<Evenement> implements EvenementFacadeLocal, EvenementFacadeRemote {

    @PersistenceContext(unitName = "ProjetJEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvenementFacade() {
        super(Evenement.class);
    }

    @Override
    public Evenement findEvenementById(int id) {
        try {
            return (Evenement) em.createNamedQuery("Evenement.findByIdevenement").setParameter("idevenement", id).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Evenement findEvenementByLieuDate(String lieu, Date jour) {
        try {
            Query q = em.createQuery("SELECT e FROM Evenement e WHERE e.lieu = :lieu AND e.jour = :jour");
            return (Evenement) q.setParameter("lieu", lieu).setParameter("jour", jour).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
