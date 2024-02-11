package moduloEndereco.repository.filter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ServicoAtendimentoGisFilter {

	private Integer matriculaImovel;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;

	private List<String> localidade;

	// CD_ATENDIMENTO =4412 and REF_ATENDIMENTO=201301 and SEQUENCIAL=1

	private Integer cdAtendimento;
	private Integer refAtendimento;
	private Integer sequencial;

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

	public List<String> getLocalidade() {
		return localidade;
	}

	public void setLocalidade(List<String> localidade) {
		this.localidade = localidade;
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

}
