
package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "LOG_VAR_BAI")
public class LogVarBai {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogVarBai idLogVarBai;

	@Column(name = "VDB_TX")
	private String vdbTx;

	public IdLogVarBai getIdLogVarBai() {
		return idLogVarBai;
	}

	public void setIdLogVarBai(IdLogVarBai idLogVarBai) {
		this.idLogVarBai = idLogVarBai;
	}

	public String getVdbTx() {
		return vdbTx;
	}

	public void setVdbTx(String vdbTx) {
		this.vdbTx = vdbTx;
	}

}
