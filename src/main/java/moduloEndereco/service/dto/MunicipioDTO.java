package moduloEndereco.service.dto;

public class MunicipioDTO {

	private Short cdCidade;
	private String dcCidade;
    private Integer cdRegional;
	
	public Integer getCdRegional() {
		return cdRegional;
	}
	public void setCdRegional(Integer cdRegional) {
		this.cdRegional = cdRegional;
	}
	public Short getCdCidade() {
		return cdCidade;
	}
	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}
	public String getDcCidade() {
		return dcCidade;
	}
	public void setDcCidade(String dcCidade) {
		this.dcCidade = dcCidade;
	}
	
}
