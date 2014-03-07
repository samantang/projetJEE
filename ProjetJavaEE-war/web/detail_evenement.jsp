<%-- 
    Document   : detail_evenement
    Created on : 21 mars 2013, 23:37:18
    Author     : saliou
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="entete.jsp" %>
    <h1>Détail de l'évènement</h1>
    <div id="evenement">
        <div id="titre">${evenement.titre}</div>
        <div id="detail_evenement">
            <div>${evenement.description}</div>
            <div>Lieu: ${evenement.lieu}</div>
            <div>Date: <fmt:formatDate  value="${evenement.jour}" pattern="dd/MM/yyyy HH:mm"/></div>
        </div>
    </div>
    <br>
    <a href="evenements">Retour</a>
<%@include file="pied.jsp" %>

