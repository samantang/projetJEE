/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saliou
 */
@Entity
@Table(name = "personne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findByIdpersonne", query = "SELECT p FROM Personne p WHERE p.idpersonne = :idpersonne"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByAnneeInscription", query = "SELECT p FROM Personne p WHERE p.anneeInscription = :anneeInscription"),
    @NamedQuery(name = "Personne.findByMembre", query = "SELECT p FROM Personne p WHERE p.membre = :membre"),
    @NamedQuery(name = "Personne.findByLogin", query = "SELECT p FROM Personne p WHERE p.login = :login"),
    @NamedQuery(name = "Personne.findByMotDePasse", query = "SELECT p FROM Personne p WHERE p.motDePasse = :motDePasse"),
    @NamedQuery(name = "Personne.findByEmail", query = "SELECT p FROM Personne p WHERE p.email = :email"),
    @NamedQuery(name = "Personne.findByDateDeNaissance", query = "SELECT p FROM Personne p WHERE p.dateDeNaissance = :dateDeNaissance")})
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonne")
    private Integer idpersonne;
    @Size(max = 45)
    @Column(name = "nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "annee_inscription")
    private Integer anneeInscription;
    @Size(max = 45)
    @Column(name = "membre")
    private String membre;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Size(max = 45)
    @Column(name = "mot_de_passe")
    private String motDePasse;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Column(name = "date_de_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersonne")
    private Collection<Carriere> carriereCollection = new ArrayList<Carriere>();

    public Personne() {
    }

    public Personne(Integer idpersonne) {
        this.idpersonne = idpersonne;
    }

    public Personne(String nom, String prenom, Integer anneeInscription, String membre, String login, String motDePasse, String email, Date dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.anneeInscription = anneeInscription;
        this.membre = membre;
        this.login = login;
        this.motDePasse = motDePasse;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
    }
    public Personne(Integer idpersonne, String nom, String prenom, Integer anneeInscription, String membre, String login, String motDePasse, String email, Date dateDeNaissance) {
        this.idpersonne = idpersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.anneeInscription = anneeInscription;
        this.membre = membre;
        this.login = login;
        this.motDePasse = motDePasse;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
    }
    
    public Integer getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Integer idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAnneeInscription() {
        return anneeInscription;
    }

    public void setAnneeInscription(Integer anneeInscription) {
        this.anneeInscription = anneeInscription;
    }

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
    @XmlTransient
    public Collection<Carriere> getCarriereCollection() {
        return carriereCollection;
    }

    public void setCarriereCollection(Collection<Carriere> carriereCollection) {
        this.carriereCollection = carriereCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonne != null ? idpersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.idpersonne == null && other.idpersonne != null) || (this.idpersonne != null && !this.idpersonne.equals(other.idpersonne))) {
            return false;
        }
        return true;
    }

    public void addCarriere(Carriere c) {
        if (!carriereCollection.contains(c)) {
            carriereCollection.add(c);
        }
        if (c.getIdpersonne() != null) {
            c.setIdpersonne(this);
        }

    }
    @Override
    public String toString() {
        return "metier.Personne[ idpersonne=" + idpersonne + " ][ nom=" + nom + " ][ prenom=" + prenom + " ]";
    }
}
