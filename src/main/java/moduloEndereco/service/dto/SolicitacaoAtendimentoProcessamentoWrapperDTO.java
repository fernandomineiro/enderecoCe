package moduloEndereco.service.dto;

import java.util.List;

public class SolicitacaoAtendimentoProcessamentoWrapperDTO {

	private List<ServicoAtendimentoProcessamentoDTO> listServicoAtendimentoProcessamentoDTO;
	private Long totalRegistros;
	
	
	
	public List<ServicoAtendimentoProcessamentoDTO> getListServicoAtendimentoProcessamentoDTO() {
		return listServicoAtendimentoProcessamentoDTO;
	}
	public void setListServicoAtendimentoProcessamentoDTO(
			List<ServicoAtendimentoProcessamentoDTO> listServicoAtendimentoProcessamentoDTO) {
		this.listServicoAtendimentoProcessamentoDTO = listServicoAtendimentoProcessamentoDTO;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
}
