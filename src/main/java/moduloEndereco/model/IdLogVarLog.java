package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogVarLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "LOG_NU")
	private Integer logNu;

	@Column(name = "VLO_NU")
	private Integer vloNu;

	public IdLogVarLog() {
		
	}

	public IdLogVarLog(Integer logNu, Integer vloNu) {
		super();
		this.logNu = logNu;
		this.vloNu = vloNu;
	}

	public Integer getLogNu() {
		return logNu;
	}

	public void setLogNu(Integer logNu) {
		this.logNu = logNu;
	}

	public Integer getVloNu() {
		return vloNu;
	}

	public void setVloNu(Integer vloNu) {
		this.vloNu = vloNu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logNu == null) ? 0 : logNu.hashCode());
		result = prime * result + ((vloNu == null) ? 0 : vloNu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdLogVarLog other = (IdLogVarLog) obj;
		if (logNu == null) {
			if (other.logNu != null)
				return false;
		} else if (!logNu.equals(other.logNu))
			return false;
		if (vloNu == null) {
			if (other.vloNu != null)
				return false;
		} else if (!vloNu.equals(other.vloNu))
			return false;
		return true;
	}

}
