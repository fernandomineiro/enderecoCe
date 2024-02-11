package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogFaixaUop implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "UOP_NU")
	private Integer numeroUop;

	@Column(name = "FNC_INICIAL")
	private Integer fncIncial;

	public IdLogFaixaUop() {

	}

	public IdLogFaixaUop(Integer numeroUop, Integer fncIncial) {
		super();
		this.numeroUop = numeroUop;
		this.fncIncial = fncIncial;
	}

	public Integer getNumeroUop() {
		return numeroUop;
	}

	public void setNumeroUop(Integer numeroUop) {
		this.numeroUop = numeroUop;
	}

	public Integer getFncIncial() {
		return fncIncial;
	}

	public void setFncIncial(Integer fncIncial) {
		this.fncIncial = fncIncial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fncIncial == null) ? 0 : fncIncial.hashCode());
		result = prime * result + ((numeroUop == null) ? 0 : numeroUop.hashCode());
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
		IdLogFaixaUop other = (IdLogFaixaUop) obj;
		if (fncIncial == null) {
			if (other.fncIncial != null)
				return false;
		} else if (!fncIncial.equals(other.fncIncial))
			return false;
		if (numeroUop == null) {
			if (other.numeroUop != null)
				return false;
		} else if (!numeroUop.equals(other.numeroUop))
			return false;
		return true;
	}

}
