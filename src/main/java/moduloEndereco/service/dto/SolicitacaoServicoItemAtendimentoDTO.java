package moduloEndereco.service.dto;

public class SolicitacaoServicoItemAtendimentoDTO {

	private Integer cdAtendimento;
	private Integer refAtendimento;
	private Integer sequencial;
	private String origemMatricula;
	private Integer matricula;
	private Short cdBairro;
	private Short codLogradouro;
	

	public Short getCdBairro() {
		return cdBairro;
	}
	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}
	public Short getCodLogradouro() {
		return codLogradouro;
	}
	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
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
	public String getOrigemMatricula() {
		return origemMatricula;
	}
	public void setOrigemMatricula(String origemMatricula) {
		this.origemMatricula = origemMatricula;
	}
	
	
}
