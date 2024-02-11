package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.ServicoAtendimentoGisFilter;
import moduloEndereco.service.dto.ServicoAtendimentoGisDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessadoWrapperDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessamentoWrapperDTO;

public interface ServicoAtendimentoService {

	public SolicitacaoAtendimentoProcessamentoWrapperDTO buscarProcessamento(Pageable pageable);

	public SolicitacaoAtendimentoProcessadoWrapperDTO buscarProcessado(Pageable pageable);

	public List<ServicoAtendimentoGisDTO> filtrarSS(ServicoAtendimentoGisFilter enderecoGisSSFilter);

}
