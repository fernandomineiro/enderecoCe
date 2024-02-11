package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_VAR_LOG")
public class LogVarLog {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogVarLog idLogVarLog;

	@Column(name = "TLO_TX")
	private String tloTx;

	@Column(name = "VLO_TX")
	private String vloTx;

	public IdLogVarLog getIdLogVarLog() {
		return idLogVarLog;
	}

	public void setIdLogVarLog(IdLogVarLog idLogVarLog) {
		this.idLogVarLog = idLogVarLog;
	}

	public String getTloTx() {
		return tloTx;
	}

	public void setTloTx(String tloTx) {
		this.tloTx = tloTx;
	}

	public String getVloTx() {
		return vloTx;
	}

	public void setVloTx(String vloTx) {
		this.vloTx = vloTx;
	}

}
