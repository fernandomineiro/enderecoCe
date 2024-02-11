package moduloEndereco.service.dto;

import moduloEndereco.model.LogLocalidade;

public class LogUnidadeOpDTO {

	private Integer numeroUnidadeOp;
	private String uf;
	private Integer baiNu;
	private Integer numeroLogradouro;
	private String enderecoUnidadaOp;
	private String cep;	
	private String nomeUnidadeOp;	
	private String nomeUnidadeOpAbreviada;	
	private String unidadeOpInCp;	
	private LogLocalidade numeroLocalidade;
	public Integer getNumeroUnidadeOp() {
		return numeroUnidadeOp;
	}
	public void setNumeroUnidadeOp(Integer numeroUnidadeOp) {
		this.numeroUnidadeOp = numeroUnidadeOp;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Integer getBaiNu() {
		return baiNu;
	}
	public void setBaiNu(Integer baiNu) {
		this.baiNu = baiNu;
	}
	public Integer getNumeroLogradouro() {
		return numeroLogradouro;
	}
	public void setNumeroLogradouro(Integer numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}
	public String getEnderecoUnidadaOp() {
		return enderecoUnidadaOp;
	}
	public void setEnderecoUnidadaOp(String enderecoUnidadaOp) {
		this.enderecoUnidadaOp = enderecoUnidadaOp;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNomeUnidadeOp() {
		return nomeUnidadeOp;
	}
	public void setNomeUnidadeOp(String nomeUnidadeOp) {
		this.nomeUnidadeOp = nomeUnidadeOp;
	}
	public String getNomeUnidadeOpAbreviada() {
		return nomeUnidadeOpAbreviada;
	}
	public void setNomeUnidadeOpAbreviada(String nomeUnidadeOpAbreviada) {
		this.nomeUnidadeOpAbreviada = nomeUnidadeOpAbreviada;
	}
	public String getUnidadeOpInCp() {
		return unidadeOpInCp;
	}
	public void setUnidadeOpInCp(String unidadeOpInCp) {
		this.unidadeOpInCp = unidadeOpInCp;
	}
	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}
	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}
	
	
	
}
