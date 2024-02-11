package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import moduloEndereco.util.ConvertObjectToJsonCesan;

@Entity
@Table(name = "HORARIO_ATENDIMENTO")
public class HorarioAtendimento {
	
	@Id
	@Column(name = "CD_HORARIO_ATENDIMENTO")
	private Short cdHorarioAtendimento;
	
	@Column(name = "DESCRICAO", length = 100, nullable = false)
	private String descricao;
	

	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);
	}

	@Override
	public String toString() {
		return this.cdHorarioAtendimento.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdHorarioAtendimento == null) ? 0 : cdHorarioAtendimento.hashCode());
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
		HorarioAtendimento other = (HorarioAtendimento) obj;
		if (cdHorarioAtendimento == null) {
			if (other.cdHorarioAtendimento != null)
				return false;
		} else if (!cdHorarioAtendimento.equals(other.cdHorarioAtendimento))
			return false;
		return true;
	}

	public Short getCdHorarioAtendimento() {
		return cdHorarioAtendimento;
	}

	public void setCdHorarioAtendimento(Short cdHorarioAtendimento) {
		this.cdHorarioAtendimento = cdHorarioAtendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
