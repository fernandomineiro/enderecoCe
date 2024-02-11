package moduloEndereco.repository.filter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class EnderecoGisFilter {

	private Integer matriculaImovel;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;

	private Integer dataInicioI;

	private Integer dataFimI;

	private List<String> localidade;

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDataInicioI() {
		return dataInicioI;
	}

	public void setDataInicioI(Integer dataInicioI) {
		this.dataInicioI = dataInicioI;
	}

	public Integer getDataFimI() {
		return dataFimI;
	}

	public void setDataFimI(Integer dataFimI) {
		this.dataFimI = dataFimI;
	}

	public List<String> getLocalidade() {
		return localidade;
	}

	public void setLocalidade(List<String> localidade) {
		this.localidade = localidade;
	}

	

}
