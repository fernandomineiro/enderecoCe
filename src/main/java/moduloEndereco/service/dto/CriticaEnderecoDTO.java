package moduloEndereco.service.dto;

public class CriticaEnderecoDTO {

	private Long id;
	private Integer matricula;
	private Integer dv;
	private String critica;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	
	public Integer getDv() {
		return dv;
	}
	public void setDv(Integer dv) {
		this.dv = dv;
	}
	public String getCritica() {
		return critica;
	}
	public void setCritica(String critica) {
		this.critica = critica;
	}
	
	
}
