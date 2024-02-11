package moduloEndereco.service.dto;

import moduloEndereco.util.ConvertObjectToJsonCesan;

public class ImovelLogradouroDTO {

	private Integer matriculaImovel;
	private Short dv;
	private Short codLogradouro;
	private Short codBairro;

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}

	public Short getDv() {
		return dv;
	}

	public void setDv(Short dv) {
		this.dv = dv;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Short getCodBairro() {
		return codBairro;
	}

	public void setCodBairro(Short codBairro) {
		this.codBairro = codBairro;
	}

	
}
