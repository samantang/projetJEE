/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import metier.Carriere;

/**
 *
 * @author saliou
 */
@Stateless
public class CarriereFacade extends AbstractFacade<Carriere> implements CarriereFacadeLocal, CarriereFacadeRemote {

    @PersistenceContext(unitName = "ProjetJEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarriereFacade() {
        super(Carriere.class);
    }

    @Override
    public Carriere findCarriereById(int id) {
        try{
        return (Carriere) em.createNamedQuery("Carriere.findByIdcarriere").setParameter("idcarriere", id).getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }
}
