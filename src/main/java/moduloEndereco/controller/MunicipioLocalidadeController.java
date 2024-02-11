package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.service.MunicipioLocalidadeService;
import moduloEndereco.service.dto.HorarioAtendimentoDTO;
import moduloEndereco.service.dto.MunicipioDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeRetornoDTO;
import moduloEndereco.service.dto.MunicipioLocalidadeWrapperDTO;
import moduloEndereco.service.dto.RegionalDTO;

@RestController
@RequestMapping("/backend-endereco/municipioLocalidade")
public class MunicipioLocalidadeController {

	@Autowired
	private MunicipioLocalidadeService service;

	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("listarMunicipiosAtivos")
	public List<MunicipioDTO> listarMunicipiosAtivos() {
		return service.listarTodosMunicipiosAtivos();
	}
	
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/listarLocalidade")
	public List<MunicipioDTO> listarLocalidade() {
		return service.listarTodasLocalidades();
	}
	
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/listarMunicipioLocalidade", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<MunicipioDTO> listarMunicipiosLocalidades() {
		return service.listarMunicipiosLocalidades();
	}

	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/areaAtuacao/listarMunicipioLocalidade/")
	public List<MunicipioDTO> listarMunicipiosLocalidades(@RequestHeader("Authorization") String token) {
		return service.listarMunicipiosLocalidadesPorAreaAtuacao(token);
	}
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("listarTodasRegionaisAtivas")
	public List<RegionalDTO> listarTodasRegionaisAtivas() {
		return service.listarTodasRegionaisAtivas();
	}

	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("listarHorariosAtendimento")
	public List<HorarioAtendimentoDTO> listarHorariosAtendimento() {
		return service.listarTodosHorariosAtendimento();
	}

	@CrossOrigin
	@GetMapping("pesquisarAtivoPorId")
	public MunicipioLocalidadeDTO pesquisarAtivoPorId(@RequestParam(value = "id", defaultValue = "") Short id) {
		return service.pesquisarAtivoPorId(id);
	}

	@CrossOrigin
	@GetMapping("pesquisarMunicipioLocalidade")
	public MunicipioLocalidadeWrapperDTO pesquisarMunicipioLocalidade(
			@RequestParam(value = "cdCidade", required = false) Short cdCidade,
			@RequestParam(value = "municipio", required = false) String municipio,
			@RequestParam(value = "localidade", required = false) String localidade,
			@RequestParam(value = "regional", required = false) String regional,
			@RequestParam(value = "cdTarifa", required = false) Short cdTarifa,
			@RequestParam(value = "temAgua", required = false) String temAgua,
			@RequestParam(value = "temEsgoto", required = false) String temEsgoto,
			@RequestParam(value = "temDispEsgoto", required = false) String temDispEsgoto,
			@RequestParam(value = "tipo", required = false) String tipo,
			Pageable pageable) {
		return service.pesquisarMunicipioLocalidade(cdCidade, municipio, localidade, regional, cdTarifa, temAgua, temEsgoto, temDispEsgoto, tipo, pageable);
	}

	@CrossOrigin
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void incluirMunicipioLocalidade(@RequestBody MunicipioLocalidadeDTO municipioLocalidadeDTO,
			@RequestHeader("Authorization") String token) {
		service.incluirMunicipioLocalidade(municipioLocalidadeDTO, token);
	}

	@CrossOrigin
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public MunicipioLocalidadeRetornoDTO alterarMunicipioLocalidade(
			@RequestBody MunicipioLocalidadeDTO municipioLocalidadeDTO, @RequestHeader("Authorization") String token) {
		return service.alterarMunicipioLocalidade(municipioLocalidadeDTO, token);
	}

	@CrossOrigin
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void excluirMunicipioLocalidade(@RequestParam(value = "cdCidade", required = false) Short cdCidade,
			@RequestHeader("Authorization") String token) {
		service.excluirMunicipioLocalidade(cdCidade, token);
	}
}
