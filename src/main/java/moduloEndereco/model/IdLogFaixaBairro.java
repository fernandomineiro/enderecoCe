package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLogFaixaBairro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BAI_NU")
	private Integer numeroBairro;

	@Column(name = "FCB_CEP_INI")
	private String fcbCepIncio;

	public IdLogFaixaBairro() {

	}

	public IdLogFaixaBairro(Integer numeroBairro, String fcbCepIncio) {
		super();
		this.numeroBairro = numeroBairro;
		this.fcbCepIncio = fcbCepIncio;
	}

	public Integer getNumeroBairro() {
		return numeroBairro;
	}

	public void setNumeroBairro(Integer numeroBairro) {
		this.numeroBairro = numeroBairro;
	}

	

	public String getFcbCepIncio() {
		return fcbCepIncio;
	}

	public void setFcbCepIncio(String fcbCepIncio) {
		this.fcbCepIncio = fcbCepIncio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fcbCepIncio == null) ? 0 : fcbCepIncio.hashCode());
		result = prime * result + ((numeroBairro == null) ? 0 : numeroBairro.hashCode());
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
		IdLogFaixaBairro other = (IdLogFaixaBairro) obj;
		if (fcbCepIncio == null) {
			if (other.fcbCepIncio != null)
				return false;
		} else if (!fcbCepIncio.equals(other.fcbCepIncio))
			return false;
		if (numeroBairro == null) {
			if (other.numeroBairro != null)
				return false;
		} else if (!numeroBairro.equals(other.numeroBairro))
			return false;
		return true;
	}

}
