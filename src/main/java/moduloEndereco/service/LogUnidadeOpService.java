package moduloEndereco.service;

import java.util.List;

import moduloEndereco.service.dto.LogUnidadeOpDTO;

public interface LogUnidadeOpService {

	public void salvar(List<String> listUnidadeOp) throws Exception;
	
	public List<LogUnidadeOpDTO> buscarCep(String cep);
}
