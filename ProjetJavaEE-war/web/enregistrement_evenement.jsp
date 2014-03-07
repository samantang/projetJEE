<%-- 
    Document   : inscription
    Created on : 17 mars 2013, 10:56:01
    Author     : saliou
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>
<%@include file="entete.jsp" %>
<h1>${titre}</h1>
<div class="succes">${message}</div>
<div class="enregistrer">
    <!-- On ajoute l'id de modification dans le cas d'une modification -->
    <form method="post" action="evenements?action=creer&modifId=${modifId}" onsubmit="return validerFormulaireInscription();">
        <table>
            <tr>
                <td align="right">Titre:</td><td><input  type="text" size="60" name="titre" required="true" id="titre" value="${evenement.titre}"></td>
            </tr>
            <tr>
                <td align="right">Description:</td><td><textarea rows="10"  type="text" size="60" required="true" name="description" id="description">${evenement.description}</textarea></td>
            </tr>
            <tr>
                <td align="right">Lieu:</td><td><input  type="text" size="60" name="lieu" id="lieu" required="true" value="${evenement.lieu}"></td>
            </tr>

            <c:if test="${evenement != null}">
                <c:set var="jourEvmt" value="${evenement.jour}" scope="request" />
                <%
                    Date d = (Date) request.getAttribute("jourEvmt");
                    request.setAttribute("heure", d.getHours());
                    request.setAttribute("minute", d.getMinutes());
                %>
            </c:if>
            <tr>
                <td align="right">Date:</td>
                <td>
                    <input type="text" required="true" readonly="true" value="<fmt:formatDate value='${evenement.jour}' pattern='dd/MM/yyyy' />"  name="jour" id="jour" onclick="displayCalendar(document.forms[0].jour, 'dd/mm/yyyy', this);">
                    à 
                    <select name="heure"  id="heure">
                        <c:forEach var="h" items="${heureEvenement}">
                            <option value="${h}" <c:if test="${heure == h}"> selected </c:if> > ${h}</option>                            
                        </c:forEach>
                    </select> h
                    <select name="minute"  id="minute">
                        <c:forEach var="m" items="${minuteEvenement}">
                            <option value="${m}" <c:if test="${minute == m}"> selected </c:if> > ${m}</option>                            
                        </c:forEach>
                    </select> m
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="Submit" type="Submit" style="width: 60px;"  value="Valider"/></td>
            </tr>
        </table>
        <div></div>
    </form>
</div>

<%@include file="pied.jsp" %>
