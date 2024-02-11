package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogFaixaUf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "UFE_CEP_INI")
	private String UfCepInci;

	public IdLogFaixaUf() {

	}

	public IdLogFaixaUf(String uf, String ufCepInci) {
		super();
		this.uf = uf;
		UfCepInci = ufCepInci;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUfCepInci() {
		return UfCepInci;
	}

	public void setUfCepInci(String ufCepInci) {
		UfCepInci = ufCepInci;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UfCepInci == null) ? 0 : UfCepInci.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		IdLogFaixaUf other = (IdLogFaixaUf) obj;
		if (UfCepInci == null) {
			if (other.UfCepInci != null)
				return false;
		} else if (!UfCepInci.equals(other.UfCepInci))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

}
