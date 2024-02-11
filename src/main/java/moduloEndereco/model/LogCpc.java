package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_CPC")
public class LogCpc {

	@Id
	@Column(name = "CPC_NU")
	private Integer numeroCpc;

	@Column(name = "UFE_SG")
	private String uf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOC_NU")
	private LogLocalidade numeroLocalidade;

	@Column(name = "CPC_NO")
	private String nomeCpc;

	@Column(name = "CPC_ENDERECO")
	private String enderecoCpc;

	@Column(name = "CEP")
	private String cep;

	public Integer getNumeroCpc() {
		return numeroCpc;
	}

	public void setNumeroCpc(Integer numeroCpc) {
		this.numeroCpc = numeroCpc;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

	public String getNomeCpc() {
		return nomeCpc;
	}

	public void setNomeCpc(String nomeCpc) {
		this.nomeCpc = nomeCpc;
	}

	public String getEnderecoCpc() {
		return enderecoCpc;
	}

	public void setEnderecoCpc(String enderecoCpc) {
		this.enderecoCpc = enderecoCpc;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
