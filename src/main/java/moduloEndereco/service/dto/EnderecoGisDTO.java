package moduloEndereco.service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

public class EnderecoGisDTO {

	private Long id;
	private Integer matriculaImovel;
	private Integer dv;
	private Short cdBairroAntigo;
	private String descricaoBairroAntigo;
	private Short cdLogradouroAntigo;
	private String descricaoLogradouroAntigo;
	private Short cdBairroNovo;
	private String descricaoBairroNovo;
	private Short cdLogradouroNovo;
	private String descricaoLogradouroNovo;
	@JsonIgnore
	private Integer dtInclusao;

	//@JsonIgnore
	private Short horaExecucao;
	//@JsonIgnore
	private Short minutoExecucao;
	@JsonIgnore
	private String usuario;
	
	private Integer dataExecucao;
	private Short horaInclusao;
	private Short minutoInclusao;
	private Short sequenciaInclusao;
	private Short situacao;
	
	@JsonCesanNotSerializable
	private LocalDate dataInclusao;
	
	@JsonCesanNotSerializable
	private LocalDate dataExecucaoD;
	
	@JsonIgnore
	private Short cdLocalidade;
	private String localidade;

	public Short getHoraExecucao() {
		return horaExecucao;
	}

	public void setHoraExecucao(Short horaExecucao) {
		this.horaExecucao = horaExecucao;
	}

	public Short getMinutoExecucao() {
		return minutoExecucao;
	}

	public void setMinutoExecucao(Short minutoExecucao) {
		this.minutoExecucao = minutoExecucao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Integer dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	
	
	public LocalDate getDataExecucaoD() {
		return dataExecucaoD;
	}

	public void setDataExecucaoD(LocalDate dataExecucaoD) {
		this.dataExecucaoD = dataExecucaoD;
	}

	public Integer getDv() {
		return dv;
	}

	public void setDv(Integer dv) {
		this.dv = dv;
	}

	public String getDescricaoBairroAntigo() {
		return descricaoBairroAntigo;
	}

	public void setDescricaoBairroAntigo(String descricaoBairroAntigo) {
		this.descricaoBairroAntigo = descricaoBairroAntigo;
	}

	public String getDescricaoLogradouroAntigo() {
		return descricaoLogradouroAntigo;
	}

	public void setDescricaoLogradouroAntigo(String descricaoLogradouroAntigo) {
		this.descricaoLogradouroAntigo = descricaoLogradouroAntigo;
	}

	public String getDescricaoBairroNovo() {
		return descricaoBairroNovo;
	}

	public void setDescricaoBairroNovo(String descricaoBairroNovo) {
		this.descricaoBairroNovo = descricaoBairroNovo;
	}

	public String getDescricaoLogradouroNovo() {
		return descricaoLogradouroNovo;
	}

	public void setDescricaoLogradouroNovo(String descricaoLogradouroNovo) {
		this.descricaoLogradouroNovo = descricaoLogradouroNovo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getCdBairroAntigo() {
		return cdBairroAntigo;
	}

	public void setCdBairroAntigo(Short cdBairroAntigo) {
		this.cdBairroAntigo = cdBairroAntigo;
	}

	public Short getCdLogradouroAntigo() {
		return cdLogradouroAntigo;
	}

	public void setCdLogradouroAntigo(Short cdLogradouroAntigo) {
		this.cdLogradouroAntigo = cdLogradouroAntigo;
	}

	public Short getCdBairroNovo() {
		return cdBairroNovo;
	}

	public void setCdBairroNovo(Short cdBairroNovo) {
		this.cdBairroNovo = cdBairroNovo;
	}

	public Short getCdLogradouroNovo() {
		return cdLogradouroNovo;
	}

	public void setCdLogradouroNovo(Short cdLogradouroNovo) {
		this.cdLogradouroNovo = cdLogradouroNovo;
	}

	public Integer getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Integer dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Short getHoraInclusao() {
		return horaInclusao;
	}

	public void setHoraInclusao(Short horaInclusao) {
		this.horaInclusao = horaInclusao;
	}

	public Short getMinutoInclusao() {
		return minutoInclusao;
	}

	public void setMinutoInclusao(Short minutoInclusao) {
		this.minutoInclusao = minutoInclusao;
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

	public Short getCdLocalidade() {
		return cdLocalidade;
	}

	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

}
