package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_FAIXA_LOCALIDADE")
public class LogFaixaLocalidade {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogFaixaLocalidade idLogFaixaLocalidade;

	@Column(name = "LOC_CEP_FIM")
	private String locCepFim;

	public IdLogFaixaLocalidade getIdLogFaixaLocalidade() {
		return idLogFaixaLocalidade;
	}

	public void setIdLogFaixaLocalidade(IdLogFaixaLocalidade idLogFaixaLocalidade) {
		this.idLogFaixaLocalidade = idLogFaixaLocalidade;
	}

	public String getLocCepFim() {
		return locCepFim;
	}

	public void setLocCepFim(String locCepFim) {
		this.locCepFim = locCepFim;
	}

}
