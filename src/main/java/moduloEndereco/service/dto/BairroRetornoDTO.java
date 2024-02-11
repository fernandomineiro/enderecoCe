package moduloEndereco.service.dto;

public class BairroRetornoDTO {

	private Short cdBairro;
	private LocalidadeDTO localidadeDTO;
	private String nomeBairro;
	private MunicipioDTO municipioDTO;
	
	public Short getCdBairro() {
		return cdBairro;
	}
	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}
	public LocalidadeDTO getLocalidadeDTO() {
		return localidadeDTO;
	}
	public void setLocalidadeDTO(LocalidadeDTO localidadeDTO) {
		this.localidadeDTO = localidadeDTO;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public MunicipioDTO getMunicipioDTO() {
		return municipioDTO;
	}
	public void setMunicipioDTO(MunicipioDTO municipioDTO) {
		this.municipioDTO = municipioDTO;
	}
	
	
 
}
