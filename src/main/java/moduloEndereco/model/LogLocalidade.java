package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_LOCALIDADE")
public class LogLocalidade {

	@Id
	@Column(name = "LOC_NU")
	private Integer numeroLocalidade;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "LOC_NO")
	private String localidade;

	@Column(name = "CEP")
	private String cep;

	@Column(name = "LOC_IN_SIT")
	private String locInSit;

	@Column(name = "LOC_IN_TIPO_LOC")
	private String locInTipoLoc;

	@Column(name = "LOC_NU_SUB")
	private Integer locNuSub;

	@Column(name = "LOC_NO_ABREV")
	private String localidadeAbreviada;

	@Column(name = "MUN_NU")
	private String numeroMunicipio;

	public Integer getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(Integer numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLocInSit() {
		return locInSit;
	}

	public void setLocInSit(String locInSit) {
		this.locInSit = locInSit;
	}

	public String getLocInTipoLoc() {
		return locInTipoLoc;
	}

	public void setLocInTipoLoc(String locInTipoLoc) {
		this.locInTipoLoc = locInTipoLoc;
	}

	public Integer getLocNuSub() {
		return locNuSub;
	}

	public void setLocNuSub(Integer locNuSub) {
		this.locNuSub = locNuSub;
	}

	public String getLocalidadeAbreviada() {
		return localidadeAbreviada;
	}

	public void setLocalidadeAbreviada(String localidadeAbreviada) {
		this.localidadeAbreviada = localidadeAbreviada;
	}

	public String getNumeroMunicipio() {
		return numeroMunicipio;
	}

	public void setNumeroMunicipio(String numeroMunicipio) {
		this.numeroMunicipio = numeroMunicipio;
	}

}
