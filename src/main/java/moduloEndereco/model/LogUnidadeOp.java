package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_UNID_OPER")
public class LogUnidadeOp {

	@Id
	@Column(name = "UOP_NU")
	private Integer numeroUnidadeOp;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "BAI_NU")
	private Integer baiNu;

	@Column(name = "LOG_NU")
	private Integer numeroLogradouro;

	@Column(name = "UOP_ENDERECO")
	private String enderecoUnidadaOp;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "UOP_NO")
	private String nomeUnidadeOp;

	@Column(name = "UOP_NO_ABREV")
	private String nomeUnidadeOpAbreviada;

	@Column(name = "UOP_IN_CP")
	private String unidadeOpInCp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOC_NU")
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
