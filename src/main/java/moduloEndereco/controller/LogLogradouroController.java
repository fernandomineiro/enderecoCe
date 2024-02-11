package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moduloEndereco.service.LogLogradouroService;
import moduloEndereco.service.dto.LogLogradouroCepDTO;

@RestController
@RequestMapping("/backend-endereco/logLogradouro")
public class LogLogradouroController {

	@Autowired
	private LogLogradouroService logLogradouroService;

	@CrossOrigin
	@GetMapping("/{cep}")
	public List<LogLogradouroCepDTO> busca(@PathVariable(value = "cep") String cep) {
		return logLogradouroService.listarEnderecoPorCep(cep);
	}

}
