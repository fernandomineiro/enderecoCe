package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_NUM_SEC")
public class LogNumeroSec {

	@Id
	@Column(name = "LOG_NU")
	private Integer numeroLog;

	@Column(name = "SEC_NU_INI")
	private String secNuIni;

	@Column(name = "SEC_NU_FIM")
	private String secNuFim;

	@Column(name = "SEC_IN_LADO")
	private String secInLado;

	public Integer getNumeroLog() {
		return numeroLog;
	}

	public void setNumeroLog(Integer numeroLog) {
		this.numeroLog = numeroLog;
	}

	public String getSecNuIni() {
		return secNuIni;
	}

	public void setSecNuIni(String secNuIni) {
		this.secNuIni = secNuIni;
	}

	public String getSecNuFim() {
		return secNuFim;
	}

	public void setSecNuFim(String secNuFim) {
		this.secNuFim = secNuFim;
	}

	public String getSecInLado() {
		return secInLado;
	}

	public void setSecInLado(String secInLado) {
		this.secInLado = secInLado;
	}

}