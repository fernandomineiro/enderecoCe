package moduloEndereco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

import moduloEndereco.util.ConvertObjectToJsonCesan;
import moduloEndereco.util.DateFormatDefault;
import moduloEndereco.util.customAnnotation.DateTimeFormatCesan;
import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;


@Entity
@Table(name = "CDACI")
public class MunicipioLocalidade {
	
	@Id
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	
	@Column(name = "CD_MUNICIPIO", nullable = false)
	private Short cdMunicipio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_REGIONAL")
	private Regional regional;
	
	@Column(name = "CD_TARIFA", nullable = false)
	private Short cdTarifa;
	
	@Column(name = "DC_CIDADE", length = 25, nullable = false)
	private String dcCidade;
	
	@Column(name = "DENSIDADE", length = 6, precision = 4, scale = 1, nullable = false)
	private Float densidade;
	
	@Column(name = "ENDERECO_ATEND", length = 50, nullable = false)
	private String enderecoAtendimento;
	
	@Column(name = "ICMS", length = 5, precision = 3, scale = 1, nullable = false)
	private Float icms;
	
	@Column(name = "ISSQN", length = 5, precision = 3, scale = 1, nullable = false)
	private Float issqn;
	
	@Column(name = "MAINT", length = 1, nullable = false)
	private String maint;
	
	@Column(name = "METROPOLITANA", length = 1, nullable = false)
	private String metropolitana;
	
	@Column(name = "MUNICIPIO", length = 1, nullable = false)
	private String municipio;
	
	@Column(name = "POPULACAO", nullable = false)
	private Integer populacao;
	
	@Column(name = "TEM_AGUA", length = 1, nullable = false)
	private String temAgua;
	
	@Column(name = "TEM_ESGOTO", length = 1, nullable = false)
	private String temEsgoto;
	
	@Column(name = "TP_LEITURA", nullable = false)
	@Range(min = 0, max = 255)
	private Integer tpLeitura;
	
	@Column(name = "TP_CDBARRAS", nullable = false)
	@Range(min = 0, max = 255)
	private Integer tpCodigoBarras;
	
	@Column(name = "horario_atend", nullable = false)
	private Short horarioAtendimento;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	@Column(name = "TEM_DISP_ESGOTO", length = 1, nullable = false)
	private String temDispEsgoto;
	
	@Column(name= "DATA_HORA_EXCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormatCesan(formatString = DateFormatDefault.FORMATTER_DATE_TIME)
	@JsonCesanNotSerializable
	private Date dataHoraExclusao;

	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);
	}

	@Override
	public String toString() {
		return this.cdCidade.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MunicipioLocalidade other = (MunicipioLocalidade) obj;
		if (cdCidade == null) {
			if (other.cdCidade != null)
				return false;
		} else if (!cdCidade.equals(other.cdCidade))
			return false;
		return true;
	}

	public Short getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Short getCdMunicipio() {
		return cdMunicipio;
	}

	public void setCdMunicipio(Short cdMunicipio) {
		this.cdMunicipio = cdMunicipio;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Short getCdTarifa() {
		return cdTarifa;
	}

	public void setCdTarifa(Short cdTarifa) {
		this.cdTarifa = cdTarifa;
	}

	public String getDcCidade() {
		return dcCidade;
	}

	public void setDcCidade(String dcCidade) {
		this.dcCidade = dcCidade;
	}

	public Float getDensidade() {
		return densidade;
	}

	public void setDensidade(Float densidade) {
		this.densidade = densidade;
	}

	public String getEnderecoAtendimento() {
		return enderecoAtendimento;
	}

	public void setEnderecoAtendimento(String enderecoAtendimento) {
		this.enderecoAtendimento = enderecoAtendimento;
	}

	public Float getIcms() {
		return icms;
	}

	public void setIcms(Float icms) {
		this.icms = icms;
	}

	public Float getIssqn() {
		return issqn;
	}

	public void setIssqn(Float issqn) {
		this.issqn = issqn;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public String getMetropolitana() {
		return metropolitana;
	}

	public void setMetropolitana(String metropolitana) {
		this.metropolitana = metropolitana;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public String getTemAgua() {
		return temAgua;
	}

	public void setTemAgua(String temAgua) {
		this.temAgua = temAgua;
	}

	public String getTemEsgoto() {
		return temEsgoto;
	}

	public void setTemEsgoto(String temEsgoto) {
		this.temEsgoto = temEsgoto;
	}

	public Integer getTpLeitura() {
		return tpLeitura;
	}

	public void setTpLeitura(Integer tpLeitura) {
		this.tpLeitura = tpLeitura;
	}

	public Integer getTpCodigoBarras() {
		return tpCodigoBarras;
	}

	public void setTpCodigoBarras(Integer tpCodigoBarras) {
		this.tpCodigoBarras = tpCodigoBarras;
	}

	public Short getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(Short horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTemDispEsgoto() {
		return temDispEsgoto;
	}

	public void setTemDispEsgoto(String temDispEsgoto) {
		this.temDispEsgoto = temDispEsgoto;
	}

	public Date getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(Date dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}

}
