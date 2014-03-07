/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import metier.Evenement;

/**
 *
 * @author saliou
 */
@Local
public interface EvenementFacadeLocal {

    void create(Evenement evenement);

    void edit(Evenement evenement);

    void remove(Evenement evenement);

    Evenement find(Object id);

    List<Evenement> findAll();

    List<Evenement> findRange(int[] range);

    int count();

    Evenement findEvenementById(int id);

    Evenement findEvenementByLieuDate(String lieu, Date jour);
    
}
