package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogVarBai implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BAI_NU")
	private Integer baiNu;

	@Column(name = "VDB_NU")
	private Integer vdbNu;

	public IdLogVarBai() {

	}

	public IdLogVarBai(Integer baiNu, Integer vdbNu) {
		super();
		this.baiNu = baiNu;
		this.vdbNu = vdbNu;
	}

	public Integer getBaiNu() {
		return baiNu;
	}

	public void setBaiNu(Integer baiNu) {
		this.baiNu = baiNu;
	}

	public Integer getVdbNu() {
		return vdbNu;
	}

	public void setVdbNu(Integer vdbNu) {
		this.vdbNu = vdbNu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baiNu == null) ? 0 : baiNu.hashCode());
		result = prime * result + ((vdbNu == null) ? 0 : vdbNu.hashCode());
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
		IdLogVarBai other = (IdLogVarBai) obj;
		if (baiNu == null) {
			if (other.baiNu != null)
				return false;
		} else if (!baiNu.equals(other.baiNu))
			return false;
		if (vdbNu == null) {
			if (other.vdbNu != null)
				return false;
		} else if (!vdbNu.equals(other.vdbNu))
			return false;
		return true;
	}

}
