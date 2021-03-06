package br.cefetmg.inf.hosten.model.domain;

import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
    , @NamedQuery(name = "Hospedagem.findBySeqHospedagem", query = "SELECT h FROM Hospedagem h WHERE h.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "Hospedagem.findByDatCheckin", query = "SELECT h FROM Hospedagem h WHERE h.datCheckin = :datCheckin")
    , @NamedQuery(name = "Hospedagem.findByDatCheckout", query = "SELECT h FROM Hospedagem h WHERE h.datCheckout = :datCheckout")
    , @NamedQuery(name = "Hospedagem.findByVlrPago", query = "SELECT h FROM Hospedagem h WHERE h.vlrPago = :vlrPago")})
public class Hospedagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seqhospedagem", nullable = false)
    private Integer seqHospedagem;

    @Basic(optional = false)
    @Column(name = "datcheckin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp datCheckin;

    @Column(name = "datcheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp datCheckout;

    @Column(name = "vlrpago", precision = 7, scale = 2)
    private BigDecimal vlrPago;

    @OneToMany(mappedBy = "hospedagem", cascade = CascadeType.ALL)
    private Set<QuartoHospedagem> quartoHospedagens;

    @ManyToOne
    @JoinColumn(name = "codcpf", referencedColumnName = "codcpf")
    private Hospede hospede;

    public Hospedagem() {
    }

    public Hospedagem(Timestamp datCheckin, Timestamp datCheckout, BigDecimal vlrPago) {
        this.datCheckin = datCheckin;
        this.datCheckout = datCheckout;
        this.vlrPago = vlrPago;
    }

    public Hospedagem(Integer seqHospedagem, Timestamp datCheckin, Timestamp datCheckout, BigDecimal vlrPago) {
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

    public Timestamp getDatCheckin() {
        return datCheckin;
    }

    public void setDatCheckin(Timestamp datCheckin) {
        this.datCheckin = datCheckin;
    }

    public Timestamp getDatCheckout() {
        return datCheckout;
    }

    public void setDatCheckout(Timestamp datCheckout) {
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

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public void addQuarto(Quarto quarto, short nroadultos, short nrocriancas, BigDecimal vlrdiaria) {
        QuartoHospedagemId qhId = new QuartoHospedagemId(this.getSeqHospedagem(), quarto.getNroQuarto());
        QuartoHospedagem qh = new QuartoHospedagem(qhId, nroadultos, nrocriancas, vlrdiaria);

        this.quartoHospedagens.add(qh);
        quarto.getQuartoHospedagens().add(qh);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqHospedagem != null ? seqHospedagem.hashCode() : 0);
        return hash;
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
        String saida = "";
        saida += "Hospedagem={ seqhospedagem=[" + seqHospedagem + "], datCheckin=[" + datCheckin + "],"
                + "datCheckout=[" + datCheckout + ", vlrPago=[" + vlrPago + "]";
        if (hospede != null) {
            saida += ", codCPF = [" + hospede.toString() + "]";
        }
        saida += "}";

        return saida;
    }
}
