package moduloEndereco.service.dto;

import java.util.List;

public class BairroWrapperDTO {

	private List<BairroDTO> listBairroDTO;
	private Long totalRegistros;

	public List<BairroDTO> getListBairroDTO() {
		return listBairroDTO;
	}

	public void setListBairroDTO(List<BairroDTO> listBairroDTO) {
		this.listBairroDTO = listBairroDTO;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

}
