package moduloEndereco.service;

import java.util.List;

import moduloEndereco.service.dto.LogLocalidadeDTO;

public interface LogLocalidadeService {

	public void salvar(List<String> listLocalidade) throws Exception;
	
	public List<LogLocalidadeDTO> buscarPorCep(String cep);
}
