/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import metier.Carriere;

/**
 *
 * @author saliou
 */
@Local
public interface CarriereFacadeLocal {

    void create(Carriere carriere);

    void edit(Carriere carriere);

    void remove(Carriere carriere);

    Carriere find(Object id);

    List<Carriere> findAll();

    List<Carriere> findRange(int[] range);

    int count();

    Carriere findCarriereById(int id);
    
}
