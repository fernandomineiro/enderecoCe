package moduloEndereco.service.dto;

import java.util.List;

public class EnderecoGisDTOWrapper {

	private List<EnderecoGisDTO> listEnderecoGisDTO;
	private Integer totalRegistros;

	public List<EnderecoGisDTO> getListEnderecoGisDTO() {
		return listEnderecoGisDTO;
	}

	public void setListEnderecoGisDTO(List<EnderecoGisDTO> listEnderecoGisDTO) {
		this.listEnderecoGisDTO = listEnderecoGisDTO;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}


}
