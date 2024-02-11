package moduloEndereco.service;

import java.util.List;

import moduloEndereco.repository.filter.EnderecoFilter;
import moduloEndereco.service.dto.EnderecoCepDTO;
import moduloEndereco.service.dto.EnderecoDTO;
import moduloEndereco.service.dto.EnderecoSituacaoAguaEsgotoDTO;
import moduloEndereco.service.dto.ImovelPesquisaEnderecoDTO;

public interface EnderecoService {

	public EnderecoDTO buscarEndereco(EnderecoFilter enderecoFilter);
	
	public EnderecoSituacaoAguaEsgotoDTO pesquisarEndereco(
			Integer numEndereco, Integer matriculaImovel, Integer cdClienteProp, 
			Integer cdBairro, Integer sitLigacaoAgua, Integer sitLigacaoEsgoto,
			Integer cdCliente, Integer cdLogradouro, Integer cdCidade);
	
	public List<EnderecoSituacaoAguaEsgotoDTO> pesquisarEnderecos(List<ImovelPesquisaEnderecoDTO> imoveis);

	public List<EnderecoCepDTO> listarEnderecoPorCep(String cep);
	
}
