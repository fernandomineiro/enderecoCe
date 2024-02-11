package moduloEndereco.service.dto;

import moduloEndereco.util.ConvertObjectToJsonCesan;

public class BairroLocalidadeDTO {

	private Short cdBairro;
	private Short cdLocalidade;


	public Short getCdBairro() {
		return cdBairro;
	}

	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}

	public Short getCdLocalidade() {
		return cdLocalidade;
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
		BairroLocalidadeDTO other = (BairroLocalidadeDTO) obj;
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

	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}
	
}
