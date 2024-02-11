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

import moduloEndereco.repository.filter.BairroFilter;
import moduloEndereco.service.BairroService;
import moduloEndereco.service.MunicipioLocalidadeService;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.BairroDropDownDTO;
import moduloEndereco.service.dto.BairroRetornoDTO;
import moduloEndereco.service.dto.BairroWrapperRetornoDTO;
import moduloEndereco.service.dto.ItemAtendimentoSSMatriculaImovelWrapperDTO;
import moduloEndereco.service.dto.MunicipioDTO;

@RestController
@RequestMapping("/backend-endereco/bairro")
public class BairroController {
	@Autowired
	private BairroService bairroService;
	@Autowired
	private MunicipioLocalidadeService municipioLocalidadeService;

	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void cadastrar(@RequestBody BairroDTO bairroDTO, @RequestHeader("Authorization") String token) {
		bairroService.salvar(bairroDTO, token);
	}

	@CrossOrigin
	@GetMapping
	public BairroWrapperRetornoDTO pesquisar(BairroFilter bairroFilter, Pageable pageable) {
		return bairroService.filtrar(bairroFilter, pageable);
	}

	@CrossOrigin
	@GetMapping("/{idBairro}/{idLocalidade}")
	public BairroRetornoDTO buscarPorId(@PathVariable(value = "idBairro") Short idBairro,
			@PathVariable(value = "idLocalidade") Short idLocalidade) {
		return bairroService.buscarPorId(idBairro, idLocalidade);
	}
	@GetMapping("/areaAtuacao/bairro/{idLocalidade}")
	public List<BairroDTO> listarBairroPorAreaAtuacao(@PathVariable(value = "idLocalidade") Short idLocalidade,@RequestHeader("Authorization") String token) {
		return bairroService.listarBairroPorAreaAtuacao(idLocalidade, token);
	}
	
	@CrossOrigin
	@PutMapping
	public BairroRetornoDTO atualizar(@RequestBody BairroDTO bairroDTO, @RequestHeader("Authorization") String token) {
		return bairroService.atualizar(bairroDTO, token);
	}

	@CrossOrigin
	@DeleteMapping("/{idBairro}/{idLocalidade}")
	public void excluir(@PathVariable(value = "idBairro") Short idBairro,
			@PathVariable(value = "idLocalidade") Short idLocalidade, @RequestHeader("Authorization") String token) {
		bairroService.excluir(idBairro, idLocalidade, token);
	}

	@CrossOrigin
	@GetMapping("/localidade")
	public List<MunicipioDTO> listarLocalidade() {
		return municipioLocalidadeService.listarLocalidade();
	}

	@CrossOrigin
	@GetMapping("/municipio")
	public List<MunicipioDTO> listarMuncipios() {
		return municipioLocalidadeService.listarTodosMunicipiosAtivos();
	}

	@CrossOrigin
	@GetMapping("/pendenciaBairro")
	public ItemAtendimentoSSMatriculaImovelWrapperDTO listarItemAtendimentoSSMatriculaImovel(
			@RequestParam(value = "codBairro", required = true) Short codBairro,
			@RequestParam(value = "codLocalidade", required = true) Short codLocalidade) {
		return bairroService.listarItemAtendimentoSSMatriculaImovel(codBairro, codLocalidade);
	}
	
	@CrossOrigin
	@GetMapping(value = "/bairros")
	public List<BairroDropDownDTO> findAllByOrderByNomeBairroAsc() {
		return bairroService.findAllByOrderByNomeBairroAsc();
	}

}
