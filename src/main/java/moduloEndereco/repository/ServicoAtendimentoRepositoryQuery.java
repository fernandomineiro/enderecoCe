package moduloEndereco.repository;

import java.util.List;

import moduloEndereco.model.ServicoAtendimento;
import moduloEndereco.repository.filter.ServicoAtendimentoGisFilter;

public interface ServicoAtendimentoRepositoryQuery {

	public List<ServicoAtendimento> filtrarSS(ServicoAtendimentoGisFilter enderecoGisSSFilter);
}
