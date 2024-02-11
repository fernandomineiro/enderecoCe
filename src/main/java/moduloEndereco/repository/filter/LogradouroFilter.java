package moduloEndereco.repository.filter;

import java.util.List;

public class LogradouroFilter {

	private Short codLogradouro;
	private Short codLocalidade;
	private String nomeLogradouro;
	private String siglaLogradouro;
	private List<Short> idsBairro;

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Short getCodLocalidade() {
		return codLocalidade;
	}

	public void setCodLocalidade(Short codLocalidade) {
		this.codLocalidade = codLocalidade;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getSiglaLogradouro() {
		return siglaLogradouro;
	}

	public void setSiglaLogradouro(String siglaLogradouro) {
		this.siglaLogradouro = siglaLogradouro;
	}

	public List<Short> getIdsBairro() {
		return idsBairro;
	}

	public void setIdsBairro(List<Short> idsBairro) {
		this.idsBairro = idsBairro;
	}

}
