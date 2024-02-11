package moduloEndereco.service.dto;

import java.util.List;

public class RegionalWrapperDTO {

	private List<RegionalDTO> listRegionalDTO;
	private Long totalRegistros;
	
	public List<RegionalDTO> getListRegionalDTO() {
		return listRegionalDTO;
	}
	public void setListRegionalDTO(List<RegionalDTO> listRegionalDTO) {
		this.listRegionalDTO = listRegionalDTO;
	}
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	
	
}
