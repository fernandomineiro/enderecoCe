package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_GRANDE_USUARIO")
public class LogGrandeUsuario {

	@Id
	@Column(name = "GRU_NU")
	private Integer numeroGRU;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "BAI_NU")
	private Integer numeroBairro;

	@Column(name = "GRU_NO")
	private String nomeGRU;

	@Column(name = "GRU_ENDERECO")
	private String enderecoGRU;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "GRU_NO_ABREV")
	private String nomeAbreviadoGRU;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOC_NU")
	private LogLocalidade numeroLocalidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOG_NU")
	private LogLogradouro numeroLogradouro;

	public Integer getNumeroGRU() {
		return numeroGRU;
	}

	public void setNumeroGRU(Integer numeroGRU) {
		this.numeroGRU = numeroGRU;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getNumeroBairro() {
		return numeroBairro;
	}

	public void setNumeroBairro(Integer numeroBairro) {
		this.numeroBairro = numeroBairro;
	}

	public String getNomeGRU() {
		return nomeGRU;
	}

	public void setNomeGRU(String nomeGRU) {
		this.nomeGRU = nomeGRU;
	}

	public String getEnderecoGRU() {
		return enderecoGRU;
	}

	public void setEnderecoGRU(String enderecoGRU) {
		this.enderecoGRU = enderecoGRU;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeAbreviadoGRU() {
		return nomeAbreviadoGRU;
	}

	public void setNomeAbreviadoGRU(String nomeAbreviadoGRU) {
		this.nomeAbreviadoGRU = nomeAbreviadoGRU;
	}

	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

	public LogLogradouro getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(LogLogradouro numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

}
