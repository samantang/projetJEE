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
import javax.persistence.Lob;
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
@Table(name = "evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
    @NamedQuery(name = "Evenement.findByIdevenement", query = "SELECT e FROM Evenement e WHERE e.idevenement = :idevenement"),
    @NamedQuery(name = "Evenement.findByJour", query = "SELECT e FROM Evenement e WHERE e.jour = :jour"),
    @NamedQuery(name = "Evenement.findByTitre", query = "SELECT e FROM Evenement e WHERE e.titre = :titre"),
    @NamedQuery(name = "Evenement.findByLieu", query = "SELECT e FROM Evenement e WHERE e.lieu = :lieu")})
public class Evenement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevenement")
    private Integer idevenement;
    @Column(name = "jour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jour;
    @Size(max = 45)
    @Column(name = "titre")
    private String titre;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "lieu")
    private String lieu;

    public Evenement() {
    }

    public Evenement(Integer idevenement) {
        this.idevenement = idevenement;
    }

    public Evenement(Date date, String titre, String description, String lieu) {
        this.jour = date;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
    }
    
    public Integer getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(Integer idevenement) {
        this.idevenement = idevenement;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevenement != null ? idevenement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.idevenement == null && other.idevenement != null) || (this.idevenement != null && !this.idevenement.equals(other.idevenement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metier.Evenement[ idevenement=" + idevenement + " ]";
    }
    
}
