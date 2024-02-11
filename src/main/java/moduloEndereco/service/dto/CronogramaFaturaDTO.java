package moduloEndereco.service.dto;

public class CronogramaFaturaDTO {

	private Short codLocalidade;
	private Short ciclo;
	private Short fase;
	
	public Short getCodLocalidade() {
		return codLocalidade;
	}
	public void setCodLocalidade(Short codLocalidade) {
		this.codLocalidade = codLocalidade;
	}
	public Short getCiclo() {
		return ciclo;
	}
	public void setCiclo(Short ciclo) {
		this.ciclo = ciclo;
	}
	public Short getFase() {
		return fase;
	}
	public void setFase(Short fase) {
		this.fase = fase;
	}
	
	
}
