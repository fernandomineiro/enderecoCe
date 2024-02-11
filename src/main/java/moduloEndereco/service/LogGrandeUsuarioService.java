package moduloEndereco.service;

import java.util.List;

import moduloEndereco.service.dto.LogGrandeUsuarioDTO;

public interface LogGrandeUsuarioService {

	public void salvar(List<String> listGrandeUsuario) throws Exception;

	public List<LogGrandeUsuarioDTO> listarEnderecoPorCep(String cep);
}
