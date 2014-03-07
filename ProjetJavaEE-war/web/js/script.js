/**
 * Ajout de la section pour ajouter une nouvelle carriere
 * @returns {undefined}
 */
function ajouterSectionCarriere() {

//    var i = $('.carriere').length;
    var carriere = '<table class="carriere" id="carriere">\n\
<tr><td align="right">Date de d&eacute;but:</td> \n\
    <td><input type="text" readonly="true" value="" required="true" name="dateDebut" id="dateDebut" onclick="displayCalendar(document.forms[0].dateDebut, \'dd/mm/yyyy\', this);"/></td>\n\
</tr>\n\
<tr>\n\
    <td align="right">Date de fin:</td>\n\
    <td><input type="text" readonly="true" value=""  name="dateFin" id="dateFin" onclick="displayCalendar(document.forms[0].dateFin, \'dd/mm/yyyy\', this);"/></td>\n\
</tr>\n\
<tr>\n\
    <td align="right">Employeur:</td>\n\
    <td><input type="text" name="employeur" id="employeur" required="true"></td>\n\
</tr>\n\
<tr>\n\
    <td align="right">Fonction:</td>\n\
    <td><input type="text" name="fonction" id="fonction" required="true"></td>\n\
</tr>\n\
<tr>\n\
<td colspan="2" align="right"><span onclick="supprimerSectionCarriere()"><img src="images/delete.png"/></span></td>\n\
</tr></table>';
    $('#sectionCarriere').html(carriere);

}

function supprimerSectionCarriere() {
    $('#sectionCarriere').html('');
}


/**
 * Suppression d'une carrière tout en restant sur la meme page
 * @param {type} lienSuppr
 * @param {type} lienRedirect description
 * @returns {Boolean}
 */
function supprimerCarriere(lienSuppr, lienRedirect) {
    var lienPageCourante = document.location.href;
    var sp = lienPageCourante.split('?');    
    if (confirm('Voulez vous supprimer cette carriere?')) {
        $.ajax({
            url: lienSuppr,
            success: function() {
                document.location.href =sp[0]+ "?"+ lienRedirect;
            }

        });
        return true;
    }
    return false;
}