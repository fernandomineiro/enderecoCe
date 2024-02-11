package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogFaixaLocalidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "LOC_NU")
	private Integer numeroLocalidade;

	@Column(name = "LOC_CEP_INI")
	private String locCepInci;

	public IdLogFaixaLocalidade() {

	}

	public IdLogFaixaLocalidade(Integer numeroLocalidade, String locCepInci) {
		super();
		this.numeroLocalidade = numeroLocalidade;
		this.locCepInci = locCepInci;
	}

	public Integer getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(Integer numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

	public String getLocCepInci() {
		return locCepInci;
	}

	public void setLocCepInci(String locCepInci) {
		this.locCepInci = locCepInci;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locCepInci == null) ? 0 : locCepInci.hashCode());
		result = prime * result + ((numeroLocalidade == null) ? 0 : numeroLocalidade.hashCode());
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
		IdLogFaixaLocalidade other = (IdLogFaixaLocalidade) obj;
		if (locCepInci == null) {
			if (other.locCepInci != null)
				return false;
		} else if (!locCepInci.equals(other.locCepInci))
			return false;
		if (numeroLocalidade == null) {
			if (other.numeroLocalidade != null)
				return false;
		} else if (!numeroLocalidade.equals(other.numeroLocalidade))
			return false;
		return true;
	}

}
