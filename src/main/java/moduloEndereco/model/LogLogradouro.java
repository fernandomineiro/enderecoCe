package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_LOGRADOURO")
public class LogLogradouro {

	@Id
	@Column(name = "LOG_NU")
	private Integer numeroLogradouro;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "BAI_NU_INI")
	private Integer baiNuIni;

	@Column(name = "BAI_NU_FIM")
	private Integer baiNuFim;

	@Column(name = "LOG_NO")
	private String logradouro;

	@Column(name = "LOG_COMPLEMENTO")
	private String complementoLogradouro;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "TLO_TX")
	private String tloTx;

	@Column(name = "LOG_STA_TLO")
	private String logStaTlo;

	@Column(name = "LOG_NO_ABREV")
	private String logradouroAbreviado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOC_NU")
	private LogLocalidade numeroLocalidade;

	public Integer getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(Integer numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getBaiNuIni() {
		return baiNuIni;
	}

	public void setBaiNuIni(Integer baiNuIni) {
		this.baiNuIni = baiNuIni;
	}

	public Integer getBaiNuFim() {
		return baiNuFim;
	}

	public void setBaiNuFim(Integer baiNuFim) {
		this.baiNuFim = baiNuFim;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTloTx() {
		return tloTx;
	}

	public void setTloTx(String tloTx) {
		this.tloTx = tloTx;
	}

	public String getLogStaTlo() {
		return logStaTlo;
	}

	public void setLogStaTlo(String logStaTlo) {
		this.logStaTlo = logStaTlo;
	}

	public String getLogradouroAbreviado() {
		return logradouroAbreviado;
	}

	public void setLogradouroAbreviado(String logradouroAbreviado) {
		this.logradouroAbreviado = logradouroAbreviado;
	}

	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

}