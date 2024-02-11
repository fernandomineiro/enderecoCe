package moduloEndereco.service;

import java.util.List;

import moduloEndereco.service.dto.LogCpcDTO;

public interface LogCpcService {

	public void salvar(List<String> listCpc)throws Exception;

	List<LogCpcDTO> listarEnderecoPorCep(String cep);
}
