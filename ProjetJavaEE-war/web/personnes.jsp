<%-- 
    Document   : personnes
    Created on : 16 mars 2013, 00:10:18
    Author     : saliou
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="entete.jsp" %>
<h1>Listes des personnes</h1>
<br>
<table cellspacing="1" border="0">
    <tr>
        <th>N&deg;</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Email</th>
        <th>Login</th>
        <th>Date de naissance</th>
        <th>Année d'inscription</th>
        <th>Membre CCI</th>
            <c:if test="${utilisateur.login == 'admin'}" >
            <th>Modifier</th>
            <th>Supprimer</th>
            </c:if>
    </tr>
    <% int i = 0;%>
    <c:forEach var="p" items="${personnes}">
        <c:if test="${p.login != 'admin'}">
            <tr id="line<%=i % 2%>">
                <td align="center"><%=i + 1%></td>
                <td align="center">${p.nom}</td>
                <td align="center">${p.prenom}</td>
                <td align="center">${p.email}</td>
                <td align="center">${p.login}</td>
                <td align="center"><fmt:formatDate value="${p.dateDeNaissance}" pattern="dd/MM/yyyy"/></td>
                <td align="center">${p.anneeInscription}</td>
                <td align="center">${p.membre}</td>
                <c:if test="${utilisateur.login == 'admin'}" >
                    <td align="center"><a href="personnes?action=modifierPersonne&modifId=${p.idpersonne}"><img src="images/edit.png"/></a></td>
                    <td align="center"><a href="personnes?action=supprimerPersonne&supprId=${p.idpersonne}" onclick=" return confirm('Voulez vous supprimer cette personne?');"><img src="images/delete.png"/></a></td>
                        </c:if>
            </tr>
            <% i++;%>
        </c:if>
    </c:forEach> 
</table>
<br>
<%@include file="pied.jsp" %>