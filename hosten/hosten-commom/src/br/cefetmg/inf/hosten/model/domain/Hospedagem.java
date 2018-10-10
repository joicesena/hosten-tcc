package br.cefetmg.inf.hosten.model.domain;

import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hospedagem", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Hospedagem.findAll", query = "SELECT h FROM Hospedagem h")
    , @NamedQuery(name = "Hospedagem.findBySeqhospedagem", query = "SELECT h FROM Hospedagem h WHERE h.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "Hospedagem.findByDatcheckin", query = "SELECT h FROM Hospedagem h WHERE h.datCheckin = :datCheckin")
    , @NamedQuery(name = "Hospedagem.findByDatcheckout", query = "SELECT h FROM Hospedagem h WHERE h.datCheckout = :datCheckout")
    , @NamedQuery(name = "Hospedagem.findByVlrpago", query = "SELECT h FROM Hospedagem h WHERE h.vlrPago = :vlrPago")})
public class Hospedagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seqhospedagem", nullable = false)
    private Integer seqHospedagem;

    @Basic(optional = false)
    @Column(name = "datcheckin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckin;

    @Column(name = "datcheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckout;

    @Column(name = "vlrpago", precision = 7, scale = 2)
    private BigDecimal vlrPago;

    @OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL)
    private Set<QuartoHospedagem> quartoHospedagens;

    @ManyToOne
    @JoinColumn(name = "codcpf", referencedColumnName = "codcpf")
    private Hospede codCpf;

    public Hospedagem() {
    }

    public Hospedagem(Integer seqhospedagem) {
        this.seqHospedagem = seqhospedagem;
    }

    public Hospedagem(Integer seqHospedagem, Date datCheckin, Date datCheckout, BigDecimal vlrPago) {
        this.seqHospedagem = seqHospedagem;
        this.datCheckin = datCheckin;
        this.datCheckout = datCheckout;
        this.vlrPago = vlrPago;
    }

    public Integer getSeqHospedagem() {
        return seqHospedagem;
    }

    public void setSeqHospedagem(Integer seqHospedagem) {
        this.seqHospedagem = seqHospedagem;
    }

    public Date getDatCheckin() {
        return datCheckin;
    }

    public void setDatCheckin(Date datCheckin) {
        this.datCheckin = datCheckin;
    }

    public Date getDatCheckout() {
        return datCheckout;
    }

    public void setDatCheckout(Date datCheckout) {
        this.datCheckout = datCheckout;
    }

    public BigDecimal getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(BigDecimal vlrPago) {
        this.vlrPago = vlrPago;
    }

    public Set<QuartoHospedagem> getQuartoHospedagens() {
        return quartoHospedagens;
    }

    public void setQuartoHospedagens(Set<QuartoHospedagem> quartoHospedagens) {
        this.quartoHospedagens = quartoHospedagens;
    }

    public Hospede getCodCpf() {
        return codCpf;
    }

    public void setCodCpf(Hospede codCpf) {
        this.codCpf = codCpf;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqHospedagem != null ? seqHospedagem.hashCode() : 0);
        return hash;
    }
    
    public void addQuarto(Quarto quarto, short nroadultos, short nrocriancas, BigDecimal vlrdiaria) {
        QuartoHospedagemId qhid = new QuartoHospedagemId(this.getSeqHospedagem(), quarto.getNroQuarto());
        QuartoHospedagem qh = new QuartoHospedagem(qhid, nroadultos, nrocriancas, vlrdiaria);
        
        this.quartoHospedagens.add(qh);
        quarto.getQuartoHospedagens().add(qh);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospedagem)) {
            return false;
        }
        Hospedagem other = (Hospedagem) object;
        if ((this.seqHospedagem == null && other.seqHospedagem != null) || (this.seqHospedagem != null && !this.seqHospedagem.equals(other.seqHospedagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Hospedagem[ seqhospedagem=" + seqHospedagem + " ]";
    }

}
