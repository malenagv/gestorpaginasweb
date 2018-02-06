/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AARON
 */
@Entity
@Table(name = "suscripcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suscripcion.findAll", query = "SELECT s FROM Suscripcion s")
    , @NamedQuery(name = "Suscripcion.findByIdSuscripcion", query = "SELECT s FROM Suscripcion s WHERE s.idSuscripcion = :idSuscripcion")
    , @NamedQuery(name = "Suscripcion.findByMedioDePago", query = "SELECT s FROM Suscripcion s WHERE s.medioDePago = :medioDePago")
    , @NamedQuery(name = "Suscripcion.findByMonto", query = "SELECT s FROM Suscripcion s WHERE s.monto = :monto")
    , @NamedQuery(name = "Suscripcion.findByFecha", query = "SELECT s FROM Suscripcion s WHERE s.fecha = :fecha")})
public class Suscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSuscripcion")
    private Integer idSuscripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MedioDePago")
    private String medioDePago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Monto")
    private long monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idPersona", referencedColumnName = "idCM")
    @ManyToOne(optional = false)
    private Communitymanager idPersona;
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(optional = false)
    private Plan idPlan;

    public Suscripcion() {
    }

    public Suscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public Suscripcion(Integer idSuscripcion, String medioDePago, long monto, Date fecha) {
        this.idSuscripcion = idSuscripcion;
        this.medioDePago = medioDePago;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Integer getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Communitymanager getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Communitymanager idPersona) {
        this.idPersona = idPersona;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSuscripcion != null ? idSuscripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suscripcion)) {
            return false;
        }
        Suscripcion other = (Suscripcion) object;
        if ((this.idSuscripcion == null && other.idSuscripcion != null) || (this.idSuscripcion != null && !this.idSuscripcion.equals(other.idSuscripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Suscripcion[ idSuscripcion=" + idSuscripcion + " ]";
    }
    
}
