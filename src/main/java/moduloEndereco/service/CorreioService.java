package moduloEndereco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import moduloEndereco.service.dto.CorreioStatusDTO;

public interface CorreioService {

	public void salvarArquivo(List<MultipartFile> listMultipartFile);
	
	public CorreioStatusDTO buscarStatus();
}
