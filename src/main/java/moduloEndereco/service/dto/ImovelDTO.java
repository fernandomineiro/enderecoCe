package moduloEndereco.service.dto;

import java.time.LocalDate;
import java.util.List;

import moduloEndereco.util.ConvertObjectToJsonCesan;

public class ImovelDTO {

	private Integer matriculaImovel;
	private Short dv;
	private char agrupaFatura;
	private Short areaInfluencia;
	private Short categoria;
	private Integer codAtendimento;
	private Integer codAtividade;
	private Integer codAtividade2;
	private Short codBairro;
	private Short codCidade;
	private Integer codCliente;
	private Short dvR;
	private Integer codClienteProp;
	private Short dvP;
	private Integer numeroEndereco;
	private String complementoEndereco;
	private Short codLogradouro;
	private Integer grupoConsumo;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private String nome;
	private Short ciclo;
	private String dcGrupoConsumo;
	private Integer economias;
	private boolean flagMinhaCasaMinhaVida;
	private Integer cep;
	private String pontoReferencia;	
	private String registroImobiliario;
	private String justificativa;
	private Short codMacro;
	private List<Integer> listCodAtividade;
	private Integer idOrigemLigacaoagua;
	private LocalDate dataLimiteLigacao;
	private LocalDate dataLigacaoAgua;
	private Integer idMaterialLigacaoAgua;
	private Integer idDiametroLigacaoAgua;
	private Integer idTipoLigacaoAgua;
	private boolean flagPiscina;
	private Integer idFonteLigacaoAgua;
	private String processoAgua;
	private String processoEsgoto;
	private LocalDate dataLigacaoEsgoto;
	private LocalDate dataUltimaVistoria;
	private boolean flagTratamentoEsgoto;
	private boolean flagPi;
	private boolean flagNivelRua;
	private boolean flagElatorio;
	private boolean flagNaoGeraEsgoto;
	private boolean flagUnidadeCesan;
	private List<ClassificacaoImobiliariaDTO> classificacaoImobiliariaDTO;
	
	
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

	public char getAgrupaFatura() {
		return agrupaFatura;
	}

	public void setAgrupaFatura(char agrupaFatura) {
		this.agrupaFatura = agrupaFatura;
	}

	public Short getAreaInfluencia() {
		return areaInfluencia;
	}

	public void setAreaInfluencia(Short areaInfluencia) {
		this.areaInfluencia = areaInfluencia;
	}

	public Short getCategoria() {
		return categoria;
	}

	public void setCategoria(Short categoria) {
		this.categoria = categoria;
	}

	public Integer getCodAtendimento() {
		return codAtendimento;
	}

	public void setCodAtendimento(Integer codAtendimento) {
		this.codAtendimento = codAtendimento;
	}

	public Integer getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(Integer codAtividade) {
		this.codAtividade = codAtividade;
	}

	public Integer getCodAtividade2() {
		return codAtividade2;
	}

	public void setCodAtividade2(Integer codAtividade2) {
		this.codAtividade2 = codAtividade2;
	}

	public Short getCodBairro() {
		return codBairro;
	}

	public void setCodBairro(Short codBairro) {
		this.codBairro = codBairro;
	}

	public Short getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(Short codCidade) {
		this.codCidade = codCidade;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getCodClienteProp() {
		return codClienteProp;
	}

	public void setCodClienteProp(Integer codClienteProp) {
		this.codClienteProp = codClienteProp;
	}

	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Integer getGrupoConsumo() {
		return grupoConsumo;
	}

	public void setGrupoConsumo(Integer grupoConsumo) {
		this.grupoConsumo = grupoConsumo;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getRegistroImobiliario() {
		return registroImobiliario;
	}

	public void setRegistroImobiliario(String registroImobiliario) {
		this.registroImobiliario = registroImobiliario;
	}

	public Short getCodMacro() {
		return codMacro;
	}

	public void setCodMacro(Short codMacro) {
		this.codMacro = codMacro;
	}

	public List<Integer> getListCodAtividade() {
		return listCodAtividade;
	}

	public void setListCodAtividade(List<Integer> listCodAtividade) {
		this.listCodAtividade = listCodAtividade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Short getCiclo() {
		return ciclo;
	}

	public void setCiclo(Short ciclo) {
		this.ciclo = ciclo;
	}

	public String getDcGrupoConsumo() {
		return dcGrupoConsumo;
	}

	public void setDcGrupoConsumo(String dcGrupoConsumo) {
		this.dcGrupoConsumo = dcGrupoConsumo;
	}

	public Integer getEconomias() {
		return economias;
	}

	public void setEconomias(Integer economias) {
		this.economias = economias;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public boolean isFlagMinhaCasaMinhaVida() {
		return flagMinhaCasaMinhaVida;
	}

	public void setFlagMinhaCasaMinhaVida(boolean flagMinhaCasaMinhaVida) {
		this.flagMinhaCasaMinhaVida = flagMinhaCasaMinhaVida;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public Integer getIdOrigemLigacaoagua() {
		return idOrigemLigacaoagua;
	}

	public void setIdOrigemLigacaoagua(Integer idOrigemLigacaoagua) {
		this.idOrigemLigacaoagua = idOrigemLigacaoagua;
	}

	public LocalDate getDataLimiteLigacao() {
		return dataLimiteLigacao;
	}

	public void setDataLimiteLigacao(LocalDate dataLimiteLigacao) {
		this.dataLimiteLigacao = dataLimiteLigacao;
	}

	public LocalDate getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}

	public void setDataLigacaoAgua(LocalDate dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	public Integer getIdMaterialLigacaoAgua() {
		return idMaterialLigacaoAgua;
	}

	public void setIdMaterialLigacaoAgua(Integer idMaterialLigacaoAgua) {
		this.idMaterialLigacaoAgua = idMaterialLigacaoAgua;
	}

	public Integer getIdDiametroLigacaoAgua() {
		return idDiametroLigacaoAgua;
	}

	public void setIdDiametroLigacaoAgua(Integer idDiametroLigacaoAgua) {
		this.idDiametroLigacaoAgua = idDiametroLigacaoAgua;
	}

	public Integer getIdTipoLigacaoAgua() {
		return idTipoLigacaoAgua;
	}

	public void setIdTipoLigacaoAgua(Integer idTipoLigacaoAgua) {
		this.idTipoLigacaoAgua = idTipoLigacaoAgua;
	}

	public boolean isFlagPiscina() {
		return flagPiscina;
	}

	public void setFlagPiscina(boolean flagPiscina) {
		this.flagPiscina = flagPiscina;
	}

	public Integer getIdFonteLigacaoAgua() {
		return idFonteLigacaoAgua;
	}

	public void setIdFonteLigacaoAgua(Integer idFonteLigacaoAgua) {
		this.idFonteLigacaoAgua = idFonteLigacaoAgua;
	}

	public String getProcessoAgua() {
		return processoAgua;
	}

	public void setProcessoAgua(String processoAgua) {
		this.processoAgua = processoAgua;
	}

	public String getProcessoEsgoto() {
		return processoEsgoto;
	}

	public void setProcessoEsgoto(String processoEsgoto) {
		this.processoEsgoto = processoEsgoto;
	}

	public LocalDate getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(LocalDate dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public LocalDate getDataUltimaVistoria() {
		return dataUltimaVistoria;
	}

	public void setDataUltimaVistoria(LocalDate dataUltimaVistoria) {
		this.dataUltimaVistoria = dataUltimaVistoria;
	}
	public boolean isFlagTratamentoEsgoto() {
		return flagTratamentoEsgoto;
	}

	public void setFlagTratamentoEsgoto(boolean flagTratamentoEsgoto) {
		this.flagTratamentoEsgoto = flagTratamentoEsgoto;
	}

	public boolean isFlagPi() {
		return flagPi;
	}

	public void setFlagPi(boolean flagPi) {
		this.flagPi = flagPi;
	}

	public boolean isFlagNivelRua() {
		return flagNivelRua;
	}

	public void setFlagNivelRua(boolean flagNivelRua) {
		this.flagNivelRua = flagNivelRua;
	}

	public boolean isFlagElatorio() {
		return flagElatorio;
	}

	public void setFlagElatorio(boolean flagElatorio) {
		this.flagElatorio = flagElatorio;
	}

	public boolean isFlagNaoGeraEsgoto() {
		return flagNaoGeraEsgoto;
	}

	public void setFlagNaoGeraEsgoto(boolean flagNaoGeraEsgoto) {
		this.flagNaoGeraEsgoto = flagNaoGeraEsgoto;
	}

	public boolean isFlagUnidadeCesan() {
		return flagUnidadeCesan;
	}

	public void setFlagUnidadeCesan(boolean flagUnidadeCesan) {
		this.flagUnidadeCesan = flagUnidadeCesan;
	}
	
	public List<ClassificacaoImobiliariaDTO> getClassificacaoImobiliariaDTO() {
		return classificacaoImobiliariaDTO;
	}

	public void setClassificacaoImobiliariaDTO(List<ClassificacaoImobiliariaDTO> classificacaoImobiliariaDTO) {
		this.classificacaoImobiliariaDTO = classificacaoImobiliariaDTO;
	}
	public Short getDvR() {
		return dvR;
	}

	public void setDvR(Short dvR) {
		this.dvR = dvR;
	}

	public Short getDvP() {
		return dvP;
	}

	public void setDvP(Short dvP) {
		this.dvP = dvP;
	}

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}

}
