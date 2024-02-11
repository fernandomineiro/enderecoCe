package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.service.dto.HorarioAtendimentoDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeRetornoDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeWrapperDTO;
import moduloEndereco.service.dto.RegionalDTO;

public interface MunicipioLocalidadeService {

	List<MunicipioDTO> listarTodosMunicipiosAtivos();
	
	List<RegionalDTO> listarTodasRegionaisAtivas();

	List<HorarioAtendimentoDTO> listarTodosHorariosAtendimento();
	
	MunicipioLocalidadeDTO pesquisarAtivoPorId(Short id);
	
	MunicipioLocalidadeWrapperDTO pesquisarMunicipioLocalidade(
			Short cdCidade,
			String municipio,
			String localidade,
			String regional,
			Short cdTarifa,
			String temAgua,
			String temEsgoto,
			String temDispEsgoto,
			String tipo,
			Pageable pageable
			);
	
	void incluirMunicipioLocalidade(MunicipioLocalidadeDTO municipioLocalidadeDTO, String token);
	
	MunicipioLocalidadeRetornoDTO alterarMunicipioLocalidade(MunicipioLocalidadeDTO municipioLocalidadeDTO, String token);
	
	void excluirMunicipioLocalidade(Short id, String token);
	
	List<MunicipioDTO> listarLocalidade();
	
	List<MunicipioDTO> buscarPorRegional(Integer id);
	
	List<MunicipioDTO> listarTodasLocalidades();
	
	List<MunicipioDTO> listarMunicipiosLocalidades();
	
	List<MunicipioDTO> listarMunicipiosLocalidadesPorAreaAtuacao(String token);
}
