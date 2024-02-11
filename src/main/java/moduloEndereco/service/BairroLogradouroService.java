package moduloEndereco.service;

import java.util.List;

import moduloEndereco.model.IdLogradouroIdLocalidade;

public interface BairroLogradouroService {

	public void salvarBairroLogradouro(List<Short>idBairro,IdLogradouroIdLocalidade idLogradouroIdLocalidade,String token);
	
	public void excluirBairroLogradouro(IdLogradouroIdLocalidade idLogradouroIdLocalidade,String token);
	
	public void atualizarBairroLogradouro(List<Short>idBairro,IdLogradouroIdLocalidade idLogradouroIdLocalidade,String token);
	
	public List<Short> listaBairro(IdLogradouroIdLocalidade idLogradouroIdLocalidade);
}
