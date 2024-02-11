package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogFaixaCpc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CPC_NU")
	private Integer numeroCpc;

	@Column(name = "CPC_INICIAL")
	private String cpcIncial;

	public IdLogFaixaCpc() {

	}

	public IdLogFaixaCpc(Integer numeroCpc, String cpcIncial) {
		super();
		this.numeroCpc = numeroCpc;
		this.cpcIncial = cpcIncial;
	}

	public Integer getNumeroCpc() {
		return numeroCpc;
	}

	public void setNumeroCpc(Integer numeroCpc) {
		this.numeroCpc = numeroCpc;
	}

	public String getCpcIncial() {
		return cpcIncial;
	}

	public void setCpcIncial(String cpcIncial) {
		this.cpcIncial = cpcIncial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpcIncial == null) ? 0 : cpcIncial.hashCode());
		result = prime * result + ((numeroCpc == null) ? 0 : numeroCpc.hashCode());
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
		IdLogFaixaCpc other = (IdLogFaixaCpc) obj;
		if (cpcIncial == null) {
			if (other.cpcIncial != null)
				return false;
		} else if (!cpcIncial.equals(other.cpcIncial))
			return false;
		if (numeroCpc == null) {
			if (other.numeroCpc != null)
				return false;
		} else if (!numeroCpc.equals(other.numeroCpc))
			return false;
		return true;
	}

	
}
