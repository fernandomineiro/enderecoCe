package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.model.Regional;
import moduloEndereco.repository.filter.RegionalFilter;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.dto.RegionalWrapperDTO;

public interface RegionalService {

	public RegionalDTO executarProcedimentoSalvar(RegionalDTO dto, String token);
	
	public RegionalDTO executarProcedimentoEditar(RegionalDTO dto, String token);
	
	public RegionalDTO executarProcedimentoRemover(Integer id, String token);
	
	public Regional buscarRegionalPorID(Integer id);
	
	public RegionalWrapperDTO filtrar(RegionalFilter regionalFilter, Pageable pageable);
	
	public List<RegionalDTO> listarRegionais();
}
