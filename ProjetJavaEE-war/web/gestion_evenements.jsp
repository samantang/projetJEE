<%-- 
    Document   : gestion_evenements
    Created on : 21 mars 2013, 23:37:58
    Author     : saliou
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="entete.jsp" %>

<h1>Les évènements</h1>
<br>
<table cellspacing="1" border="0">
    <tr>
        <th>N&deg;</th>
        <th>Titre</th>
        <th>Description</th>
        <th>Date</th>
        <th>Lieu</th>
        <th>Modifier</th>
        <th>Supprimer</th>
    </tr>
    <% int i = 0;%>
    <c:forEach var="e" items="${evenements}">
        <tr id="line<%=i % 2%>">
            <td align="center"><%=i + 1%></td>
            <td>${e.titre}</td>
            <td>${e.description}</td>
            <td align="center"><fmt:formatDate value="${e.jour}" pattern="dd/MM/yyyy HH:mm"/></td>
            <td align="center">${e.lieu}</td>
    <td align="center"><a href="evenements?action=modifier&modifId=${e.idevenement}"><img src="images/edit.png"/></a></td>
    <td align="center"><a href="evenements?action=supprimer&supprId=${e.idevenement}" onclick=" return confirm('Voulez vous supprimer cet évènement?');"><img src="images/delete.png"/></a></td>
</tr>
<% i++;%>
</c:forEach> 
</table>
<br>
<%@include file="pied.jsp" %>

