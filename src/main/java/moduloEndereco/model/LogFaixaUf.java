package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_FAIXA_UF")
public class LogFaixaUf {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogFaixaUf  idLogFaixaUf ;

	@Column(name = "UFE_CEP_FIM")
	private String ufCepFim;

	public IdLogFaixaUf getIdLogFaixaUf() {
		return idLogFaixaUf;
	}

	public void setIdLogFaixaUf(IdLogFaixaUf idLogFaixaUf) {
		this.idLogFaixaUf = idLogFaixaUf;
	}

	public String getUfCepFim() {
		return ufCepFim;
	}

	public void setUfCepFim(String ufCepFim) {
		this.ufCepFim = ufCepFim;
	}

	
	
}
