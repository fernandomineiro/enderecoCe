package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_BAIRRO")
public class LogBairro {

	@Id
	@Column(name = "BAI_NU")
	private Integer numeroBairro;

	@Column(name = "BAI_NO")
	private String bairro;

	@Column(name = "BAI_NO_ABREV")
	private String abreviacaoBairro;

	@Column(name = "UFE_SG")
	private String uf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOC_NU")
	private LogLocalidade numeroLocalidade;

	public Integer getNumeroBairro() {
		return numeroBairro;
	}

	public void setNumeroBairro(Integer numeroBairro) {
		this.numeroBairro = numeroBairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getAbreviacaoBairro() {
		return abreviacaoBairro;
	}

	public void setAbreviacaoBairro(String abreviacaoBairro) {
		this.abreviacaoBairro = abreviacaoBairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public LogLocalidade getNumeroLocalidade() {
		return numeroLocalidade;
	}

	public void setNumeroLocalidade(LogLocalidade numeroLocalidade) {
		this.numeroLocalidade = numeroLocalidade;
	}

	

}
