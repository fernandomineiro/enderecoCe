package moduloEndereco.service.dto;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

public class BairroDropDownDTO {

	
	private Short cdBairro;
	private Short cdLocalidade;
	private String nomeBairro;
	@JsonCesanNotSerializable
	private String idConcatenada;
	
	
	public BairroDropDownDTO() {
		
	}

	public BairroDropDownDTO(Short cdBairro, Short cdLocalidade, String nomeBairro, String idConcatenada) {
		super();
		this.cdBairro = cdBairro;
		this.cdLocalidade = cdLocalidade;
		this.nomeBairro = nomeBairro;
		this.idConcatenada = idConcatenada;
	}

	public Short getCdBairro() {
		return cdBairro;
	}

	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}

	public Short getCdLocalidade() {
		return cdLocalidade;
	}

	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getIdConcatenada() {
		return cdBairro + "_" + cdLocalidade;
	}

	public void setIdConcatenada(String idConcatenada) {
		this.idConcatenada = idConcatenada;
	}

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}
	
	

}
