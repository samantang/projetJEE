/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import beans.PersonneFacadeLocal;
import beans.PersonneFacadeRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import metier.Personne;

/**
 *
 * @author saliou
 */
@WebService(serviceName = "WSPersonne")
public class WSPersonne implements PersonneFacadeLocal, PersonneFacadeRemote{
    @EJB
    private PersonneFacadeLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "personne") Personne personne) {
        ejbRef.create(personne);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "personne") Personne personne) {
        ejbRef.edit(personne);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "personne") Personne personne) {
        ejbRef.remove(personne);
    }

    @WebMethod(operationName = "find")
    public Personne find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Personne> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Personne> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "findPersonneById")
    public Personne findPersonneById(@WebParam(name = "id") int id) {
        return ejbRef.findPersonneById(id);
    }

    @WebMethod(operationName = "findPersonneByEmail")
    public Personne findPersonneByEmail(@WebParam(name = "email") String email) {
        return ejbRef.findPersonneByEmail(email);
    }

    @WebMethod(operationName = "findPersonneByLoginMdp")
    public Personne findPersonneByLoginMdp(@WebParam(name = "login") String login, @WebParam(name = "mdp") String mdp) {
        return ejbRef.findPersonneByLoginMdp(login, mdp);
    }
    
}
