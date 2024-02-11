package moduloEndereco.service.dto;

import java.util.List;

public class AreaAtuacaoBairroDTO {

	private Long idAreaAtuacao;
	private String nomeAreaAtuacao;
	private List<BairroLocalidadeDTO> listBairroLocalidade;

	public Long getIdAreaAtuacao() {
		return idAreaAtuacao;
	}

	public void setIdAreaAtuacao(Long idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}

	public String getNomeAreaAtuacao() {
		return nomeAreaAtuacao;
	}

	public void setNomeAreaAtuacao(String nomeAreaAtuacao) {
		this.nomeAreaAtuacao = nomeAreaAtuacao;
	}

	public List<BairroLocalidadeDTO> getListBairroLocalidade() {
		return listBairroLocalidade;
	}

	public void setListBairroLocalidade(List<BairroLocalidadeDTO> listBairroLocalidade) {
		this.listBairroLocalidade = listBairroLocalidade;
	}

	
}
