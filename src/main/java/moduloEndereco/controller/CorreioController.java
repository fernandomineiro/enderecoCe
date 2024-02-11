package moduloEndereco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import moduloEndereco.service.CorreioService;
import moduloEndereco.service.dto.CorreioStatusDTO;

@RestController
@RequestMapping("/backend-endereco/correio")
public class CorreioController {

	@Autowired
	private CorreioService correioService;
	
	@CrossOrigin
	@PostMapping
	public void salvar(@RequestParam("files") List<MultipartFile> files) {
		correioService.salvarArquivo(files);
	}
	
	@CrossOrigin
	@GetMapping
	public CorreioStatusDTO buscarStatusProcesso() {
		return correioService.buscarStatus();
	}
	
}
