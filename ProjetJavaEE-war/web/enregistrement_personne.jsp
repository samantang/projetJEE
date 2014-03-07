<%-- 
    Document   : inscription
    Created on : 17 mars 2013, 10:56:01
    Author     : saliou
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Date"%>
<%@include file="entete.jsp" %>
<div>
    <h1>${titre}</h1>
    <div class="succes">${message}</div>
    <div class="enregistrer">
        <!-- On ajoute l'id de modification dans le cas d'une modification -->
        <form method="post" action="personnes?action=creerPersonne&modifId=${modifId}">
            <fieldset id="infoPerso">
                <legend>Informations personnelles</legend>
                <table>
                    <tr>
                        <td align="right">Nom:</td><td><input  type="text" size="60" name="nom" required="true" id="nom" value="${personne.nom}"></td>
                    </tr>
                    <tr>
                        <td align="right">Prénom:</td><td><input type="text" name="prenom" required="true" id="prenom"  value="${personne.prenom}"></td>
                    </tr>
                    
                    <tr>
                        <td align="right">
                            Année d'inscription:</td><td><select name="annee_inscription" id="annee_inscription" >
                            <c:forEach var="annee" items="${anneeInscription}">
                                    <option value="${annee}" <c:if test="${personne.anneeInscription == annee}"> selected </c:if> > ${annee}</option>                            
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Membre:</td>
                        <td>
                            <select name="membre" id="membre">
                                <option <c:if test="${personne.membre == 'NON'}"> selected </c:if> value="NON">NON</option>
                                <option <c:if test="${personne.membre == 'OUI'}"> selected </c:if> value="OUI">OUI</option>
                                </select>
                            </td>
                        </tr>              
                        <tr>
                            <td align="right">Date de naissance:</td>
                            <td><input type="text" readonly="true" value="<fmt:formatDate value='${personne.dateDeNaissance}' pattern='dd/MM/yyyy' />" required="true" name="dateDeNaissance" id="dateDeNaissance" onclick="displayCalendar(document.forms[0].dateDeNaissance, 'dd/mm/yyyy', this);"/></td>

                    </tr>
                    <tr>
                        <td align="right">Email:</td><td><input type="text" name="email" required="true" id="email" value="${personne.email}"></td>
                    </tr>
                    <tr>
                        <td align="right">Login:</td><td><input type="text" name="login" required="true" id="login"  value="${personne.login}"></td>
                    </tr>
                    <tr>
                        <td align="right">Mot de passe:</td><td><input type="password" name="motDePasse" required="true" id="motDePasse" value="${personne.motDePasse}"></td>
                    </tr>
                    <tr>
                        <td align="right">Confirmer le mot de passe:</td><td><input type="password" name="reMotDePasse" required="true" id="reMotDePasse" value="${personne.motDePasse}"></td>
                    </tr>

                </table>
            </fieldset>
            <table cellspacing="1" border="0" style="border: solid 1px gray">
                <tr>
                    <th>N&deg;</th>
                    <th>Date de début</th>
                    <th>Date de fin</th>
                    <th>Fonction</th>
                    <th>Employeur</th>
                        <c:if test="${utilisateur.login == 'admin' || utilisateur.login == personne.login}" >
                        <th>Supprimer</th>
                        </c:if>
                </tr>
                <% int i = 0;%>
                <c:forEach var="c" items="${personne.carriereCollection}">
                    <tr id="line<%=i % 2%>">
                        <td align="center"><%=i + 1%></td>
                        <td align="center"><fmt:formatDate value="${c.dateDebut}" pattern="dd/MM/yyyy"/></td>
                        <td align="center"><fmt:formatDate value="${c.dateFin}" pattern="dd/MM/yyyy"/></td>
                        <td align="center">${c.fonction}</td>
                        <td align="center">${c.employeur}</td>
                        <c:if test="${utilisateur.login == 'admin' ||  utilisateur.login == personne.login}" >
                            <td align="center"><span href="personnes?action=supprimerCarriere&supprId=${c.idcarriere}" 
                                                     onclick="supprimerCarriere('personnes?action=supprimerCarriere&supprId=${c.idcarriere}','action=modifierPersonne&modifId=${modifId}');"><img src="images/delete.png"/></span></td>
                                </c:if>
                    </tr>
                    <% i++;%>
                </c:forEach>
            </table>
            <fieldset id="carrieres">
                <legend>Carrière</legend>
                <div id="sectionCarriere"></div>
                <div id="ajoutCarriere"><span style="cursor: pointer; float: right;" onclick="ajouterSectionCarriere();">Ajouter une carrière</span></div>
            </fieldset>
            <center>
                <table>
                    <tr>
                        <td colspan="2" align="right"><input type="Submit" style="width: 60px;" value="Valider"/></td>
                    </tr>
                </table>
            </center>
        </form>
    </div>

</div>
<%@include file="pied.jsp" %>
