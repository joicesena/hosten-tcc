package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servicoarea", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ServicoArea.findAll", query = "SELECT s FROM ServicoArea s")
    , @NamedQuery(name = "ServicoArea.findByCodServicoArea", query = "SELECT s FROM ServicoArea s WHERE s.codServicoArea = :codServicoArea")
    , @NamedQuery(name = "ServicoArea.findByNomServicoArea", query = "SELECT s FROM ServicoArea s WHERE s.nomServicoArea = :nomServicoArea")})
public class ServicoArea implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codservicoarea", nullable = false, length = 3)
    private String codServicoArea;

    @Basic(optional = false)
    @Column(name = "nomservicoarea", nullable = false, length = 40)
    private String nomServicoArea;

    @OneToMany(mappedBy = "servicoArea", cascade = CascadeType.ALL)
    private final List<Servico> servicos = new ArrayList<>();

    public ServicoArea() {
    }

    public ServicoArea(String codservicoarea) {
        this.codServicoArea = codservicoarea;
    }

    public ServicoArea(String codServicoArea, String nomServicoArea) {
        this.codServicoArea = codServicoArea;
        this.nomServicoArea = nomServicoArea;
    }

    public String getCodServicoArea() {
        return codServicoArea;
    }

    public void setCodServicoArea(String codServicoArea) {
        this.codServicoArea = codServicoArea;
    }

    public String getNomServicoArea() {
        return nomServicoArea;
    }

    public void setNomServicoArea(String nomServicoArea) {
        this.nomServicoArea = nomServicoArea;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void addServico(Servico servico) {
        servico.setServicoArea(this);
        this.servicos.add(servico);
    }

    public void removeServico(Servico serv, ServicoArea saNov) {
        if (servicos.contains(serv)) {
            saNov.addServico(serv);
            this.servicos.remove(serv);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codServicoArea != null ? codServicoArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicoArea)) {
            return false;
        }
        ServicoArea other = (ServicoArea) object;
        if ((this.codServicoArea == null && other.codServicoArea != null) || (this.codServicoArea != null && !this.codServicoArea.equals(other.codServicoArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Servicoarea[ codservicoarea=" + codServicoArea + " ]";
    }

}
