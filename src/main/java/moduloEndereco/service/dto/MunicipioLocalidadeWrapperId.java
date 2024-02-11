package moduloEndereco.service.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class MunicipioLocalidadeWrapperId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "cdMunicipio")
	private Short cdMunicipio;
	@Column(name = "cdLocalidade")
	private Short cdLocalidade;
	
	public Short getCdMunicipio() {
		return cdMunicipio;
	}
	public void setCdMunicipio(Short cdMunicipio) {
		this.cdMunicipio = cdMunicipio;
	}
	public Short getCdLocalidade() {
		return cdLocalidade;
	}
	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}
	
}
