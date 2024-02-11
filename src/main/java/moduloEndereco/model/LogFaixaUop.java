package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_FAIXA_UOP")
public class LogFaixaUop {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogFaixaUop idLogFaixaUop;

	@Column(name = "FNC_FINAL")
	private Integer fncFinal;

	public IdLogFaixaUop getIdLogFaixaUop() {
		return idLogFaixaUop;
	}

	public void setIdLogFaixaUop(IdLogFaixaUop idLogFaixaUop) {
		this.idLogFaixaUop = idLogFaixaUop;
	}

	public Integer getFncFinal() {
		return fncFinal;
	}

	public void setFncFinal(Integer fncFinal) {
		this.fncFinal = fncFinal;
	}

}
