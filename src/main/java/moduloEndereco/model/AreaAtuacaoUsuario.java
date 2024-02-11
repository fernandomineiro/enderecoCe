package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CAD_AREA_ATUACAO_USUARIO")
public class AreaAtuacaoUsuario extends EntityBase {

	@JoinColumn(name="ID_AREA_ATUACAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private AreaAtuacao areaAtuacao;
	
	@Column(name="ID_USUARIO")
	private Long idUsuario;
	
	@Column(name="LOGIN")
	private String login;

	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}



	
	
}
