package moduloEndereco.service.dto;

import java.time.LocalDateTime;

public class ServicoAtendimentoProcessamentoDTO {

	
	private Integer cdAtendimento;
	private Integer refAtendimento;
	private Integer sequencial;
	private Integer matriculaImovel;
	private Short dv;
	private String status;
	private LocalDateTime dataExcucao;
	private String usuario;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCdAtendimento() {
		return cdAtendimento;
	}
	public void setCdAtendimento(Integer cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}
	public Integer getRefAtendimento() {
		return refAtendimento;
	}
	public void setRefAtendimento(Integer refAtendimento) {
		this.refAtendimento = refAtendimento;
	}
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public Short getDv() {
		return dv;
	}
	public void setDv(Short dv) {
		this.dv = dv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getDataExcucao() {
		return dataExcucao;
	}
	public void setDataExcucao(LocalDateTime dataExcucao) {
		this.dataExcucao = dataExcucao;
	}
	
	
	
}
