package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.repository.filter.EnderecoFilter;
import moduloEndereco.service.EnderecoService;
import moduloEndereco.service.dto.EnderecoCepDTO;
import moduloEndereco.service.dto.EnderecoDTO;
import moduloEndereco.service.dto.EnderecoSituacaoAguaEsgotoDTO;
import moduloEndereco.service.dto.ImovelPesquisaEnderecoDTO;

@RestController
@RequestMapping("/backend-endereco/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@CrossOrigin
	@GetMapping
	public EnderecoDTO pesquisar(EnderecoFilter enderecoFilter) {
		return enderecoService.buscarEndereco(enderecoFilter);
	}

	@CrossOrigin
	@GetMapping("/enderecoCompleto")
	public EnderecoSituacaoAguaEsgotoDTO pesquisarEndereco(Integer numEndereco, Integer matriculaImovel,
			Integer cdClienteProp, Integer cdBairro, Integer sitLigacaoAgua, Integer sitLigacaoEsgoto,
			Integer cdCliente, Integer cdLogradouro, Integer cdCidade) {
		return enderecoService.pesquisarEndereco(numEndereco, matriculaImovel, cdClienteProp, cdBairro, sitLigacaoAgua,
				sitLigacaoEsgoto, cdCliente, cdLogradouro, cdCidade);
	}

	@CrossOrigin
	@GetMapping("/enderecosCompletos")
	public List<EnderecoSituacaoAguaEsgotoDTO> pesquisarEnderecos(@RequestBody List<ImovelPesquisaEnderecoDTO> imoveis) {
		return enderecoService.pesquisarEnderecos(imoveis);
	}

	@CrossOrigin
	@GetMapping("/{cep}")
	public List<EnderecoCepDTO> buscarEnderecoPorCep(@PathVariable(value = "cep") String cep) {
		return enderecoService.listarEnderecoPorCep(cep);
	}

}
