package moduloEndereco.service.dto;

import java.util.List;

public class LogradouroWrapperDTO {

	List<LogradouroDTO> listLogradouroDTO;
	Long totalRegistros;

	public List<LogradouroDTO> getListLogradouroDTO() {
		return listLogradouroDTO;
	}

	public void setListLogradouroDTO(List<LogradouroDTO> listLogradouroDTO) {
		this.listLogradouroDTO = listLogradouroDTO;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

}
