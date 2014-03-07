<%-- 
    Document   : evenements
    Created on : 21 mars 2013, 21:50:00
    Author     : saliou
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="entete.jsp" %>
<div>
    <h1>Les évènements</h1>
    <c:forEach var="e" items="${evenements}">
        <div id="evenements">
            <div id="titre">${e.titre}</div>
            <div id="detail_evenement">
                <c:set var="desc" scope="request" value="${e.description}" />
                <%
                    String desc = (String) request.getAttribute("desc");
                    if (desc.length() > 10) {
                        request.setAttribute("desc", desc.substring(0, 11) + " ...");
                    }
                %>
                <div>${desc}</div>
                <div>Lieu: ${e.lieu}</div>
                <div>Date: <fmt:formatDate  value="${e.jour}" pattern="dd/MM/yyyy HH:mm"/></div><br>
                <div><a href='evenements?action=detail&evenementId=${e.idevenement}'>Détail</a></div>
            </div>
        </div>
    </c:forEach>
</div>               

<%@include file="pied.jsp" %>
