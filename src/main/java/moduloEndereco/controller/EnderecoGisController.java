package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.repository.filter.EnderecoGisFilter;
import moduloEndereco.repository.filter.ServicoAtendimentoGisFilter;
import moduloEndereco.service.EnderecoGisService;
import moduloEndereco.service.ServicoAtendimentoService;
import moduloEndereco.service.dto.CriticaEnderecoDTO;
import moduloEndereco.service.dto.EnderecoGisDTO;
import moduloEndereco.service.dto.EnderecoGisDTOWrapper;
import moduloEndereco.service.dto.EnderecoGisIdsDTO;
import moduloEndereco.service.dto.ServicoAtendimentoGisDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessadoWrapperDTO;
import moduloEndereco.service.dto.SolicitacaoAtendimentoProcessamentoWrapperDTO;

@RestController
@RequestMapping("/backend-endereco/enderecoGis")
public class EnderecoGisController {

	@Autowired
	private EnderecoGisService enderecoGisService;
	@Autowired
	private ServicoAtendimentoService servicoAtendimentoService;

	@CrossOrigin
	@GetMapping("/selecao")
	public EnderecoGisDTOWrapper buscarEnderecosAlterado(Pageable pageable) {
		return enderecoGisService.buscarEnderecosAlterado(pageable);
	}

	@CrossOrigin
	@GetMapping(value = "/pesquisa")
	public List<EnderecoGisDTO> pesquisar(EnderecoGisFilter enderecoGisFilter) {
		return enderecoGisService.filtrar(enderecoGisFilter);
	}
	
	@CrossOrigin
	@GetMapping(value = "/pesquisaSS")
	public List<ServicoAtendimentoGisDTO> pesquisaSS(ServicoAtendimentoGisFilter servicoAtendimentoGisFilter) {
		return servicoAtendimentoService.filtrarSS(servicoAtendimentoGisFilter);
	}
	

	@CrossOrigin
	@GetMapping("/critica")
	public List<CriticaEnderecoDTO> buscarEnderecosAlterado(@RequestParam List<Integer> ids) {
		return enderecoGisService.validarAtualizacao(ids);
	}

	@CrossOrigin
	@PutMapping
	public void atualizarEnderecos(@RequestBody EnderecoGisIdsDTO enderecoGisIdsDTO,
			@RequestHeader("Authorization") String token) {
		enderecoGisService.atualizarEndereco(enderecoGisIdsDTO, token);
	}

	@CrossOrigin
	@GetMapping("/processamento")
	public SolicitacaoAtendimentoProcessamentoWrapperDTO buscarServicoAtendimentoProcessamento(Pageable pageable) {
		return servicoAtendimentoService.buscarProcessamento(pageable);
	}

	@CrossOrigin
	@GetMapping("/processado")
	public SolicitacaoAtendimentoProcessadoWrapperDTO buscarServicoAtendimentoProcessado(Pageable pageable) {
		return servicoAtendimentoService.buscarProcessado(pageable);
	}
}
