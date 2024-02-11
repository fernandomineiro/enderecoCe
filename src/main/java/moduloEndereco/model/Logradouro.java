package moduloEndereco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import moduloEndereco.util.DateFormatDefault;
import moduloEndereco.util.customAnnotation.DateTimeFormatCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "CDALO")
public class Logradouro {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogradouroIdLocalidade idLogradouroIdLocalidade;

	@Column(name = "ID")
	@JsonCesanNotSerializable
	private Integer id;

	@Column(name = "CD_ATENDIMENTO")
	private Integer codAtendimento;

	/*
	 * Tamanho do campo de descrição do logradouro deve ser aumentado (ideal: 40,
	 * além da sigla - IMPACTO: rotina atual de faturamento com a Allsan- aguardar
	 * faturamento)
	 */

	@Column(name = "DC_LOGRADOURO")
	private String nomeLogradouro;

	@Column(name = "LOC_REDE_AGUA")
	private Short localRedeAgua;

	@Column(name = "LOC_REDE_ESGOTO")
	private Short localRedeEsgoto;

	@Column(name = "DIA_REDE_AGUA")
	private Short diametroRedeAgua;

	@Column(name = "DIA_REDE_ESGOTO")
	private Short diametroRedeEsgoto;

	@Column(name = "MAINT")
	private char maint;

	@Column(name = "MAT_REDE_AGUA")
	private Short materialRedeAgua;

	@Column(name = "MAT_REDE_ESGOTO")
	private Short materialRedeEsgoto;

	@Column(name = "REF_ATENDIMENTO")
	private Integer refAtendimenoSS;

	@Column(name = "SEQ_SS")
	private Short sequencialSS;

	@Column(name = "TP_PAVIMENTO")
	private Short tipoPavimento;

	@Column(name = "SIGLA_LOGRADOURO")
	private String siglaLogradouro;

	@Column(name = "DATA_HORA_EXCLUSAO", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormatCesan(formatString = DateFormatDefault.FORMATTER_DATE_TIME)
	@JsonCesanNotSerializable
	private Date dataHoraExclusao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_LOGRADOURO")
	private TipoLogradouro tipoLogradouro;

	public IdLogradouroIdLocalidade getIdLogradouroIdLocalidade() {
		return idLogradouroIdLocalidade;
	}

	public void setIdLogradouroIdLocalidade(IdLogradouroIdLocalidade idLogradouroIdLocalidade) {
		this.idLogradouroIdLocalidade = idLogradouroIdLocalidade;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodAtendimento() {
		return codAtendimento;
	}

	public void setCodAtendimento(Integer codAtendimento) {
		this.codAtendimento = codAtendimento;
	}

	public String getDescricao() {
		return nomeLogradouro;
	}

	public void setDescricao(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public Short getLocalRedeAgua() {
		return localRedeAgua;
	}

	public void setLocalRedeAgua(Short localRedeAgua) {
		this.localRedeAgua = localRedeAgua;
	}

	public Short getLocalRedeEsgoto() {
		return localRedeEsgoto;
	}

	public void setLocalRedeEsgoto(Short localRedeEsgoto) {
		this.localRedeEsgoto = localRedeEsgoto;
	}

	public Short getDiametroRedeAgua() {
		return diametroRedeAgua;
	}

	public void setDiametroRedeAgua(Short diametroRedeAgua) {
		this.diametroRedeAgua = diametroRedeAgua;
	}

	public Short getDiametroRedeEsgoto() {
		return diametroRedeEsgoto;
	}

	public void setDiametroRedeEsgoto(Short diametroRedeEsgoto) {
		this.diametroRedeEsgoto = diametroRedeEsgoto;
	}

	public char getMaint() {
		return maint;
	}

	public void setMaint(char maint) {
		this.maint = maint;
	}

	public Short getMaterialRedeAgua() {
		return materialRedeAgua;
	}

	public void setMaterialRedeAgua(Short materialRedeAgua) {
		this.materialRedeAgua = materialRedeAgua;
	}

	public Short getMaterialRedeEsgoto() {
		return materialRedeEsgoto;
	}

	public void setMaterialRedeEsgoto(Short materialRedeEsgoto) {
		this.materialRedeEsgoto = materialRedeEsgoto;
	}

	public int getRefAtendimenoSS() {
		return refAtendimenoSS;
	}

	public void setRefAtendimenoSS(Integer refAtendimenoSS) {
		this.refAtendimenoSS = refAtendimenoSS;
	}

	public Short getSequencialSS() {
		return sequencialSS;
	}

	public void setSequencialSS(Short sequencialSS) {
		this.sequencialSS = sequencialSS;
	}

	public Short getTipoPavimento() {
		return tipoPavimento;
	}

	public void setTipoPavimento(Short tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}

	public String getSiglaLogradouro() {
		return siglaLogradouro;
	}

	public void setSiglaLogradouro(String siglaLogradouro) {
		this.siglaLogradouro = siglaLogradouro;
	}

	public Date getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(Date dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}

}
