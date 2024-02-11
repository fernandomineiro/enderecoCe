package moduloEndereco.service.dto;

public class EnderecoCepDTO {

	private String uf;

	private String logradouro;

	private String complementoLogradouro;
	private String cep;

	private String localidade;

	private String bairro;

	private String nomeReferencia;

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNomeReferencia() {
		return nomeReferencia;
	}

	public void setNomeReferencia(String nomeReferencia) {
		this.nomeReferencia = nomeReferencia;
	}

}
