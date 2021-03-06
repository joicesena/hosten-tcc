package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoConsumoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.DespesaDaoAdapter;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoConsumoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;
import java.sql.Timestamp;

public class ControlarDespesas implements IControlarDespesas {

    @Override
    public boolean inserir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        IQuartoConsumoDao quartoConsumoDAO = QuartoConsumoDaoAdapter.getInstance();
        if (quartoConsumo != null) {
            try {
                quartoConsumoDAO.adiciona(quartoConsumo);
                return true;
            } catch (SQLException e) {
            }
        } else {
            throw new NegocioException("O QuartoConsumo passado é inválido");
        }
        return false;
    }

    @Override
    public List<Despesa> listar(int seqHospedagem, short nroQuarto) throws NegocioException, SQLException {
        IDespesaDao DespesaDAO = DespesaDaoAdapter.getInstance();
        List<Despesa> despesaEncontradas = null;
        if (seqHospedagem > 0 && nroQuarto > 0) {
            try {
                despesaEncontradas = DespesaDAO.busca(seqHospedagem, nroQuarto);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new NegocioException("O 'seqHospedagem', e / ou o 'nroQuarto' é(são) inválido(s)");
        }
        return despesaEncontradas;
    }

    @Override
    public boolean excluir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        IQuartoConsumoDao quartoConsumoDAO = QuartoConsumoDaoAdapter.getInstance();
        if (quartoConsumo != null) {
            try {
                quartoConsumoDAO.deleta(
                        quartoConsumo.getQuartoHospedagem().getId().getSeqHospedagem(),
                        quartoConsumo.getQuartoHospedagem().getId().getNroQuarto(), 
                        (Timestamp) quartoConsumo.getDatConsumo());
                return true;
            } catch (SQLException e) {
            }
        } else {
            throw new NegocioException("O QuartoConsumo passado é inválido");
        }
        return false;
    }
}
