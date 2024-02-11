package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_VAR_LOC")
public class LogVarLoc {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogVarLoc idLogVarLoc;

	@Column(name = "VAL_TX")
	private String valTx;

	public IdLogVarLoc getIdLogVarLoc() {
		return idLogVarLoc;
	}

	public void setIdLogVarLoc(IdLogVarLoc idLogVarLoc) {
		this.idLogVarLoc = idLogVarLoc;
	}

	public String getValTx() {
		return valTx;
	}

	public void setValTx(String valTx) {
		this.valTx = valTx;
	}

	
}
