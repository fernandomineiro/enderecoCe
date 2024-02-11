package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;

@Entity
@Table(name="CDTBL")
public class BairroLogradouro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROWID")
	@JsonCesanNotSerializable
	private Integer id;
	@Column(name = "CD_BAIRRO")
	private Short codBairro;
	@Column(name = "CD_CIDADE")
	private Short codLocalidade;
	@Column(name = "CD_LOGRADOURO")
	private Short codLogradouro;
	@Column(name = "ESGOTO")
	@JsonCesanNotSerializable
	private char esgoto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Short getCodBairro() {
		return codBairro;
	}
	public void setCodBairro(Short codBairro) {
		this.codBairro = codBairro;
	}
	public Short getCodLocalidade() {
		return codLocalidade;
	}
	public void setCodLocalidade(Short codLocalidade) {
		this.codLocalidade = codLocalidade;
	}
	public Short getCodLogradouro() {
		return codLogradouro;
	}
	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}
	public char getEsgoto() {
		return esgoto;
	}
	public void setEsgoto(char esgoto) {
		this.esgoto = esgoto;
	}
	
	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}
	
		    
		   
}
