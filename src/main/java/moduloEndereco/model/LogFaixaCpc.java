package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_FAIXA_CPC")
public class LogFaixaCpc {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogFaixaCpc idLogFaixaCpc;

	@Column(name = "CPC_FINAL")
	private String cpcFinal;

	public IdLogFaixaCpc getIdLogFaixaCpc() {
		return idLogFaixaCpc;
	}

	public void setIdLogFaixaCpc(IdLogFaixaCpc idLogFaixaCpc) {
		this.idLogFaixaCpc = idLogFaixaCpc;
	}

	public String getCpcFinal() {
		return cpcFinal;
	}

	public void setCpcFinal(String cpcFinal) {
		this.cpcFinal = cpcFinal;
	}

}
