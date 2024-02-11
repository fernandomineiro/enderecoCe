package moduloEndereco.service.dto;

import java.util.List;

public class MunicipioLocalidadeWrapperDTO {

	List<MunicipioLocalidadeWrapper> listMunicipioLocalidadeRetornoDTO;
	Long totalRegistros;
	
	public Long getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public List<MunicipioLocalidadeWrapper> getListMunicipioLocalidadeRetornoDTO() {
		return listMunicipioLocalidadeRetornoDTO;
	}
	public void setListMunicipioLocalidadeRetornoDTO(
			List<MunicipioLocalidadeWrapper> listMunicipioLocalidadeRetornoDTO) {
		this.listMunicipioLocalidadeRetornoDTO = listMunicipioLocalidadeRetornoDTO;
	}
	
}
