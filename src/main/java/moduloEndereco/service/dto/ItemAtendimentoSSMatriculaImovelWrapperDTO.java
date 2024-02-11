package moduloEndereco.service.dto;

import java.util.List;

public class ItemAtendimentoSSMatriculaImovelWrapperDTO {

	List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoRetornoDTO;

	List<ImovelLogradouroDTO> listImovelLogradouroDTO;

	public List<ItemAtendimentoSSRetornoDTO> getListItemAtendimentoRetornoDTO() {
		return listItemAtendimentoRetornoDTO;
	}

	public void setListItemAtendimentoRetornoDTO(List<ItemAtendimentoSSRetornoDTO> listItemAtendimentoRetornoDTO) {
		this.listItemAtendimentoRetornoDTO = listItemAtendimentoRetornoDTO;
	}

	public List<ImovelLogradouroDTO> getListImovelLogradouroDTO() {
		return listImovelLogradouroDTO;
	}

	public void setListImovelLogradouroDTO(List<ImovelLogradouroDTO> listImovelLogradouroDTO) {
		this.listImovelLogradouroDTO = listImovelLogradouroDTO;
	}

}
