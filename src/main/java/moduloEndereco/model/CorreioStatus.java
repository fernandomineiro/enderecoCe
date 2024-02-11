package moduloEndereco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_CORREIO_STATUS")
public class CorreioStatus extends EntityBase{

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ULTIMO_ARQUIVO_EXECUTADO")
	private String ultimoArquivoExecutado;
	
	@Column(name = "DATA_HORA_INICIO_PROCESSO")
	private Date dataHoraInicioProcesso;
	
	@Column(name = "DATA_HORA_FIM_PROCESSO")
	private Date dataHoraFimProcesso;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUltimoArquivoExecutado() {
		return ultimoArquivoExecutado;
	}

	public void setUltimoArquivoExecutado(String ultimoArquivoExecutado) {
		this.ultimoArquivoExecutado = ultimoArquivoExecutado;
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
	
	
}
