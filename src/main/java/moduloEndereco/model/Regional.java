package moduloEndereco.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name ="CDARE")
public class Regional {
	
	@Id
	@Column(name = "CD_REGIONAL")
	private Integer codRegional;
	
	@Column(name = "DC_REGIONAL")
	@Size(min = 0, max = 25)
	@NotNull
	private String nomeRegional;
	
	@Column(name = "MAINT")
	@Size(min = 0, max = 1)
	@JsonCesanNotSerializable
	private String status;
	
	@Column(name = "DATA_HORA_EXCLUSAO")
	@JsonCesanNotSerializable
	private Instant dataRemocao;
	


	public Integer getCodRegional() {
		return codRegional;
	}

	public void setCodRegional(Integer codRegional) {
		this.codRegional = codRegional;
	}

	public String getNomeRegional() {
		return nomeRegional;
	}

	public void setNomeRegional(String nomeRegional) {
		this.nomeRegional = nomeRegional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Instant dataRemoção) {
		this.dataRemocao = dataRemoção;
	}
	
	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);	
	}
	
	@Override
	public String toString() {
		return "codRegional";
	}

}
