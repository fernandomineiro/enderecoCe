package moduloEndereco.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloEndereco.excecoes.MsgGenericaPersonalizadaException;
import moduloEndereco.model.IdBairroIdLocalidade;
import moduloEndereco.model.IdLogradouroIdLocalidade;
import moduloEndereco.model.Logradouro;
import moduloEndereco.repository.BairroRepository;
import moduloEndereco.repository.EnderecoSituacaoAguaEsgotoRepository;
import moduloEndereco.repository.LogBairroRepository;
import moduloEndereco.repository.LogLocalidadeRepository;
import moduloEndereco.repository.LogradouroRepository;
import moduloEndereco.repository.MunicipioLocalidadeRepository;
import moduloEndereco.repository.filter.EnderecoFilter;
import moduloEndereco.service.EnderecoService;
import moduloEndereco.service.LogCpcService;
import moduloEndereco.service.LogGrandeUsuarioService;
import moduloEndereco.service.LogLocalidadeService;
import moduloEndereco.service.LogLogradouroService;
import moduloEndereco.service.LogUnidadeOpService;
import moduloEndereco.service.dto.EnderecoCepDTO;
import moduloEndereco.service.dto.EnderecoDTO;
import moduloEndereco.service.dto.EnderecoSituacaoAguaEsgotoDTO;
import moduloEndereco.service.dto.ImovelPesquisaEnderecoDTO;
import moduloEndereco.service.dto.LogCpcDTO;
import moduloEndereco.service.dto.LogGrandeUsuarioDTO;
import moduloEndereco.service.dto.LogLocalidadeDTO;
import moduloEndereco.service.dto.LogLogradouroCepDTO;
import moduloEndereco.service.dto.LogUnidadeOpDTO;

@Transactional
@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private MunicipioLocalidadeRepository municipioLocalidadeRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private LogradouroRepository logradouroRepository;
	@Autowired
	private EnderecoSituacaoAguaEsgotoRepository enderecoRepository;
	@Autowired
	private LogBairroRepository logBairroRepository;
	@Autowired
	private LogLogradouroService logLogradouroService;
	@Autowired
	private LogCpcService logCpcService;
	@Autowired
	private LogGrandeUsuarioService logGrandeUsuarioService;
	@Autowired
	private LogLocalidadeService logLocalidadeService;
	@Autowired
	private LogUnidadeOpService logUnidadeOpService;
	@Autowired
	private LogLocalidadeRepository logLocalidadeRepository;

	@Override
	public EnderecoDTO buscarEndereco(EnderecoFilter enderecoFilter) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setLocalidade(
				municipioLocalidadeRepository.findById(enderecoFilter.getCdLocalidade()).get().getDcCidade());
		enderecoDTO.setBairro(bairroRepository
				.findById(new IdBairroIdLocalidade(enderecoFilter.getCdBairro(), enderecoFilter.getCdLocalidade()))
				.get().getNomeBairro());
		Logradouro logradouro = logradouroRepository.findById(
				new IdLogradouroIdLocalidade(enderecoFilter.getCdLogradouro(), enderecoFilter.getCdLocalidade())).get();
		enderecoDTO.setLogradouro(logradouro.getSiglaLogradouro() + ":" + " " + logradouro.getNomeLogradouro());
		return enderecoDTO;
	}

	@Override
	public EnderecoSituacaoAguaEsgotoDTO pesquisarEndereco(Integer numEndereco, Integer matriculaImovel,
			Integer cdClienteProp, Integer cdBairro, Integer sitLigacaoAgua, Integer sitLigacaoEsgoto,
			Integer cdCliente, Integer cdLogradouro, Integer cdCidade) {
		return enderecoRepository.pesquisarEndereco(numEndereco, matriculaImovel, cdClienteProp, cdBairro,
				sitLigacaoAgua, sitLigacaoEsgoto, cdCliente, cdLogradouro, cdCidade);
	}

	@Override
	public List<EnderecoSituacaoAguaEsgotoDTO> pesquisarEnderecos(List<ImovelPesquisaEnderecoDTO> imoveis) {
		List<EnderecoSituacaoAguaEsgotoDTO> enderecos = new ArrayList<>();

		for (ImovelPesquisaEnderecoDTO imovel : imoveis) {

			enderecos.add(enderecoRepository.pesquisarEndereco(
					imovel.getNumeroEndereco(), imovel.getMatriculaImovel(),
					imovel.getCodClienteProp(), imovel.getCodBairro().intValue(), 
					imovel.getIdSituacaoLigacaoAgua(), imovel.getIdSituacaoLigacaoEsgoto(),
					imovel.getCodCliente(), imovel.getCodLogradouro().intValue(), 
					imovel.getCodCidade().intValue()));
		}

		return enderecos;
	}

	@Override
	public List<EnderecoCepDTO> listarEnderecoPorCep(String cep) {

		List<EnderecoCepDTO> listEnderecoCep = new ArrayList<>();
		if (cep == null) {
			throw new MsgGenericaPersonalizadaException("O cep é obrigatório");
		}
		List<LogLogradouroCepDTO> listLogLogradouroCep = logLogradouroService.listarEnderecoPorCep(cep);

		if (!listLogLogradouroCep.isEmpty())
		{
		 for(LogLogradouroCepDTO logLogradouroCepDTO : listLogLogradouroCep) {	
			EnderecoCepDTO enderecoCep = new EnderecoCepDTO();
			enderecoCep.setBairro(logLogradouroCepDTO.getBairro());
			enderecoCep.setCep(logLogradouroCepDTO.getCep());
			enderecoCep.setComplementoLogradouro(logLogradouroCepDTO.getComplementoLogradouro());
			enderecoCep.setLogradouro(logLogradouroCepDTO.getSiglaLogradouro() + " " + logLogradouroCepDTO.getLogradouro());
			enderecoCep.setLocalidade(logLogradouroCepDTO.getLocalidade());
			enderecoCep.setUf(logLogradouroCepDTO.getUf());
			listEnderecoCep.add(enderecoCep);
		 }

			return listEnderecoCep;
		}
		 
		
		List<LogCpcDTO> listEnderecoCaixaPostal = logCpcService.listarEnderecoPorCep(cep);

		if (!listEnderecoCaixaPostal.isEmpty()) {
			for(LogCpcDTO logCpcDTO : listEnderecoCaixaPostal) {	
			EnderecoCepDTO enderecoCep = new EnderecoCepDTO();
			enderecoCep.setNomeReferencia(logCpcDTO.getNomeCpc());
			enderecoCep.setCep(logCpcDTO.getCep());
			enderecoCep.setLogradouro(logCpcDTO.getEnderecoCpc());
			enderecoCep.setLocalidade(logCpcDTO.getLocalidade());
			enderecoCep.setUf(logCpcDTO.getUf());
			listEnderecoCep.add(enderecoCep);
			}
			return listEnderecoCep;
			
		}

		List<LogGrandeUsuarioDTO> listEnderecoGrandeUsuario = logGrandeUsuarioService.listarEnderecoPorCep(cep);

		if (!listEnderecoGrandeUsuario.isEmpty()) {
			for(LogGrandeUsuarioDTO logGrandeUsuarioDTO:listEnderecoGrandeUsuario) {
				EnderecoCepDTO enderecoCep = new EnderecoCepDTO();
			enderecoCep.setNomeReferencia(logGrandeUsuarioDTO.getNomeGRU());
			enderecoCep.setCep(logGrandeUsuarioDTO.getCep());
			enderecoCep.setLogradouro(logGrandeUsuarioDTO.getEnderecoGRU());
			enderecoCep.setLocalidade(logGrandeUsuarioDTO.getLocalidade());
			enderecoCep.setUf(logGrandeUsuarioDTO.getUf());
			enderecoCep.setBairro(
					logBairroRepository.findByNumeroBairro(logGrandeUsuarioDTO.getNumeroBairro()).getBairro());
			listEnderecoCep.add(enderecoCep);
			}
			
			return listEnderecoCep;
		}
		
		List<LogLocalidadeDTO> listEnderecoLocalidade = logLocalidadeService.buscarPorCep(cep);

		if (!listEnderecoLocalidade.isEmpty()) {

			for(LogLocalidadeDTO logLocalidadeDTO : listEnderecoLocalidade) {
				EnderecoCepDTO enderecoCep = new EnderecoCepDTO();
				enderecoCep.setCep(logLocalidadeDTO.getCep());
				enderecoCep.setLocalidade(logLocalidadeDTO.getLocalidade());
				enderecoCep.setUf(logLocalidadeDTO.getUf());
				listEnderecoCep.add(enderecoCep);
			}
			return listEnderecoCep;
		}

		List<LogUnidadeOpDTO> listEnderecoUnidade = logUnidadeOpService.buscarCep(cep);

		if (!listEnderecoUnidade.isEmpty()) {
			for(LogUnidadeOpDTO logUnidadeOpDTO:listEnderecoUnidade) {
				EnderecoCepDTO enderecoCep = new EnderecoCepDTO();

			enderecoCep.setCep(logUnidadeOpDTO.getCep());
			enderecoCep.setBairro(
					logBairroRepository.findByNumeroBairro(logUnidadeOpDTO.getBaiNu()).getBairro());
			enderecoCep.setLogradouro(logUnidadeOpDTO.getEnderecoUnidadaOp());
		    enderecoCep.setLocalidade(logLocalidadeRepository.findById(logUnidadeOpDTO.getNumeroLocalidade().getNumeroLocalidade()).get().getLocalidade());
			enderecoCep.setUf(logUnidadeOpDTO.getUf());
			listEnderecoCep.add(enderecoCep);
			}
			return listEnderecoCep;
		}

		
		return listEnderecoCep;

	}

}
