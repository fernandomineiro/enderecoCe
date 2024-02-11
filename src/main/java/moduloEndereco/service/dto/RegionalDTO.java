package moduloEndereco.service.dto;

public class RegionalDTO {

	private Integer codRegional;
	private String nomeRegional;
	private String status;
	
	
	public Integer getCodRegional() {
		return codRegional;
	}
	public void setCodRegional(Integer codRegional) {
		this.codRegional = codRegional;
	}
	public String getNomeRegional() {
		return nomeRegional;
	}
	public void setNomeRegional(String nomeRegional) {
		this.nomeRegional = nomeRegional;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
