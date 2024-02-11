package moduloEndereco.service.dto;

import java.util.List;

public class BairroWrapperRetornoDTO {

	private List<BairroRetornoDTO> listBairroDTO;
	private Long totalRegistros;
	
	public List<BairroRetornoDTO> getListBairroDTO() {
		return listBairroDTO;
	}
	public void setListBairroDTO(List<BairroRetornoDTO> listBairroDTO) {
		this.listBairroDTO = listBairroDTO;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
}
