<%-- 
    Document   : profil
    Created on : 17 mars 2013, 10:45:32
    Author     : saliou
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>
<%@include file="entete.jsp" %>
<h1>Mes informations personnelles</h1>
<div class="info">
    <div><span id="titre">Nom: </span> <span>${utilisateur.nom}</span></div>
    <div><span id="titre">Pr�nom: </span> <span>${utilisateur.prenom}</span></div>
    <div><span id="titre">Membre: </span> <span>${utilisateur.membre}</span></div>
    <div><span id="titre">Email: </span> <span>${utilisateur.email}</span></div>
    <div><span id="titre">Date de naissance </span> <span><fmt:formatDate  value="${utilisateur.dateDeNaissance}" pattern="dd/MM/yyyy"/></span></div>
    <div><span id="titre">Ann�e d'inscription: </span> <span>${utilisateur.anneeInscription}</span></div>
</div>
<div id="maCarriere">
    <h4>Ma carri�re</h4>
    <c:forEach var="c" items="${utilisateur.carriereCollection}">
         <div>
             <span id="titre">Du <fmt:formatDate  value="${c.dateDebut}" pattern="dd/MM/yyyy"/> au :<fmt:formatDate  value="${c.dateFin}" pattern="dd/MM/yyyy"/></span>
             <span>employ� chez ${c.employeur} comme ${c.fonction}</span>
         </div>
    </c:forEach>
</div>
<a href="personnes?action=modifierPersonne&modifId=${utilisateur.idpersonne}">Modifier mon profil</a>
<%@include file="pied.jsp" %>

