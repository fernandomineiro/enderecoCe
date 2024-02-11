package moduloEndereco.service.dto;

import java.util.List;

public class LogradouroWrapperRetornoDTO {

	List<LogradouroRetornoDTO> listLogradouroDTO;
	Long totalRegistros;
	
	public List<LogradouroRetornoDTO> getListLogradouroDTO() {
		return listLogradouroDTO;
	}
	public void setListLogradouroDTO(List<LogradouroRetornoDTO> listLogradouroDTO) {
		this.listLogradouroDTO = listLogradouroDTO;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	

}
