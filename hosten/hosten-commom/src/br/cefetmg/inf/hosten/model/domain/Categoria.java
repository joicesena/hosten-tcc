package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByCodcategoria", query = "SELECT c FROM Categoria c WHERE c.codCategoria = :codCategoria")
    , @NamedQuery(name = "Categoria.findByNomcategoria", query = "SELECT c FROM Categoria c WHERE c.nomCategoria = :nomCategoria")
    , @NamedQuery(name = "Categoria.findByVlrdiaria", query = "SELECT c FROM Categoria c WHERE c.vlrDiaria = :vlrDiaria")})
public class Categoria implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codcategoria", nullable = false, length = 3)
    private String codCategoria;

    @Basic(optional = false)
    @Column(name = "nomcategoria", nullable = false, length = 40)
    private String nomCategoria;

    @Basic(optional = false)
    @Column(name = "vlrdiaria", nullable = false, precision = 7, scale = 2)
    private BigDecimal vlrDiaria;

    @ManyToMany
    @JoinTable(
            name = "categoriaitemconforto",
            joinColumns = {
                @JoinColumn(name = "codcategoria", referencedColumnName = "codcategoria", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "coditem", referencedColumnName = "coditem", nullable = false)})
    private Set<ItemConforto> itemConfortos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoria")
    private List<Quarto> quartos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(String codcategoria) {
        this.codCategoria = codcategoria;
    }

    public Categoria(String codCategoria, String nomCategoria, BigDecimal vlrDiaria, Set<ItemConforto> itemConfortos) {
        this.codCategoria = codCategoria;
        this.nomCategoria = nomCategoria;
        this.vlrDiaria = vlrDiaria;
        this.itemConfortos = itemConfortos;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public BigDecimal getVlrDiaria() {
        return vlrDiaria;
    }

    public void setVlrDiaria(BigDecimal vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
    }

    public Set<ItemConforto> getItemConfortos() {
        return itemConfortos;
    }

    public void setItemConfortos(Set<ItemConforto> itemConfortos) {
        this.itemConfortos = itemConfortos;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
    }
    
    public void addItemConforto(ItemConforto itemConforto) {
        this.itemConfortos.add(itemConforto);
        itemConforto.getCategorias().add(this);
    }
    
    public void removeItemConforto(ItemConforto itemConforto) {
        this.itemConfortos.remove(itemConforto);
        itemConforto.getCategorias().remove(this);
    }
    
    public void addQuarto(Quarto quarto) {
        this.quartos.add(quarto);
        quarto.setCodCategoria(this);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Categoria[ codcategoria=" + codCategoria + " ]";
    }
}
