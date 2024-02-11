package moduloEndereco.service.dto;

public class AreaAtuacaoUsuarioDTO {


	private Long IdAreaAtuacao;

	private Long idUsuario;

	private String login;
	
	private String status;

	public Long getIdAreaAtuacao() {
		return IdAreaAtuacao;
	}

	public void setIdAreaAtuacao(Long idAreaAtuacao) {
		IdAreaAtuacao = idAreaAtuacao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "AreaAtuacaoUsuarioDTO [IdAreaAtuacao=" + IdAreaAtuacao + ", idUsuario=" + idUsuario + ", status="
				+ status + "]";
	}
}
