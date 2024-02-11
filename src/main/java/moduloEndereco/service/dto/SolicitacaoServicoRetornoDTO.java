package moduloEndereco.service.dto;

public class SolicitacaoServicoRetornoDTO {

	private Integer codAtendimento;
	private Integer refAtendimento;
	private Integer seqSS;
	private Short codLogradouro;
	private Short cdBairro;

	public Integer getCodAtendimento() {
		return codAtendimento;
	}

	public void setCodAtendimento(Integer codAtendimento) {
		this.codAtendimento = codAtendimento;
	}

	public Integer getRefAtendimento() {
		return refAtendimento;
	}

	public void setRefAtendimento(Integer refAtendimento) {
		this.refAtendimento = refAtendimento;
	}

	public Integer getSeqSS() {
		return seqSS;
	}

	public void setSeqSS(Integer seqSS) {
		this.seqSS = seqSS;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Short getCdBairro() {
		return cdBairro;
	}

	public void setCdBairro(Short cdBairro) {
		this.cdBairro = cdBairro;
	}
	
	

}
