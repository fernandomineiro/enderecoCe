package moduloEndereco.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloEndereco.repository.filter.LogradouroFilter;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.LogradouroDTO;
import moduloEndereco.service.dto.LogradouroRetornoDTO;
import moduloEndereco.service.dto.LogradouroWrapperRetornoDTO;

public interface LogradouroService {

	public void salvar(LogradouroDTO LogradouroDTO, String token);

	public List<LogradouroDTO> listAll();

	public LogradouroRetornoDTO buscarPorcodLogradouroCodLocalidade(Short codLogradouro, Short codLocalidade);

	public void excluir(Short idLogradouro, Short idLocalidade, String token);

	public LogradouroDTO atualizar(LogradouroDTO logradouroDTO, String token);

	public LogradouroWrapperRetornoDTO filtrar(LogradouroFilter logradouroFilter, Pageable pageable);

	ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(Short codLogradouro,
			Short codLocalidade,List<Short> idsBairro,boolean flagExclusao);
	public List<LogradouroDTO> buscarLogradouroPorLocalidadeBairro(Short cdBairro,Short cdLocalidade);
	
	
	

}
