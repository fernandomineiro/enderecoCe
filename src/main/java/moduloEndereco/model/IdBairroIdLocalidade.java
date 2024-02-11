package moduloEndereco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdBairroIdLocalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name="CD_BAIRRO")
	private Short cdBairro;
	@Column(name="CD_CIDADE")
	private Short cdLocalidade;
	
	
	public IdBairroIdLocalidade() {
		
	}
	
	public IdBairroIdLocalidade(Short cdBairro, Short cdLocalidade) {
		this.cdBairro = cdBairro;
		this.cdLocalidade = cdLocalidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdBairro == null) ? 0 : cdBairro.hashCode());
		result = prime * result + ((cdLocalidade == null) ? 0 : cdLocalidade.hashCode());
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
		IdBairroIdLocalidade other = (IdBairroIdLocalidade) obj;
		if (cdBairro == null) {
			if (other.cdBairro != null)
				return false;
		} else if (!cdBairro.equals(other.cdBairro))
			return false;
		if (cdLocalidade == null) {
			if (other.cdLocalidade != null)
				return false;
		} else if (!cdLocalidade.equals(other.cdLocalidade))
			return false;
		return true;
	}
	public Short getCdBairro() {
		return cdBairro;
	}
	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}
	public Short getCdLocalidade() {
		return cdLocalidade;
	}
	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}
	
	
}
