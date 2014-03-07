/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.CarriereFacadeLocal;
import beans.PersonneFacadeLocal;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Carriere;
import metier.Personne;

/**
 *
 * @author saliou
 */
public class Personnes extends HttpServlet {

    @EJB
    private CarriereFacadeLocal carriereFacade;
    @EJB
    private PersonneFacadeLocal personneFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "personnes.jsp";
        request.setAttribute("message", "");
        String action = request.getParameter("action");
        //liste des annees d'inscription
        Collection<Integer> anneeInscription = new ArrayList<Integer>();
        for (int i = 2013; i > 1995; i--) {
            anneeInscription.add(i);
        }
        request.setAttribute("anneeInscription", anneeInscription);

        Collection<Personne> personnes;

        //gestion des requetes

        if (action.equals("test")) { // creation des personnes predefinies
            this.createPersonneTests();
            Collection<Personne> p = personneFacade.findAll();
            request.setAttribute("personnes", p);
        } else if (action.equals("tout")) { // Affichage de toutes les personnes de la filiaire cci
            Collection<Personne> p = personneFacade.findAll();
            request.setAttribute("personnes", p);

        } else if (action.equals("profil")) {
            Integer personneID = Integer.parseInt(request.getParameter("id"));
            Personne p = personneFacade.findPersonneById(personneID);
            request.getSession(true).setAttribute("utilisateur", p);
            page = "profil.jsp";
        } else if (action.equals("inscription")) { // Aller  à la page d'inscription 
            request.setAttribute("titre", "Inscription");
            request.setAttribute("personne", null);
            page = "enregistrement_personne.jsp";
        } else if (action.equals("modifierPersonne")) { // Aller à la page de modification d'un personne
            Integer personneID = Integer.parseInt(request.getParameter("modifId"));
            Personne p = personneFacade.findPersonneById(personneID);
            if (p != null) {
                request.setAttribute("modifId", personneID);
                request.setAttribute("titre", "Modifier");
                request.setAttribute("personne", p);
                page = "enregistrement_personne.jsp";
            } else {
                page = "erreur.jsp";
                request.setAttribute("message", "Aucune personne correspondante trouvée");
            }
        } else if (action.equals("supprimerPersonne")) { // Supprimer une personne
            if (!request.getParameter("supprId").equals("")) {
                Integer personneID = Integer.parseInt(request.getParameter("supprId"));
                Personne p = personneFacade.findPersonneById(personneID);
                if (p != null) {
                    personneFacade.remove(p);
                    for (Carriere c : p.getCarriereCollection()) {
                        carriereFacade.remove(c);
                    }
                    personnes = personneFacade.findAll();
                    request.setAttribute("personnes", personnes);
                    page = "personnes.jsp";
                } else {
                    page = "erreur.jsp";
                    request.setAttribute("message", "Aucune personne correspondante trouvée");
                }
            } else {
                personnes = personneFacade.findAll();
                request.setAttribute("personnes", personnes);
                page = "personnes.jsp";
            }
        } else if (action.equals("creerPersonne")) { // enregistrer une nouvelle personne et mofidier une personne
            Personne p;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateDeNaissance = sdf.parse((String) request.getParameter("dateDeNaissance"), new ParsePosition(0));
            if (request.getParameter("modifId").equals("")) { // Enregistrement

                if (personneFacade.findPersonneByEmail(request.getParameter("email")) != null) {
                    request.setAttribute("message", "L'email saisi existe déjà pour une autre personne");
                    page = "erreur.jsp";
                } else {
                    p = new Personne(request.getParameter("nom"), request.getParameter("prenom"),
                            Integer.parseInt(request.getParameter("annee_inscription")), request.getParameter("membre"),
                            request.getParameter("login"), request.getParameter("motDePasse"), request.getParameter("email"),
                            dateDeNaissance);
                    if (request.getParameter("dateDebut") != null) {
                        Date dateDebut = sdf.parse((String) request.getParameter("dateDebut"), new ParsePosition(0));
                        Date dateFin = sdf.parse((String) request.getParameter("dateFin"), new ParsePosition(0));
                        Carriere c = new Carriere(dateDebut, dateFin, request.getParameter("employeur"), request.getParameter("fonction"), p);
                        p.addCarriere(c);
                    }

                    //Creation de la nouvelle personne
                    personneFacade.create(p);
                    request.setAttribute("personne", p);
                    request.setAttribute("modifId", p.getIdpersonne());
                    request.setAttribute("message", "Enregistrement OK");
                    page = "enregistrement_personne.jsp";
                    // page = "enregistrement_ok.jsp";
                }
            } else { // Modification 
                Integer personneID = Integer.parseInt(request.getParameter("modifId"));
                p = personneFacade.findPersonneById(personneID);
                try {
                    p.setNom(request.getParameter("nom"));
                    p.setPrenom(request.getParameter("prenom"));
                    p.setLogin(request.getParameter("login"));
                    p.setAnneeInscription(Integer.parseInt(request.getParameter("annee_inscription")));
                    p.setEmail(request.getParameter("email"));
                    p.setMembre(request.getParameter("membre"));
                    p.setDateDeNaissance(dateDeNaissance);
                    p.setMotDePasse(request.getParameter("motDePasse"));

                    if (request.getParameter("dateDebut") != null) {
                        Date dateDebut = sdf.parse((String) request.getParameter("dateDebut"), new ParsePosition(0));
                        Date dateFin = sdf.parse((String) request.getParameter("dateFin"), new ParsePosition(0));
                        Carriere c = new Carriere(dateDebut, dateFin, request.getParameter("employeur"), request.getParameter("fonction"), p);
                        p.addCarriere(c);
                    }
                    personneFacade.edit(p);
                    request.setAttribute("personne", p);
                    request.setAttribute("modifId", personneID);
                    request.setAttribute("message", "Modification OK");
                    //page = "enregistrement_ok.jsp";
                    page = "enregistrement_personne.jsp";

                } catch (Exception ex) {
                    request.setAttribute("message", "Le modification de la personne n'a pas été effectuée");
                    page = "erreur.jsp";
                }
            }
        } else if (action.equals("supprimerCarriere")) {
            if (!request.getParameter("supprId").equals("")) {
                Integer carriereID = Integer.parseInt(request.getParameter("supprId"));
                //Rechercher la carriere
                Carriere c = carriereFacade.findCarriereById(carriereID);
                if (c != null) {
                    //Trouver la personne concernée pour supprimer la carriere courante
                    Personne p = c.getIdpersonne();
                    p.getCarriereCollection().remove(c);
                    //Mise a jour des carrieres de la personne
                    personneFacade.edit(p);
                    //Suppression de la carriere
                    carriereFacade.remove(c);
                    request.setAttribute("personne", p);
                    request.setAttribute("modifId", p.getIdpersonne());

                    page = "enregistrement_personne.jsp";
                }
            }
        }
        RequestDispatcher dp = request.getRequestDispatcher(page);
        dp.forward(request, response);

    }

    /**
     * Creation des comptes de test
     */
    private void createPersonneTests() {
        if (personneFacade.findPersonneByEmail("mamkaba2000@yahoo.fr") == null) {
            Personne p = new Personne("KABA", "Mamady", 2008, "OUI", "mkaba", "kaba", "mamkaba2000@yahoo.fr", new Date(1988 - 1900, 11, 8));
            Carriere c1 = new Carriere(new Date(2000 - 1900, 9, 8), new Date(2002 - 1900, 5, 10), "Orange", "Dévéloppeur", p);
            c1.setIdcarriere(1);
            p.addCarriere(c1);
            Carriere c2 = new Carriere(new Date(2002 - 1900, 6, 1), null, "Orange", "Dévéloppeur", p);
            p.addCarriere(c2);
            personneFacade.create(p); // 8-dec-1988
        }

        if (personneFacade.findPersonneByEmail("mamkaba2001@yahoo.fr") == null) {
            personneFacade.create(new Personne("KABA_2", "Mamady", 2008, "OUI", "mkaba2", "kaba2", "mamkaba2001@yahoo.fr", new Date(1987 - 1900, 11, 8)));
        }

        if (personneFacade.findPersonneByEmail("mamkaba2002@yahoo.fr") == null) {
            personneFacade.create(new Personne("KABA_3", "Mamady", 2008, "OUI", "mkaba3", "kaba3", "mamkaba2002@yahoo.fr", new Date(1986 - 1900, 11, 8)));
        }

        if (personneFacade.findPersonneByEmail("mamkaba2003@yahoo.fr") == null) {
            personneFacade.create(new Personne("KABA_4", "Mamady", 2008, "OUI", "mkaba4", "kaba4", "mamkaba2003@yahoo.fr", new Date(1985 - 1900, 11, 8)));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
