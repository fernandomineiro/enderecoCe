package moduloEndereco.service.dto;

import moduloEndereco.util.ConvertObjectToJsonCesan;

public class ImovelPesquisaEnderecoDTO {

	private Integer matriculaImovel;
	private Integer codClienteProp;
	private Integer numeroEndereco;
	private Short codBairro;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer codCliente;
	private Short codLogradouro;
	private Short codCidade;
	
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getCodBairro() {
		return codBairro;
	}

	public void setCodBairro(Short codBairro) {
		this.codBairro = codBairro;
	}

	public Short getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(Short codCidade) {
		this.codCidade = codCidade;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getCodClienteProp() {
		return codClienteProp;
	}

	public void setCodClienteProp(Integer codClienteProp) {
		this.codClienteProp = codClienteProp;
	}

	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);
	}

}
