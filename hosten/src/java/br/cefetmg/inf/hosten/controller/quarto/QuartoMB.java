package br.cefetmg.inf.hosten.controller.quarto;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.dist.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.hosten.dist.proxy.ManterQuartoProxy;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

@ViewScoped
@Named(value = "quartoMB")
public class QuartoMB implements Serializable {

    private IManterQuarto manterQuarto;

    private Quarto quarto;
    private List<Quarto> listaQuartos;

    private CategoriaQuarto categoriaQuarto;
    private CategoriaQuarto categoriaSelecionada;

    private short nroQuartoAlterar;

    public QuartoMB() {
        quarto = new Quarto();
        manterQuarto = new ManterQuartoProxy();
        try {
            listaQuartos = manterQuarto.listarTodos();
        } catch (NegocioException | SQLException e) {
            //
        }
    }

    public CategoriaQuarto getCategoriaQuarto(Quarto quarto) {
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        try {
            categoriaQuarto = manterCategoria.listar(quarto.getCategoria(), "codCategoria").get(0);
        } catch (NegocioException | SQLException ex) {
            ex.printStackTrace();
            //
        }
        return categoriaQuarto;
    }

    public void setCategoriaQuarto(CategoriaQuarto categoriaQuarto) {
        this.categoriaQuarto = categoriaQuarto;
    }

    public List<Quarto> getListaQuartos() {
        return listaQuartos;
    }

    public void setListaQuartos(List<Quarto> listaQuartos) {
        this.listaQuartos = listaQuartos;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public void onRowInit(RowEditEvent event) {
        nroQuartoAlterar = (short) event.getComponent().getAttributes().get("quartoEditar");
    }

    public void onRowEdit(RowEditEvent event) throws IOException {
        try {
            quarto = (Quarto) event.getObject();
            quarto.setCategoria(categoriaSelecionada);

            boolean testeExclusao = manterQuarto.alterar(nroQuartoAlterar, quarto);
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Alteração efetuada", "Registro alterado com sucesso!", true);
            } else {
                ContextUtils.mostrarMensagem("Falha na alteração", "Falha ao alterar o registro!", true);
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na alteração", ex.getMessage(), true);
        }
        ContextUtils.redireciona(null);
    }

    public void onRowCancel(RowEditEvent event) {
        ContextUtils.mostrarMensagem("Edição Cancelada", ((Quarto) event.getObject()).getCategoria().getCodCategoria(), false);
    }

    public String excluir(Quarto quarto) {
        this.quarto = quarto;

        try {
            boolean testeExclusao = manterQuarto.excluir(quarto.getNroQuarto());
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Exclusão efetuada", "Registro excluído com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na exclusão", "Falha ao excluir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na exclusão", ex.getMessage(), true);
            return "falha";
        }

    }

    public String inserir() {
        try {
            quarto.setCategoria(categoriaSelecionada);

            boolean testeInsercao = manterQuarto.inserir(quarto);
            if (testeInsercao) {
                ContextUtils.mostrarMensagem("Inserção efetuada", "Registro inserido com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na inserção", "Falha ao inserir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na inserção", ex.getMessage(), true);
            return "falha";
        }
    }

    public CategoriaQuarto getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(CategoriaQuarto categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

}
