/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AARON
 */
@Entity
@Table(name = "communitymanager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communitymanager.findAll", query = "SELECT c FROM Communitymanager c")
    , @NamedQuery(name = "Communitymanager.findByIdCM", query = "SELECT c FROM Communitymanager c WHERE c.idCM = :idCM")
    , @NamedQuery(name = "Communitymanager.findByMembresia", query = "SELECT c FROM Communitymanager c WHERE c.membresia = :membresia")})
public class Communitymanager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCM")
    private Integer idCM;
    @Size(max = 150)
    @Column(name = "Membresia")
    private String membresia;
    @JoinColumn(name = "idCM", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Suscripcion> suscripcionList;

    public Communitymanager() {
    }

    public Communitymanager(Integer idCM) {
        this.idCM = idCM;
    }

    public Integer getIdCM() {
        return idCM;
    }

    public void setIdCM(Integer idCM) {
        this.idCM = idCM;
    }

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Suscripcion> getSuscripcionList() {
        return suscripcionList;
    }

    public void setSuscripcionList(List<Suscripcion> suscripcionList) {
        this.suscripcionList = suscripcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCM != null ? idCM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communitymanager)) {
            return false;
        }
        Communitymanager other = (Communitymanager) object;
        if ((this.idCM == null && other.idCM != null) || (this.idCM != null && !this.idCM.equals(other.idCM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Communitymanager[ idCM=" + idCM + " ]";
    }
    
}
