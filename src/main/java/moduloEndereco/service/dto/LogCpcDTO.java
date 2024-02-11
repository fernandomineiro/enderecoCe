package moduloEndereco.service.dto;

public class LogCpcDTO {

	private Integer numeroCpc;

	private String uf;

	private String localidade;

	private String nomeCpc;

	private String enderecoCpc;

	private String cep;

	public Integer getNumeroCpc() {
		return numeroCpc;
	}

	public void setNumeroCpc(Integer numeroCpc) {
		this.numeroCpc = numeroCpc;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNomeCpc() {
		return nomeCpc;
	}

	public void setNomeCpc(String nomeCpc) {
		this.nomeCpc = nomeCpc;
	}

	public String getEnderecoCpc() {
		return enderecoCpc;
	}

	public void setEnderecoCpc(String enderecoCpc) {
		this.enderecoCpc = enderecoCpc;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
