package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.repository.filter.LogradouroFilter;
import moduloEndereco.service.BairroService;
import moduloEndereco.service.LogradouroService;
import moduloEndereco.service.MunicipioLocalidadeService;
import moduloEndereco.service.TipoLogradouroService;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.LogradouroDTO;
import moduloEndereco.service.dto.LogradouroRetornoDTO;
import moduloEndereco.service.dto.LogradouroWrapperRetornoDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.TipoLogradouroDTO;

@RestController

@RequestMapping("/backend-endereco/logradouro")
public class LogradouroController {
	@Autowired
	private LogradouroService logradouroService;

	@Autowired
	private BairroService bairroService;

	@Autowired
	private MunicipioLocalidadeService municipioLocalidadeService;
	
	@Autowired
	private TipoLogradouroService  tipoLogradouroService;

	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void cadastrar(@RequestBody LogradouroDTO logradouroDTO, @RequestHeader("Authorization") String token) {
		logradouroService.salvar(logradouroDTO, token);
	}

	@CrossOrigin
	@DeleteMapping("/{idLogradouro}/{idLocalidade}")
	public void excluir(@PathVariable(value = "idLogradouro") Short idLogradouro,
			@PathVariable(value = "idLocalidade") Short idLocalidade, @RequestHeader("Authorization") String token) {
		logradouroService.excluir(idLogradouro, idLocalidade, token);
	}

	@CrossOrigin
	@PutMapping
	public LogradouroDTO atualizar(@RequestBody LogradouroDTO logradouroDTO,
			@RequestHeader("Authorization") String token) {
		return logradouroService.atualizar(logradouroDTO, token);
	}

	@CrossOrigin
	@GetMapping
	public LogradouroWrapperRetornoDTO pesquisar(LogradouroFilter bairroFilter, Pageable pageable) {
		return logradouroService.filtrar(bairroFilter, pageable);
	}

	@CrossOrigin
	@GetMapping("/{idLogradouro}/{idLocalidade}")
	public LogradouroRetornoDTO buscarPorId(@PathVariable(value = "idLogradouro") Short idLogradouro,
			@PathVariable(value = "idLocalidade") Short idLocalidade) {
		return logradouroService.buscarPorcodLogradouroCodLocalidade(idLogradouro, idLocalidade);
	}

	@CrossOrigin
	@GetMapping("/bairro/{cdLocalidade}")
	public List<BairroDTO> listaBairroPorCodLocalidade(@PathVariable(value = "cdLocalidade") Short cdLocalidade) {
		return bairroService.listPorIdLocalidade(cdLocalidade);
	}

	@CrossOrigin
	@GetMapping("/localidade")
	public List<MunicipioDTO> listarLocalidade() {
		return municipioLocalidadeService.listarLocalidade();

	}

	@CrossOrigin
	@GetMapping("/siglaLogradouro")
	public List<TipoLogradouroDTO> listarSiglaLogradouro() {
		return tipoLogradouroService.buscarTipoLogradouro();

	}
	
	@CrossOrigin
	@GetMapping("/pendenciaLogradouro")
	public ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(
			@RequestParam(value = "codLogradouro", required = true) Short codLogradouro,
			@RequestParam(value = "codLocalidade", required = true) Short codLocalidade,
			@RequestParam(value = "idsBairro", required = true)List<Short> idsBairro,
			@RequestParam(value = "flagExclusao", required = true)boolean flagExclusao) {
		return logradouroService.listarItemAtendimentoSSMatriculaImovel(codLogradouro, codLocalidade,idsBairro, flagExclusao);
	}
	@CrossOrigin
	@GetMapping("bairro/{idBairro}/localidade/{idLocalidade}")
	public List<LogradouroDTO> buscarLogradouro(@PathVariable(value = "idBairro") Short idBairro,
			@PathVariable(value = "idLocalidade") Short idLocalidade) {
		return logradouroService.buscarLogradouroPorLocalidadeBairro(idBairro, idLocalidade);
	}

}
