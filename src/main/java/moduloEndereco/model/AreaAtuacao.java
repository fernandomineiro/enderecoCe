package moduloEndereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import moduloEndereco.util.customAnnotation.JsonCesanNotSerializable;





@Entity
@Table(name="CAD_AREA_ATUACAO")
public class AreaAtuacao extends EntityBase {
	
	
	@Column(name="nome")
	private String nome;
	
	@Column(name = "STATUS_REG")
	@JsonCesanNotSerializable
	private String statusReg;
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatusReg() {
		return statusReg;
	}
	public void setStatusReg(String statusReg) {
		this.statusReg = statusReg;
	}
	
	
	
	
}
