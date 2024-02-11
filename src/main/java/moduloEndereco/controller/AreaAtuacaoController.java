package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.service.AreaAtuacaoBairroService;
import moduloEndereco.service.AreaAtuacaoService;
import moduloEndereco.service.BairroService;
import moduloEndereco.service.MunicipioLocalidadeService;
import moduloEndereco.service.RegionalService;
import moduloEndereco.service.dto.AreaAtuacaoBairroDTO;
import moduloEndereco.service.dto.AreaAtuacaoDTO;
import moduloEndereco.service.dto.AreaAtuacaoWrapperDTO;
import moduloEndereco.service.dto.BairroDTO;
import moduloEndereco.service.dto.MensagemTipoGenericoDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.RegionalDTO;

@RestController
@RequestMapping("/backend-endereco/areaAtuacao")
public class AreaAtuacaoController {
	
	@Autowired
	private AreaAtuacaoService areaAtuacaoService;
	
	@Autowired
	private AreaAtuacaoBairroService areaAtuacaoBairroService;
	
	@Autowired
	private RegionalService regionalService;
	
	@Autowired
	private MunicipioLocalidadeService municipioLocalidadeService;
	
	@Autowired
	private BairroService bairroService;
	
	
	@CrossOrigin
	@GetMapping
	public AreaAtuacaoWrapperDTO pesquisar(@RequestParam(value = "nome", required = false) String nome,Pageable pageable) {
		return areaAtuacaoService.pesquisarAreaAtuacao(nome,pageable);
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public AreaAtuacaoDTO buscarPorId(@PathVariable(value = "id") Long id) {
		return areaAtuacaoService.buscarPorId(id);
	}
	
	@CrossOrigin
	@GetMapping("/regional")
	public List<RegionalDTO> listarRegional() {
		return regionalService.listarRegionais();
	}
	
	@CrossOrigin
	@GetMapping("/regional/{idAreaAtuacao}")
	public List<RegionalDTO> listarRegionalAreaAtuacaoBairro( @PathVariable(value = "idAreaAtuacao") Long idAreaAtuacao) {
		return areaAtuacaoBairroService.listRegional(idAreaAtuacao);
	}

	@CrossOrigin
	@GetMapping("/localidade")
	public List<MunicipioDTO> buscarPorRegional() {
		return municipioLocalidadeService.listarLocalidade();
	}

	@CrossOrigin
	@GetMapping("/localidade/{idAreaAtuacao}/{idRegional}")
	public List<MunicipioDTO> buscarPorRegionalAreaAtuacaoBairro( @PathVariable(value = "idAreaAtuacao") Long idAreaAtuacao, @PathVariable(value = "idRegional") Integer idRegional) {
		return areaAtuacaoBairroService.listLocalidade(idAreaAtuacao, idRegional);
	}
	
	@CrossOrigin
	@GetMapping("/bairro")
	public List<BairroDTO> buscarPorLocalidade() {
		return bairroService.buscarTodas();
	}
	@CrossOrigin
	@GetMapping("/bairro/{idAreaAtuacao}/{idLocalidade}")
	public List<BairroDTO> buscarPorLocalidadeAreaAtuacaoBairro(@PathVariable(value = "idAreaAtuacao") Long idAreaAtuacao, @PathVariable(value = "idLocalidade") Short idLocalidade) {
		return areaAtuacaoBairroService.listBairro(idAreaAtuacao, idLocalidade);
	}
	@CrossOrigin
	@GetMapping("/localidade/{idLocalidade}/bairro/{idBairro}")
	public MensagemTipoGenericoDTO<Boolean> validarAreaAtuacao(@PathVariable(value = "idLocalidade") Short idLocalidade, @PathVariable(value = "idBairro") Short idBairro,@RequestHeader("Authorization") String token) {
		return areaAtuacaoService.validarAreaAtuacao(idLocalidade, idBairro, token);
	}
	@CrossOrigin
	@GetMapping("/bairro/{idAreaAtuacao}")
	public List<BairroDTO> buscarAreaAtuacaoBairro(@PathVariable(value = "idAreaAtuacao") Long idAreaAtuacao) {
		return areaAtuacaoBairroService.listBairro(idAreaAtuacao);
	}


	@CrossOrigin
	@PostMapping
	public Long salvarAreaAtuacao(@RequestBody AreaAtuacaoBairroDTO areaAtuacaoBairroDTO,@RequestHeader("Authorization") String token) {
		return areaAtuacaoService.salvar(areaAtuacaoBairroDTO, token);
	}
	
	@CrossOrigin
	@PutMapping
	public Long atualizarAreaAtuacao(@RequestBody AreaAtuacaoBairroDTO areaAtuacaoBairroDTO,@RequestHeader("Authorization") String token) {
		return areaAtuacaoService.atualizar(areaAtuacaoBairroDTO, token);
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public void excluirAreaAtuacao(@PathVariable(value = "id") Long id,@RequestHeader("Authorization") String token) {
		 areaAtuacaoService.excluir(id, token);
	}
}
