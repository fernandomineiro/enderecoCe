package moduloEndereco.service.dto;

import java.util.Date;

public class CorreioStatusDTO {

	private String status;
	private Date dataHoraInicioProcesso;
	private Date dataHoraFimProcesso;
	private String ultimoArquivoExecutado;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataHoraInicioProcesso() {
		return dataHoraInicioProcesso;
	}
	public void setDataHoraInicioProcesso(Date dataHoraInicioProcesso) {
		this.dataHoraInicioProcesso = dataHoraInicioProcesso;
	}
	public Date getDataHoraFimProcesso() {
		return dataHoraFimProcesso;
	}
	public void setDataHoraFimProcesso(Date dataHoraFimProcesso) {
		this.dataHoraFimProcesso = dataHoraFimProcesso;
	}
	public String getUltimoArquivoExecutado() {
		return ultimoArquivoExecutado;
	}
	public void setUltimoArquivoExecutado(String ultimoArquivoExecutado) {
		this.ultimoArquivoExecutado = ultimoArquivoExecutado;
	}

	
}
