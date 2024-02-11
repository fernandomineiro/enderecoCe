package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_FAIXA_BAIRRO")
public class LogFaixaBairro {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogFaixaBairro idLogFaixaBairro;

	@Column(name = "FCB_CEP_FIM")
	private String fbcCepFim;

	public IdLogFaixaBairro getIdLogFaixaBairro() {
		return idLogFaixaBairro;
	}

	public void setIdLogFaixaBairro(IdLogFaixaBairro idLogFaixaBairro) {
		this.idLogFaixaBairro = idLogFaixaBairro;
	}

	public String getFbcCepFim() {
		return fbcCepFim;
	}

	public void setFbcCepFim(String fbcCepFim) {
		this.fbcCepFim = fbcCepFim;
	}

}
