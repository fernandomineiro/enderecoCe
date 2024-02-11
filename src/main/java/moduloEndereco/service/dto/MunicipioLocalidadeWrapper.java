package moduloEndereco.service.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MunicipioLocalidadeWrapper {

	@EmbeddedId
	private MunicipioLocalidadeWrapperId id;
	@Column(name = "municipio")
	private String municipio;
	@Column(name = "localidade")
	private String localidade;
	@Column(name = "regional")
	private String regional;
	private String temAgua;
	private String temEsgoto;
	private String temDispEsgoto;
	private String enderecoAtendimento;
	private String horarioAtendimento;
	private String email;
	
	public MunicipioLocalidadeWrapper() {
		super();
	}

	public MunicipioLocalidadeWrapper(MunicipioLocalidadeWrapperId id, String municipio, String localidade,
			String regional) {
		super();
		this.id = id;
		this.municipio = municipio;
		this.localidade = localidade;
		this.regional = regional;
	}

	public MunicipioLocalidadeWrapperId getId() {
		return id;
	}

	public void setId(MunicipioLocalidadeWrapperId id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getTemAgua() {
		return temAgua;
	}

	public void setTemAgua(String temAgua) {
		this.temAgua = temAgua;
	}

	public String getTemEsgoto() {
		return temEsgoto;
	}

	public void setTemEsgoto(String temEsgoto) {
		this.temEsgoto = temEsgoto;
	}

	public String getTemDispEsgoto() {
		return temDispEsgoto;
	}

	public void setTemDispEsgoto(String temDispEsgoto) {
		this.temDispEsgoto = temDispEsgoto;
	}

	public String getEnderecoAtendimento() {
		return enderecoAtendimento;
	}

	public void setEnderecoAtendimento(String enderecoAtendimento) {
		this.enderecoAtendimento = enderecoAtendimento;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
