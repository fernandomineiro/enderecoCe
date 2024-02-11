package moduloEndereco.service.dto;

public class ItemAtendimentoRetornoDTO {

	private Integer codAtendimento;
	private Integer refAtendimento;
	private Integer seqAtendimento;
	private Short codLogradouro;
	private Short codBairro;
	
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

	public Integer getSeqAtendimento() {
		return seqAtendimento;
	}

	public void setSeqAtendimento(Integer seqAtendimento) {
		this.seqAtendimento = seqAtendimento;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Short getCodBairro() {
		return codBairro;
	}

	public void setCodBairro(Short codBairro) {
		this.codBairro = codBairro;
	}
	

}
