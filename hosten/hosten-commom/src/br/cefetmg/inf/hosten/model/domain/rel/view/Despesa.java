package br.cefetmg.inf.hosten.model.domain.rel.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "despesa", catalog = "hosten", schema = "public")
@Immutable
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d")
    , @NamedQuery(name = "Despesa.findBySeqhospedagem", query = "SELECT d FROM Despesa d WHERE d.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "Despesa.findByNroquarto", query = "SELECT d FROM Despesa d WHERE d.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "Despesa.findByNroadultos", query = "SELECT d FROM Despesa d WHERE d.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "Despesa.findByNrocriancas", query = "SELECT d FROM Despesa d WHERE d.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "Despesa.findByVlrdiaria", query = "SELECT d FROM Despesa d WHERE d.vlrDiaria = :vlrDiaria")
    , @NamedQuery(name = "Despesa.findByDatcheckin", query = "SELECT d FROM Despesa d WHERE d.datCheckin = :datCheckin")
    , @NamedQuery(name = "Despesa.findByDatcheckout", query = "SELECT d FROM Despesa d WHERE d.datCheckout = :datCheckout")
    , @NamedQuery(name = "Despesa.findByVlrpago", query = "SELECT d FROM Despesa d WHERE d.vlrPago = :vlrPago")
    , @NamedQuery(name = "Despesa.findByNomhospede", query = "SELECT d FROM Despesa d WHERE d.nomHospede = :nomHospede")
    , @NamedQuery(name = "Despesa.findBySeqservico", query = "SELECT d FROM Despesa d WHERE d.seqServico = :seqServico")
    , @NamedQuery(name = "Despesa.findByQtdconsumo", query = "SELECT d FROM Despesa d WHERE d.qtdConsumo = :qtdConsumo")
    , @NamedQuery(name = "Despesa.findByDesservico", query = "SELECT d FROM Despesa d WHERE d.desServico = :desServico")
    , @NamedQuery(name = "Despesa.findByVlrunit", query = "SELECT d FROM Despesa d WHERE d.vlrUnit = :vlrUnit")})
public class Despesa implements Serializable {

    @Id
    @Column(name = "seqhospedagem")
    private Integer seqHospedagem;
    
    @Column(name = "nroquarto")
    private Short nroQuarto;
    
    @Column(name = "nroadultos")
    private Short nroAdultos;
    
    @Column(name = "nrocriancas")
    private Short nroCriancas;
    
    @Column(name = "vlrdiaria", precision = 7, scale = 2)
    private BigDecimal vlrDiaria;
    
    @Column(name = "datcheckin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckin;
    
    @Column(name = "datcheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckout;
    
    @Column(name = "vlrpago", precision = 7, scale = 2)
    private BigDecimal vlrPago;
    
    @Column(name = "nomhospede", length = 90)
    private String nomHospede;
    
    @Column(name = "seqservico")
    private Short seqServico;
    
    @Column(name = "qtdconsumo")
    private Short qtdConsumo;
    
    @Column(name = "desservico", length = 40)
    private String desServico;
    
    @Column(name = "vlrunit", precision = 7, scale = 2)
    private BigDecimal vlrUnit;

    public Integer getSeqHospedagem() {
        return seqHospedagem;
    }

    public Short getNroQuarto() {
        return nroQuarto;
    }

    public Short getNroAdultos() {
        return nroAdultos;
    }

    public Short getNroCriancas() {
        return nroCriancas;
    }

    public BigDecimal getVlrDiaria() {
        return vlrDiaria;
    }

    public Date getDatCheckin() {
        return datCheckin;
    }

    public Date getDatCheckout() {
        return datCheckout;
    }

    public BigDecimal getVlrPago() {
        return vlrPago;
    }

    public String getNomHospede() {
        return nomHospede;
    }

    public Short getSeqServico() {
        return seqServico;
    }

    public Short getQtdConsumo() {
        return qtdConsumo;
    }

    public String getDesServico() {
        return desServico;
    }

    public BigDecimal getVlrUnit() {
        return vlrUnit;
    }
}
