package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_CEP_AVULSO")
public class LogCepAvulso {

	@Id
	@Column(name = "ID_CEP_AVULSO")
	private Integer idCepAvulso;

	@Column(name = "UFE_SG")
	private String uf;

	@Column(name = "CEP")
	private String cep;

	public Integer getIdCepAvulso() {
		return idCepAvulso;
	}

	public void setIdCepAvulso(Integer idCepAvulso) {
		this.idCepAvulso = idCepAvulso;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
