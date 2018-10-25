package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IRelatorioDespesasDAO;
import br.cefetmg.inf.hosten.model.persistence.jdbc.RelatorioDespesasDAO;
import java.util.Map;

public class RelatorioDespesasDAOAdapter implements IRelatorioDespesasDAO {
    
    private static IRelatorioDespesasDAO instancia;
    
    public static synchronized IRelatorioDespesasDAO getInstance() {
        if (instancia == null) {
            instancia  = RelatorioDespesasDAO.getInstance();
        }
        return instancia;
    }
    
    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        return instancia.busca(seqHospedagem, nroQuarto);
    }
    
    @Override
    public Map<String, Object> retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) 
            throws SQLException {
        return instancia.retornaRelatorioDespesas(seqHospedagem, nroQuarto);
    }
}