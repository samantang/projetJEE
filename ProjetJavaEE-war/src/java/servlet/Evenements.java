/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.EvenementFacadeLocal;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Evenement;

/**
 *
 * @author saliou
 */
public class Evenements extends HttpServlet {

    @EJB
    private EvenementFacadeLocal evenementFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        String page = "evenements.jsp";

        // Liste des heures
        Collection<Integer> heureEvenement = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            heureEvenement.add(i);
        }
        request.setAttribute("heureEvenement", heureEvenement);
        // Liste des heures
        Collection<Integer> minuteEvenement = new ArrayList<Integer>();
        for (int i = 0; i < 59; i++) {
            minuteEvenement.add(i);
        }
        request.setAttribute("minuteEvenement", minuteEvenement);
        Collection<Evenement> evenements;
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("test")) { // Créer des évènements tests
                creerEvenementTests();
                page = "gestion_evenements.jsp";
            } else if (action.equals("gerer")) {
                page = "gestion_evenements.jsp";
            } else if (action.equals("supprimer")) { // Supprimer un évènement
                if (!request.getParameter("supprId").equals("")) {
                    Integer evenementID = Integer.parseInt(request.getParameter("supprId"));
                    Evenement e = evenementFacade.findEvenementById(evenementID);
                    if (e != null) {
                        evenementFacade.remove(e);
                        page = "gestion_evenements.jsp";
                    } else {
                        page = "erreur.jsp";
                        request.setAttribute("message", "Aucun évènement correspondant trouvé");
                    }
                }
            } else if (action.equals("inscription")) {
                request.setAttribute("titre", "Ajouter un évènement");
                request.setAttribute("evenement", null);
                page = "enregistrement_evenement.jsp";
            } else if (action.equals("modifier")) { // Aller à la page de modification d'un évènement
                Integer evenementID = Integer.parseInt(request.getParameter("modifId"));
                Evenement e = evenementFacade.findEvenementById(evenementID);
                if (e != null) {
                    request.setAttribute("modifId", evenementID);
                    request.setAttribute("titre", "Modifier");
                    request.setAttribute("evenement", e);
                    page = "enregistrement_evenement.jsp";
                } else {
                    page = "erreur.jsp";
                    request.setAttribute("message", "Aucun évènement correspondant trouvé");
                }
            } else if (action.equals("creer")) {
                Evenement e;
                int heure = Integer.parseInt(request.getParameter("heure"));
                int minute = Integer.parseInt(request.getParameter("minute"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date d =  sdf.parse((String) request.getParameter("jour")+ " " +heure + ":" + minute, new ParsePosition(0));
                if (request.getParameter("modifId").equals("")) { // Enregistrement
                    if (evenementFacade.findEvenementByLieuDate(request.getParameter("lieu"), d) != null) {
                        request.setAttribute("message", "Un évènement existe le même jour au lieu indiqué");
                        page = "erreur.jsp";
                    } else {
                        e = new Evenement(d, request.getParameter("titre"), request.getParameter("description"), request.getParameter("lieu"));
                        evenementFacade.create(e);
                        request.setAttribute("message", "Enregistrement OK");
                        page = "enregistrement_ok.jsp";
                    }
                } else { // Modification
                    Integer evenementID = Integer.parseInt(request.getParameter("modifId"));
                    try {
                        e = new Evenement(d, request.getParameter("titre"), request.getParameter("description"), request.getParameter("lieu"));

                        e.setIdevenement(evenementID);
                        evenementFacade.edit(e);
                        request.setAttribute("message", "Modification OK");
                        page = "enregistrement_ok.jsp";

                    } catch (NoResultException ex) {
                        request.setAttribute("message", "Le modification de l'évènement n'a pas été effectuée");
                        page = "erreur.jsp";
                    }
                }
            } else if (action.equals("detail")) {
                Integer evenementID = Integer.parseInt(request.getParameter("evenementId"));
                Evenement e = evenementFacade.findEvenementById(evenementID);
                if (e != null) {
                    request.setAttribute("evenement", e);
                    page = "detail_evenement.jsp";
                } else {
                    page = "erreur.jsp";
                    request.setAttribute("message", "Aucun évènement correspondant trouvé");
                }
            }
        }
        evenements = evenementFacade.findAll();
        request.setAttribute("evenements", evenements);
        RequestDispatcher dp = request.getRequestDispatcher(page);
        dp.forward(request, response);
    }

    private void creerEvenementTests() {
        if (evenementFacade.findEvenementByLieuDate("Paris", new Date(2013 - 1900, 4, 15, 15, 30)) == null) { // 15-mai-2013 à 15h30
            evenementFacade.create(new Evenement(new Date(2013 - 1900, 4, 15, 15, 30), "Rencontre", "Rencontre des anciens", "Paris"));
        }
        if (evenementFacade.findEvenementByLieuDate("Nice", new Date(2013 - 1900, 3, 10, 19, 30)) == null) {
            evenementFacade.create(new Evenement(new Date(2013 - 1900, 3, 10, 19, 30), "Gala", "Gala des anciens", "Nice"));
        }
        if (evenementFacade.findEvenementByLieuDate("Tours", new Date(2013 - 1900, 8, 5, 18, 30)) == null) {
            evenementFacade.create(new Evenement(new Date(2013 - 1900, 8, 5, 18, 30), "Soirée", "Soirée de rencontre avec les nouveaux", "Tours"));
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
