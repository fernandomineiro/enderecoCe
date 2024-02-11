package moduloEndereco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;


@Entity
@Table(name="CAD_SERVICO_ATENDIMENTO")
public class ServicoAtendimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonCesanNotSerializable
	private Long id;
	@Column(name="CD_ATENDIMENTO")
	private Integer cdAtendimento;
	@Column(name="REF_ATENDIMENTO")
	private Integer refAtendimento;
	@Column(name="SEQUENCIAL")
	private Integer sequencial;
	@Column(name="MATRICULA_IMOVEL")
	private Integer matriculaImovel;
	@Column(name="DV")
	private Short dv;
	@Column(name="ORIGEM_MATRICULA")
	private String origemMatricula;
	@Column(name="CD_CIDADE")
	private Short cdLocalidade;
	@Column(name="CD_BAIRRO_ANTIGO")
    private Short cdBairroAntigo;
	@Column(name="DES_BAIRRO_ANTIGO")
	private String descricaoBairroAntigo;
	@Column(name="CD_BAIRRO_NOVO")
    private Short cdBairroNovo;
	@Column(name="DES_BAIRRO_NOVO")
	private String descricaoBairroNovo;
	@Column(name="CD_LOGRADOURO_ANTIGO")
    private Short cdLogradouroAntigo;
	@Column(name="DES_LOGRADOURO_ANTIGO")
	private String descricaoLogradouroAntigo;
	@Column(name="CD_LOGRADOURO_NOVO")
    private Short cdLogradouroNovo;
	@Column(name="DES_LOGRADOURO_NOVO")
	private String descricaoLogradouroNovo;
	@Column(name = "DATA_INCLUSAO")
	@JsonCesanNotSerializable
	private LocalDateTime dataInclusao;
	@Column(name = "DATA_EXECUCAO",updatable=false, insertable=false)
	@JsonCesanNotSerializable
	private LocalDateTime dataExcucao;
	@Column(name="SEQ_INCLUSAO")
	private Short sequenciaInclusao;
	@Column(name="SITUACAO")
    private Short situacao;
	@Column(name="ID_USUARIO_AD")
	@JsonCesanNotSerializable
	private String usuario;
	@Column(name="CD_ENDERECO_GIS")
	private Integer cdEnderecoGis; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getOrigemMatricula() {
		return origemMatricula;
	}
	public void setOrigemMatricula(String origemMatricula) {
		this.origemMatricula = origemMatricula;
	}
	public Short getCdLocalidade() {
		return cdLocalidade;
	}
	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}
	public Short getCdBairroAntigo() {
		return cdBairroAntigo;
	}
	public void setCdBairroAntigo(Short cdBairroAntigo) {
		this.cdBairroAntigo = cdBairroAntigo;
	}
	public String getDescricaoBairroAntigo() {
		return descricaoBairroAntigo;
	}
	public void setDescricaoBairroAntigo(String descricaoBairroAntigo) {
		this.descricaoBairroAntigo = descricaoBairroAntigo;
	}
	public Short getCdBairroNovo() {
		return cdBairroNovo;
	}
	public void setCdBairroNovo(Short cdBairroNovo) {
		this.cdBairroNovo = cdBairroNovo;
	}
	public String getDescricaoBairroNovo() {
		return descricaoBairroNovo;
	}
	public void setDescricaoBairroNovo(String descricaoBairroNovo) {
		this.descricaoBairroNovo = descricaoBairroNovo;
	}
	public Short getCdLogradouroAntigo() {
		return cdLogradouroAntigo;
	}
	public void setCdLogradouroAntigo(Short cdLogradouroAntigo) {
		this.cdLogradouroAntigo = cdLogradouroAntigo;
	}
	public String getDescricaoLogradouroAntigo() {
		return descricaoLogradouroAntigo;
	}
	public void setDescricaoLogradouroAntigo(String descricaoLogradouroAntigo) {
		this.descricaoLogradouroAntigo = descricaoLogradouroAntigo;
	}
	public Short getCdLogradouroNovo() {
		return cdLogradouroNovo;
	}
	public void setCdLogradouroNovo(Short cdLogradouroNovo) {
		this.cdLogradouroNovo = cdLogradouroNovo;
	}
	public String getDescricaoLogradouroNovo() {
		return descricaoLogradouroNovo;
	}
	public void setDescricaoLogradouroNovo(String descricaoLogradouroNovo) {
		this.descricaoLogradouroNovo = descricaoLogradouroNovo;
	}
	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public LocalDateTime getDataExcucao() {
		return dataExcucao;
	}
	public void setDataExcucao(LocalDateTime dataExcucao) {
		this.dataExcucao = dataExcucao;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    public Integer getCdEnderecoGis() {
		return cdEnderecoGis;
	}
	public void setCdEnderecoGis(Integer cdEnderecoGis) {
		this.cdEnderecoGis = cdEnderecoGis;
	}
	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);	
	}
}
