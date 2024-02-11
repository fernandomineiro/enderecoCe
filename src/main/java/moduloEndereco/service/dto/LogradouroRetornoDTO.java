package moduloEndereco.service.dto;

import java.util.List;

public class LogradouroRetornoDTO {

	private Integer id;
	private Short codLogradouro;
	private Short codLocalidade;
	private String nomeLogradouro;
	private Long idTipoLogradouro;
	private String siglaLogradouro;
	private LocalidadeDTO localidade;
	private List<BairroDTO> listBairroDTO;
	
	public List<BairroDTO> getListBairroDTO() {
		return listBairroDTO;
	}
	public void setListBairroDTO(List<BairroDTO> listBairroDTO) {
		this.listBairroDTO = listBairroDTO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Short getCodLogradouro() {
		return codLogradouro;
	}
	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}
	public Short getCodLocalidade() {
		return codLocalidade;
	}
	public void setCodLocalidade(Short codLocalidade) {
		this.codLocalidade = codLocalidade;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public Long getIdTipoLogradouro() {
		return idTipoLogradouro;
	}
	public void setIdTipoLogradouro(Long idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}
	public String getSiglaLogradouro() {
		return siglaLogradouro;
	}
	public void setSiglaLogradouro(String siglaLogradouro) {
		this.siglaLogradouro = siglaLogradouro;
	}
	public LocalidadeDTO getLocalidade() {
		return localidade;
	}
	public void setLocalidade(LocalidadeDTO localidade) {
		this.localidade = localidade;
	}
	
	
}
