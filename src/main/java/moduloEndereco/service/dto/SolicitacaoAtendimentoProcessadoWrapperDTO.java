package moduloEndereco.service.dto;

import java.util.List;

public class SolicitacaoAtendimentoProcessadoWrapperDTO {

	private List<ServicoAtendimentoProcessadoDTO> listServicoAtendimentoProcessadoDTO;
	private Long totalRegistros;
	
	public List<ServicoAtendimentoProcessadoDTO> getListServicoAtendimentoProcessadoDTO() {
		return listServicoAtendimentoProcessadoDTO;
	}
	public void setListServicoAtendimentoProcessadoDTO(
			List<ServicoAtendimentoProcessadoDTO> listServicoAtendimentoProcessadoDTO) {
		this.listServicoAtendimentoProcessadoDTO = listServicoAtendimentoProcessadoDTO;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
}
