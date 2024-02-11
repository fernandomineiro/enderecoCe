package moduloEndereco.service.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import moduloEndereco.util.ConvertObjectToJsonCesan;

public class ClassificacaoImobiliariaDTO {

	private Integer idClassificacaoImobiliaria;
	private Integer idGrupoConsumo;
	private Integer nrMatriculaImovel;
	private Short csCategoria;
	private Short economias;
	private Short predominante;
	@JsonIgnore
	private Date dataHoraFim;
	private String dcGrupoConsumo;
	private String dcCategoriaGrupoConsumo;


	public Integer getIdClassificacaoImobiliaria() {
		return idClassificacaoImobiliaria;
	}

	public void setIdClassificacaoImobiliaria(Integer idClassificacaoImobiliaria) {
		this.idClassificacaoImobiliaria = idClassificacaoImobiliaria;
	}

	public Integer getIdGrupoConsumo() {
		return idGrupoConsumo;
	}

	public void setIdGrupoConsumo(Integer idGrupoConsumo) {
		this.idGrupoConsumo = idGrupoConsumo;
	}

	public Integer getNrMatriculaImovel() {
		return nrMatriculaImovel;
	}

	public void setNrMatriculaImovel(Integer nrMatriculaImovel) {
		this.nrMatriculaImovel = nrMatriculaImovel;
	}

	public Short getCsCategoria() {
		return csCategoria;
	}

	public void setCsCategoria(Short csCategoria) {
		this.csCategoria = csCategoria;
	}

	public Short getEconomias() {
		return economias;
	}

	public void setEconomias(Short economias) {
		this.economias = economias;
	}

	public Short getPredominante() {
		return predominante;
	}

	public void setPredominante(Short predominante) {
		this.predominante = predominante;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public String getDcGrupoConsumo() {
		return dcGrupoConsumo;
	}

	public void setDcGrupoConsumo(String dcGrupoConsumo) {
		this.dcGrupoConsumo = dcGrupoConsumo;
	}

	public String getDcCategoriaGrupoConsumo() {
		return dcCategoriaGrupoConsumo;
	}

	public void setDcCategoriaGrupoConsumo(String dcCategoriaGrupoConsumo) {
		this.dcCategoriaGrupoConsumo = dcCategoriaGrupoConsumo;

	}

	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);
	}
}
