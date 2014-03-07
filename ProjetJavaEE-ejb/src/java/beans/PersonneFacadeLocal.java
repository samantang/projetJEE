/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import metier.Personne;

/**
 *
 * @author saliou
 */
@Local
public interface PersonneFacadeLocal {

    void create(Personne personne);

    void edit(Personne personne);

    void remove(Personne personne);

    Personne find(Object id);

    List<Personne> findAll();

    List<Personne> findRange(int[] range);

    int count();

    Personne findPersonneById(int id);

    Personne findPersonneByEmail(String email);

    Personne findPersonneByLoginMdp(String login, String mdp);
}
