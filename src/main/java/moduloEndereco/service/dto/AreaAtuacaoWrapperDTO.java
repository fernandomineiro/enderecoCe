package moduloEndereco.service.dto;

import java.util.List;

public class AreaAtuacaoWrapperDTO {

	private List<AreaAtuacaoDTO>  listAreaAtuacao;
	private Integer totalRegistros;
	
	public List<AreaAtuacaoDTO> getListAreaAtuacao() {
		return listAreaAtuacao;
	}
	public void setListAreaAtuacao(List<AreaAtuacaoDTO> listAreaAtuacao) {
		this.listAreaAtuacao = listAreaAtuacao;
	}
	public Integer getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
	
}
