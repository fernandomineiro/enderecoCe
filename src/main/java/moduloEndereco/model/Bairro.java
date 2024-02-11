package moduloEndereco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.DateFormatDefault;
import moduloEndereco.util.customAnnotation.DateTimeFormatCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;



@Entity
@Table(name="CDABA")
public class Bairro  {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdBairroIdLocalidade idBairroIdLocalidade;
	
	@Column(name="DC_BAIRRO")
	private String nomeBairro;
	
	@Column(name="MAINT")
	private char maint;
	
	@Column(name="CD_MUN_IMPRESSAO")
	private Short cdMunicipioImpressao;
	
	@Column(name="ID")
	@JsonCesanNotSerializable
	private Integer id;
	
	@Column(name = "DATA_HORA_EXCLUSAO", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormatCesan(formatString = DateFormatDefault.FORMATTER_DATE_TIME)
	@JsonCesanNotSerializable
	private Date dataHoraExclusao;


	
	public IdBairroIdLocalidade getIdBairroIdLocalidade() {
		return idBairroIdLocalidade;
	}

	public void setIdBairroIdLocalidade(IdBairroIdLocalidade idBairroIdLocalidade) {
		this.idBairroIdLocalidade = idBairroIdLocalidade;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	
	public char getMaint() {
		return maint;
	}

	public void setMaint(char maint) {
		this.maint = maint;
	}

	public Short getCdMunicipioImpressao() {
		return cdMunicipioImpressao;
	}

	public void setCdMunicipioImpressao(Short cdMunicipioImpressao) {
		this.cdMunicipioImpressao = cdMunicipioImpressao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(Date dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}
	

	public String toJson() {

		return ConvertObjectToJsonCesan.execute(this);
	}
	
	
}
