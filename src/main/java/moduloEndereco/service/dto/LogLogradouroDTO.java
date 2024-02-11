package moduloEndereco.service.dto;

import moduloEndereco.model.LogLocalidade;
import moduloEndereco.util.ConvertObjectToJsonCesan;

public class LogLogradouroDTO {

	private Integer numeroLogradouro;

	private String uf;

	private Integer baiNuIni;

	private String logradouro;

	private String complementoLogradouro;

	private String cep;

	private String tloTx;

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

	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}

}
