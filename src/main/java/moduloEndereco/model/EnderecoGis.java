package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name = "CDTEN")
public class EnderecoGis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonCesanNotSerializable
	@Column(name="ROWID")
	private Integer id;
	@Column(name="MATRICULA_IMOVEL")
	private Integer matriculaImovel;
	@Column(name="CD_BAIRRO_ANTIGO")
	private Short cdBairroAntigo;
	@Column(name="CD_LOGRADOURO_ANTIGO")
	private Short cdLogradouroAntigo;
	@Column(name="CD_BAIRRO_NOVO")
	private Short cdBairroNovo;
	@Column(name="CD_LOGRADOURO_NOVO")
	private Short cdLogradouroNovo;
	@Column(name="DT_INCLUSAO")
	private Integer dtInclusao;
	@Column(name="HR_INCLUSAO")
	private Short horaInclusao;
	@Column(name="MI_INCLUSAO")
	private Short minutoInclusao;
	@Column(name="DT_EXECUCAO")
	private Integer dataExecucao;
	@Column(name="HR_EXECUCAO")
	private Short horaExecucao;
	@Column(name="MI_EXECUCAO")
	private Short minutoExecucao;
	@Column(name="ID_USUARIO_AD")
	private String usuario;
	@Column(name="SEQ_INCLUSAO")
	private Short sequenciaInclusao;
	@Column(name="SITUACAO")
	private Short situacao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public Short getCdBairroAntigo() {
		return cdBairroAntigo;
	}
	public void setCdBairroAntigo(Short cdBairroAntigo) {
		this.cdBairroAntigo = cdBairroAntigo;
	}
	public Short getCdLogradouroAntigo() {
		return cdLogradouroAntigo;
	}
	public void setCdLogradouroAntigo(Short cdLogradouroAntigo) {
		this.cdLogradouroAntigo = cdLogradouroAntigo;
	}
	public Short getCdBairroNovo() {
		return cdBairroNovo;
	}
	public void setCdBairroNovo(Short cdBairroNovo) {
		this.cdBairroNovo = cdBairroNovo;
	}
	public Short getCdLogradouroNovo() {
		return cdLogradouroNovo;
	}
	public void setCdLogradouroNovo(Short cdLogradouroNovo) {
		this.cdLogradouroNovo = cdLogradouroNovo;
	}
	
	public Integer getDtInclusao() {
		return dtInclusao;
	}
	public void setDtInclusao(Integer dtInclusao) {
		this.dtInclusao = dtInclusao;
	}
	public Short getHoraInclusao() {
		return horaInclusao;
	}
	public void setHoraInclusao(Short horaInclusao) {
		this.horaInclusao = horaInclusao;
	}
	public Short getMinutoInclusao() {
		return minutoInclusao;
	}
	public void setMinutoInclusao(Short minutoInclusao) {
		this.minutoInclusao = minutoInclusao;
	}
	public Integer getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(Integer dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public Short getHoraExecucao() {
		return horaExecucao;
	}
	public void setHoraExecucao(Short horaExecucao) {
		this.horaExecucao = horaExecucao;
	}
	public Short getMinutoExecucao() {
		return minutoExecucao;
	}
	public void setMinutoExecucao(Short minutoExecucao) {
		this.minutoExecucao = minutoExecucao;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Short getSequenciaInclusao() {
		return sequenciaInclusao;
	}
	public void setSequenciaInclusao(Short sequenciaInclusao) {
		this.sequenciaInclusao = sequenciaInclusao;
	}
	public Short getSituacao() {
		return situacao;
	}
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}
	
	
}
