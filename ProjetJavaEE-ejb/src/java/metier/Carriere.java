/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saliou
 */
@Entity
@Table(name = "carriere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carriere.findAll", query = "SELECT c FROM Carriere c"),
    @NamedQuery(name = "Carriere.findByIdcarriere", query = "SELECT c FROM Carriere c WHERE c.idcarriere = :idcarriere"),
    @NamedQuery(name = "Carriere.findBydateDebut", query = "SELECT c FROM Carriere c WHERE c.dateDebut = :dateDebut"),
    @NamedQuery(name = "Carriere.findBydateFin", query = "SELECT c FROM Carriere c WHERE c.dateFin = :dateFin"),
    @NamedQuery(name = "Carriere.findByEmployeur", query = "SELECT c FROM Carriere c WHERE c.employeur = :employeur"),
    @NamedQuery(name = "Carriere.findByFonction", query = "SELECT c FROM Carriere c WHERE c.fonction = :fonction")})
public class Carriere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcarriere")
    private Integer idcarriere;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Size(max = 45)
    @Column(name = "employeur")
    private String employeur;
    @Size(max = 45)
    @Column(name = "fonction")
    private String fonction;
    @JoinColumn(name = "idpersonne", referencedColumnName = "idpersonne")
    @ManyToOne(optional = false)
    private Personne idpersonne;

    public Carriere() {
    }

    public Carriere(Integer idcarriere) {
        this.idcarriere = idcarriere;
    }

    public Carriere(Date dateDebut, Date dateFin, String employeur, String fonction, Personne idpersonne) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.employeur = employeur;
        this.fonction = fonction;
        this.idpersonne = idpersonne;        
    }

    public Integer getIdcarriere() {
        return idcarriere;
    }

    public void setIdcarriere(Integer idcarriere) {
        this.idcarriere = idcarriere;
    }

    public Date getdateDebut() {
        return dateDebut;
    }

    public void setdateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getdateFin() {
        return dateFin;
    }

    public void setdateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEmployeur() {
        return employeur;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Personne getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Personne idpersonne) {
        this.idpersonne = idpersonne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarriere != null ? idcarriere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carriere)) {
            return false;
        }
        Carriere other = (Carriere) object;
        if ((this.idcarriere == null && other.idcarriere != null) || (this.idcarriere != null && !this.idcarriere.equals(other.idcarriere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metier.Carriere[ idcarriere=" + idcarriere + " ][ Date de début=" + dateDebut + " ][ Employeur=" + employeur + " ]";
    }
    
}
