package moduloEndereco.service.dto;

import java.time.LocalDateTime;

public class ServicoAtendimentoGisDTO {

	private Integer matriculaImovel;
	private Short dv;
	private String localidade;

	private Short cdLocalidade;

	private Short cdBairroAntigo;
	private String descricaoBairroAntigo;
	private Short cdBairroNovo;
	private String descricaoBairroNovo;
	private Short cdLogradouroAntigo;
	private String descricaoLogradouroAntigo;
	private Short cdLogradouroNovo;
	private String descricaoLogradouroNovo;
	private LocalDateTime dataInclusao;
	private LocalDateTime dataExcucao;
	private Short sequenciaInclusao;
	private Short situacao;
	private String statusProcessamento;

	private Integer cdAtendimento;
	private Integer refAtendimento;
	private Integer sequencial;

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getDv() {
		return dv;
	}

	public void setDv(Short dv) {
		this.dv = dv;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Short getCdLocalidade() {
		return cdLocalidade;
	}

	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}

	public Short getCdBairroAntigo() {
		return cdBairroAntigo;
	}

	public void setCdBairroAntigo(Short cdBairroAntigo) {
		this.cdBairroAntigo = cdBairroAntigo;
	}

	public String getDescricaoBairroAntigo() {
		return descricaoBairroAntigo;
	}

	public void setDescricaoBairroAntigo(String descricaoBairroAntigo) {
		this.descricaoBairroAntigo = descricaoBairroAntigo;
	}

	public Short getCdBairroNovo() {
		return cdBairroNovo;
	}

	public void setCdBairroNovo(Short cdBairroNovo) {
		this.cdBairroNovo = cdBairroNovo;
	}

	public String getDescricaoBairroNovo() {
		return descricaoBairroNovo;
	}

	public void setDescricaoBairroNovo(String descricaoBairroNovo) {
		this.descricaoBairroNovo = descricaoBairroNovo;
	}

	public Short getCdLogradouroAntigo() {
		return cdLogradouroAntigo;
	}

	public void setCdLogradouroAntigo(Short cdLogradouroAntigo) {
		this.cdLogradouroAntigo = cdLogradouroAntigo;
	}

	public String getDescricaoLogradouroAntigo() {
		return descricaoLogradouroAntigo;
	}

	public void setDescricaoLogradouroAntigo(String descricaoLogradouroAntigo) {
		this.descricaoLogradouroAntigo = descricaoLogradouroAntigo;
	}

	public Short getCdLogradouroNovo() {
		return cdLogradouroNovo;
	}

	public void setCdLogradouroNovo(Short cdLogradouroNovo) {
		this.cdLogradouroNovo = cdLogradouroNovo;
	}

	public String getDescricaoLogradouroNovo() {
		return descricaoLogradouroNovo;
	}

	public void setDescricaoLogradouroNovo(String descricaoLogradouroNovo) {
		this.descricaoLogradouroNovo = descricaoLogradouroNovo;
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDateTime getDataExcucao() {
		return dataExcucao;
	}

	public void setDataExcucao(LocalDateTime dataExcucao) {
		this.dataExcucao = dataExcucao;
	}

	public Short getSequenciaInclusao() {
		return sequenciaInclusao;
	}

	public void setSequenciaInclusao(Short sequenciaInclusao) {
		this.sequenciaInclusao = sequenciaInclusao;
	}

	public Short getSituacao() {
		return situacao;
	}

	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	public String getStatusProcessamento() {
		return statusProcessamento;
	}

	public void setStatusProcessamento(String statusProcessamento) {
		this.statusProcessamento = statusProcessamento;
	}

	public Integer getCdAtendimento() {
		return cdAtendimento;
	}

	public void setCdAtendimento(Integer cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}

	public Integer getRefAtendimento() {
		return refAtendimento;
	}

	public void setRefAtendimento(Integer refAtendimento) {
		this.refAtendimento = refAtendimento;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

}
