package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogVarLoc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "LOC_NU")
	private Integer locNu;

	@Column(name = "VAL_NU")
	private Integer valNu;

	public IdLogVarLoc() {

	}

	public IdLogVarLoc(Integer locNu, Integer valNu) {
		super();
		this.locNu = locNu;
		this.valNu = valNu;
	}

	public Integer getLocNu() {
		return locNu;
	}

	public void setLocNu(Integer locNu) {
		this.locNu = locNu;
	}

	public Integer getValNu() {
		return valNu;
	}

	public void setValNu(Integer valNu) {
		this.valNu = valNu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locNu == null) ? 0 : locNu.hashCode());
		result = prime * result + ((valNu == null) ? 0 : valNu.hashCode());
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
		IdLogVarLoc other = (IdLogVarLoc) obj;
		if (locNu == null) {
			if (other.locNu != null)
				return false;
		} else if (!locNu.equals(other.locNu))
			return false;
		if (valNu == null) {
			if (other.valNu != null)
				return false;
		} else if (!valNu.equals(other.valNu))
			return false;
		return true;
	}

	
}
