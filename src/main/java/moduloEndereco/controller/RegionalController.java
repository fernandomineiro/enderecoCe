package moduloEndereco.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.model.Regional;
import moduloEndereco.repository.filter.RegionalFilter;
import moduloEndereco.service.RegionalService;
import moduloEndereco.service.dto.RegionalDTO;
import moduloEndereco.service.dto.RegionalWrapperDTO;

/**
 * @author Ivan Alves
 * <b>Gerencia dados de regional</b>
 */
@RestController
@RequestMapping("/backend-endereco/regional")
public class RegionalController {
	
	@Autowired
	private RegionalService service;
	
	/**
	 * @author Ivan Alves
	 * <p>Endpoint para buscar regional por id.</p>
	 */
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Regional consultarPorID(@PathVariable(value = "id") Integer id) {
		Regional regional = this.service.buscarRegionalPorID(id);
		return regional;
	}
	
	/**
	 * @author Ivan Alves
	 * <p>Endpoint para pesquisar os dados conforme parametros informados.</p>
	 */
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public RegionalWrapperDTO pesquisar(RegionalFilter regionalFilter, Pageable pageable) {
		return this.service.filtrar(regionalFilter, pageable);
	}
	
	/**
	 * @author Ivan Alves
	 * <p>Endpoint para salvar regional.</p>
	 */
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RegionalDTO cadastrar(@Valid @RequestBody RegionalDTO dto, @RequestHeader("Authorization") String token) {
		RegionalDTO dtoProduzido = this.service.executarProcedimentoSalvar(dto, token);
		return dtoProduzido;
	}
	
	/**
	 * @author Ivan Alves
	 * <p>Endpoint para editar regional.</p>
	 */
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public RegionalDTO editar(@Valid @RequestBody RegionalDTO dto, @RequestHeader("Authorization") String token) {
		RegionalDTO dtoProduzido = this.service.executarProcedimentoEditar(dto, token);
		return dtoProduzido;
	}
	
	/**
	 * @author Ivan Alves
	 * <p>Endpoint para remover regional.</p>
	 */
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public RegionalDTO remover(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String token) {
		RegionalDTO dtoProduzido = this.service.executarProcedimentoRemover(id, token);
		return dtoProduzido;
	}

}
