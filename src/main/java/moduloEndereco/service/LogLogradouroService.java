package moduloEndereco.service;

import java.util.List;

import moduloEndereco.service.dto.LogLogradouroCepDTO;

public interface LogLogradouroService {

	public void salvar(List<String> listLogradouro) throws Exception;

	public List<LogLogradouroCepDTO> listarEnderecoPorCep(String cep);

}